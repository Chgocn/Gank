package com.chgocn.lib.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.chgocn.lib.R;
import com.chgocn.lib.presenter.Presenter;
import com.chgocn.lib.util.SystemBarTintManager;
import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.RxLifecycle;
import com.trello.rxlifecycle.android.ActivityEvent;

import javax.annotation.Nonnull;

import butterknife.ButterKnife;
import rx.Observable;
import rx.subjects.BehaviorSubject;


/**
 * Created by chgocn.
 */
public abstract class BaseActivity extends AppCompatActivity implements LifecycleProvider<ActivityEvent> {

    private static final String TAG = "BaseActivity";

    private final BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();
    private boolean showHomeAsUp = true;

    protected Toolbar toolbar;
    protected TextView toolbarTitle;
    protected ImageView toolbarImage;
    private int primaryColor;
    private int primaryDarkColor;
    private boolean statusBarTranslucent;

    public void initPresenter() {
        initInjector();
        if (getPresenter() != null) {
            getPresenter().setView(this);
        }
    }

    public abstract void initInjector();

    protected abstract int getContentView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        Log.d(TAG, getClass().getSimpleName() + ".onCreate...");
        ButterKnife.bind(this);
//        Resources.Theme theme = this.getTheme();
//        TypedValue typedValue = new TypedValue();
//        // get primary color
//        theme.resolveAttribute(R.attr.colorPrimary, typedValue, true);
//        primaryColor = typedValue.data;
//        theme.resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
//        primaryDarkColor = typedValue.data;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            TypedArray windowTranslucentAttribute = theme.obtainStyledAttributes(new int[]{android.R.attr.windowTranslucentStatus});
//            statusBarTranslucent = windowTranslucentAttribute.getBoolean(0, false);
//        }
        initActionBar(isShowHomeAsUp());
        initPresenter();
        lifecycleSubject.onNext(ActivityEvent.CREATE);
    }

    /**
     * initActionBar
     */
    protected final void initActionBar(boolean showHomeAsUp) {
        // set Toolbar as actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (null != toolbar) {
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            if (null != actionBar) {
                actionBar.setDisplayHomeAsUpEnabled(showHomeAsUp);
            }
            toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        }

        // Apply background tinting to the Android system UI when using KitKat translucent modes.
        // see {https://github.com/jgilfelt/SystemBarTint}
//        if (isTranslucentStatusBar() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            setStatusBarTint(darkenColor(primaryColor));
//        }
    }

    /**
     * darken color
     */
    protected int darkenColor(int color) {
        if (color == primaryColor) {
            return primaryDarkColor;
        } else {
            float[] hsv = new float[3];
            Color.colorToHSV(color, hsv);
            hsv[2] *= 0.8f; // value component
            return Color.HSVToColor(hsv);
        }
    }

    /**
     * set the statusBar tint
     */
    protected void setStatusBarTint(int primaryDarkColor) {
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintColor(primaryDarkColor);
    }

    @Override
    public void setTitle(CharSequence title) {
        if (toolbarTitle != null) {
            toolbarTitle.setText(title);
        } else {
            super.setTitle(title);
        }
    }

    @Override
    public final Observable<ActivityEvent> lifecycle() {
        return lifecycleSubject.asObservable();
    }

    @Nonnull
    @Override
    public <T> LifecycleTransformer<T> bindUntilEvent(@Nonnull ActivityEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }

    @Nonnull
    @Override
    public <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycle.bind(lifecycleSubject);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, getClass().getSimpleName() + ".onResume...");
        Presenter presenter = getPresenter();
        if (presenter != null) {
            presenter.resume();
        }
        lifecycleSubject.onNext(ActivityEvent.RESUME);
    }

    @Override
    protected void onPause() {
        lifecycleSubject.onNext(ActivityEvent.PAUSE);
        super.onPause();
        Log.d(TAG, getClass().getSimpleName() + ".onPause...");
        Presenter presenter = getPresenter();
        if (presenter != null) {
            presenter.pause();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        lifecycleSubject.onNext(ActivityEvent.START);
        Log.d(TAG, getClass().getSimpleName() + ".onStart...");
    }

    @Override
    protected void onStop() {
        lifecycleSubject.onNext(ActivityEvent.STOP);
        super.onStop();
        Log.d(TAG, getClass().getSimpleName() + ".onStop...");
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, getClass().getSimpleName() + ".onDestroy...");
        lifecycleSubject.onNext(ActivityEvent.DESTROY);
        super.onDestroy();

        Presenter presenter = getPresenter();
        if (presenter != null) {
            presenter.destroy();
        }
    }

    public abstract Presenter getPresenter();

    public void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commitAllowingStateLoss();
    }

    public Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.container);
    }

    /**
     * Get intent extra
     *
     * @param name
     * @return parcelable
     */
    @SuppressWarnings("unchecked")
    protected <V extends Parcelable> V getParcelableExtra(final String name) {
        return (V) getIntent().getParcelableExtra(name);
    }

    /**
     * Get intent extra
     *
     * @param name
     * @return int
     */
    protected int getIntExtra(final String name) {
        return getIntent().getIntExtra(name, -1);
    }

    /**
     * Get intent extra
     *
     * @param name
     * @return int array
     */
    protected int[] getIntArrayExtra(final String name) {
        return getIntent().getIntArrayExtra(name);
    }

    /**
     * Get intent extra
     *
     * @param name
     * @return boolean array
     */
    protected boolean[] getBooleanArrayExtra(final String name) {
        return getIntent().getBooleanArrayExtra(name);
    }

    /**
     * Get intent extra
     *
     * @param name
     * @return string
     */
    protected String getStringExtra(final String name) {
        return getIntent().getStringExtra(name);
    }

    /**
     * Get intent extra
     *
     * @param name
     * @return string array
     */
    protected String[] getStringArrayExtra(final String name) {
        return getIntent().getStringArrayExtra(name);
    }

    /**
     * Get intent extra
     *
     * @param name
     * @return char sequence array
     */
    protected CharSequence[] getCharSequenceArrayExtra(final String name) {
        return getIntent().getCharSequenceArrayExtra(name);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean isShowHomeAsUp() {
        return showHomeAsUp;
    }

    public boolean isTranslucentStatusBar() {
        return statusBarTranslucent;
    }
}
