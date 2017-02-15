package com.chgocn.lib.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;
import com.chgocn.lib.R;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * ref:https://github.com/youth5201314/banner
 * Created by chgocn.
 */

public class BannerPager extends FrameLayout implements android.support.v4.view.ViewPager.OnPageChangeListener{


    public String tag = "banner";
    private int mIndicatorMargin = BannerConfig.PADDING_SIZE;
    private int mIndicatorWidth;
    private int mIndicatorHeight;
    private int indicatorSize;
    private int bannerStyle = BannerConfig.CIRCLE_INDICATOR;
    private int delayTime = BannerConfig.TIME;
    private int scrollTime = BannerConfig.DURATION;
    private boolean isAutoPlay = BannerConfig.IS_AUTO_PLAY;
    private boolean isScroll = BannerConfig.IS_SCROLL;
    private int mIndicatorSelectedResId = R.drawable.gray_radius;
    private int mIndicatorUnselectedResId = R.drawable.white_radius;
    private int titleHeight;
    private int titleBackground;
    private int titleTextColor;
    private int titleTextSize;
    private int count = 0;
    private int currentItem;
    private int gravity = -1;
    private int lastPosition = 1;
    private int scaleType = 1;
    private List<String> titles;
    private List imageUrls;
    private List<View> imageViews;
    private List<ImageView> indicatorImages;
    private Context context;
    private ViewPager viewPager;
    private TextView bannerTitle, numIndicatorInside, numIndicator;
    private LinearLayout indicator, indicatorInside, titleView;
    private ImageLoaderInterface imageLoader;
    private BannerPagerAdapter adapter;
    private android.support.v4.view.ViewPager.OnPageChangeListener mOnPageChangeListener;
    private BannerScroller mScroller;
    private OnBannerClickListener listener;
    private DisplayMetrics dm;
    private WeakHandler handler = new WeakHandler();

    public BannerPager(Context context) {
        this(context, null);
    }

    public BannerPager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerPager(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        titles = new ArrayList<>();
        imageUrls = new ArrayList<>();
        imageViews = new ArrayList<>();
        indicatorImages = new ArrayList<>();
        dm = context.getResources().getDisplayMetrics();
        indicatorSize = dm.widthPixels / 80;
        initView(context, attrs);
    }

    private void handleTypedArray(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Banner);
        mIndicatorWidth = typedArray.getDimensionPixelSize(R.styleable.Banner_indicator_width, indicatorSize);
        mIndicatorHeight = typedArray.getDimensionPixelSize(R.styleable.Banner_indicator_height, indicatorSize);
        mIndicatorMargin = typedArray.getDimensionPixelSize(R.styleable.Banner_indicator_margin, BannerConfig.PADDING_SIZE);
        mIndicatorSelectedResId = typedArray.getResourceId(R.styleable.Banner_indicator_drawable_selected, R.drawable.gray_radius);
        mIndicatorUnselectedResId = typedArray.getResourceId(R.styleable.Banner_indicator_drawable_unselected, R.drawable.white_radius);
        scaleType = typedArray.getInt(R.styleable.Banner_image_scale_type, scaleType);
        delayTime = typedArray.getInt(R.styleable.Banner_delay_time, BannerConfig.TIME);
        scrollTime = typedArray.getInt(R.styleable.Banner_scroll_time, BannerConfig.DURATION);
        isAutoPlay = typedArray.getBoolean(R.styleable.Banner_is_auto_play, BannerConfig.IS_AUTO_PLAY);
        titleBackground = typedArray.getColor(R.styleable.Banner_title_background, BannerConfig.TITLE_BACKGROUND);
        titleHeight = typedArray.getDimensionPixelSize(R.styleable.Banner_title_height, BannerConfig.TITLE_HEIGHT);
        titleTextColor = typedArray.getColor(R.styleable.Banner_title_textcolor, BannerConfig.TITLE_TEXT_COLOR);
        titleTextSize = typedArray.getDimensionPixelSize(R.styleable.Banner_title_textsize, BannerConfig.TITLE_TEXT_SIZE);
        typedArray.recycle();
    }

    private void initView(Context context, AttributeSet attrs) {
        imageViews.clear();
        View view = LayoutInflater.from(context).inflate(R.layout.banner_pager, this, true);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        titleView = (LinearLayout) view.findViewById(R.id.titleView);
        indicator = (LinearLayout) view.findViewById(R.id.indicator);
        indicatorInside = (LinearLayout) view.findViewById(R.id.indicatorInside);
        bannerTitle = (TextView) view.findViewById(R.id.bannerTitle);
        numIndicator = (TextView) view.findViewById(R.id.numIndicator);
        numIndicatorInside = (TextView) view.findViewById(R.id.numIndicatorInside);
        handleTypedArray(context, attrs);
        initViewPagerScroll();
    }

    private void initViewPagerScroll() {
        try {
            Field mField = ViewPager.class.getDeclaredField("mScroller");
            mField.setAccessible(true);
            mScroller = new BannerScroller(viewPager.getContext());
            mScroller.setDuration(scrollTime);
            mField.set(viewPager, mScroller);
        } catch (Exception e) {
            Log.e(tag, e.getMessage());
        }
    }

    public BannerPager isAutoPlay(boolean isAutoPlay) {
        this.isAutoPlay = isAutoPlay;
        return this;
    }

    public BannerPager setImageLoader(ImageLoaderInterface imageLoader) {
        this.imageLoader = imageLoader;
        return this;
    }

    public BannerPager setDelayTime(int delayTime) {
        this.delayTime = delayTime;
        return this;
    }

    public BannerPager setIndicatorGravity(int type) {
        switch (type) {
            case BannerConfig.LEFT:
                this.gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
                break;
            case BannerConfig.CENTER:
                this.gravity = Gravity.CENTER;
                break;
            case BannerConfig.RIGHT:
                this.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
                break;
        }
        return this;
    }

    public BannerPager setBannerAnimation(Class<? extends android.support.v4.view.ViewPager.PageTransformer> transformer) {
        try {
            setPageTransformer(true, transformer.newInstance());
        } catch (Exception e) {
            Log.e(tag, "Please set the PageTransformer class");
        }
        return this;
    }

    public BannerPager setBannerAnimation(android.support.v4.view.ViewPager.PageTransformer transformer) {
        try {
            setPageTransformer(true, transformer);
        } catch (Exception e) {
            Log.e(tag, "Please set the PageTransformer class");
        }
        return this;
    }

    /**
     * Set the number of pages that should be retained to either side of the
     * current page in the view hierarchy in an idle state. Pages beyond this
     * limit will be recreated from the adapter when needed.
     *
     * @param limit How many pages will be kept offscreen in an idle state.
     * @return Banner
     */
    public BannerPager setOffscreenPageLimit(int limit) {
        if (viewPager != null) {
            viewPager.setOffscreenPageLimit(limit);
        }
        return this;
    }

    /**
     * Set a {@link android.support.v4.view.ViewPager.PageTransformer} that will be called for each attached page whenever
     * the scroll position is changed. This allows the application to apply custom property
     * transformations to each page, overriding the default sliding look and feel.
     *
     * @param reverseDrawingOrder true if the supplied PageTransformer requires page views
     *                            to be drawn from last to first instead of first to last.
     * @param transformer         PageTransformer that will modify each page's animation properties
     * @return Banner
     */
    public BannerPager setPageTransformer(boolean reverseDrawingOrder, android.support.v4.view.ViewPager.PageTransformer transformer) {
        viewPager.setPageTransformer(reverseDrawingOrder, transformer);
        return this;
    }

    public BannerPager setBannerTitles(List<String> titles) {
        this.titles = titles;
        return this;
    }

    public BannerPager setBannerStyle(int bannerStyle) {
        this.bannerStyle = bannerStyle;
        return this;
    }

    public BannerPager setViewPagerIsScroll(boolean isScroll) {
        this.isScroll = isScroll;
        return this;
    }

    public BannerPager setImages(List<?> imageUrls) {
        this.imageUrls = imageUrls;
        this.count = imageUrls.size();
        return this;
    }

    public void update(List<?> imageUrls, List<String> titles) {
        this.imageUrls.clear();
        this.titles.clear();
        this.imageUrls.addAll(imageUrls);
        this.titles.addAll(titles);
        this.count = this.imageUrls.size();
        start();
    }

    public void update(List<?> imageUrls) {
        this.imageUrls.clear();
        this.imageUrls.addAll(imageUrls);
        this.count = this.imageUrls.size();
        start();
    }

    public void updateBannerStyle(int bannerStyle) {
        indicator.setVisibility(GONE);
        numIndicator.setVisibility(GONE);
        numIndicatorInside.setVisibility(GONE);
        indicatorInside.setVisibility(GONE);
        bannerTitle.setVisibility(View.GONE);
        titleView.setVisibility(View.GONE);
        this.bannerStyle = bannerStyle;
        start();
    }

    public BannerPager start() {
        setBannerStyleUI();
        setImageList(imageUrls);
        if (isAutoPlay)
            startAutoPlay();
        return this;
    }

    private void setTitleStyleUI() {
        if (titles.size() != imageUrls.size()) {
            throw new RuntimeException("[Banner] --> The number of titles and images is different");
        }
        if (titleBackground != -1) {
            titleView.setBackgroundColor(titleBackground);
        }
        if (titleHeight != -1) {
            titleView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, titleHeight));
        }
        if (titleTextColor != -1) {
            bannerTitle.setTextColor(titleTextColor);
        }
        if (titleTextSize != -1) {
            bannerTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize);
        }
        if (titles != null && titles.size() > 0) {
            bannerTitle.setText(titles.get(0));
            bannerTitle.setVisibility(View.VISIBLE);
            titleView.setVisibility(View.VISIBLE);
        }
    }

    private void setBannerStyleUI() {
        int visibility;
        if (count > 1) visibility = View.VISIBLE;
        else visibility = View.GONE;
        switch (bannerStyle) {
            case BannerConfig.CIRCLE_INDICATOR:
                indicator.setVisibility(visibility);
                break;
            case BannerConfig.NUM_INDICATOR:
                numIndicator.setVisibility(visibility);
                break;
            case BannerConfig.NUM_INDICATOR_TITLE:
                numIndicatorInside.setVisibility(visibility);
                setTitleStyleUI();
                break;
            case BannerConfig.CIRCLE_INDICATOR_TITLE:
                indicator.setVisibility(visibility);
                setTitleStyleUI();
                break;
            case BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE:
                indicatorInside.setVisibility(visibility);
                setTitleStyleUI();
                break;
        }
    }

    private void initImages() {
        imageViews.clear();
        if (bannerStyle == BannerConfig.CIRCLE_INDICATOR ||
                bannerStyle == BannerConfig.CIRCLE_INDICATOR_TITLE ||
                bannerStyle == BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE) {
            createIndicator();
        } else if (bannerStyle == BannerConfig.NUM_INDICATOR_TITLE) {
            numIndicatorInside.setText("1/" + count);
        } else if (bannerStyle == BannerConfig.NUM_INDICATOR) {
            numIndicator.setText("1/" + count);
        }
    }

    private void setImageList(List<?> imagesUrl) {
        if (imagesUrl == null || imagesUrl.size() <= 0) {
            Log.e(tag, "Please set the images data.");
            return;
        }
        initImages();
        for (int i = 0; i <= count + 1; i++) {
            View imageView = null;
            if (imageLoader != null) {
                imageView = imageLoader.createImageView(context);
            }
            if (imageView == null) {
                imageView = new ImageView(context);
            }
            setScaleType(imageView);
            Object url = null;
            if (i == 0) {
                url = imagesUrl.get(count - 1);
            } else if (i == count + 1) {
                url = imagesUrl.get(0);
            } else {
                url = imagesUrl.get(i - 1);
            }
            imageViews.add(imageView);
            if (imageLoader != null)
                imageLoader.displayImage(context, url, imageView);
            else
                Log.e(tag, "Please set images loader.");
        }
        setData();
    }

    private void setScaleType(View imageView) {
        if (imageView instanceof ImageView) {
            ImageView view = ((ImageView) imageView);
            switch (scaleType) {
                case 0:
                    view.setScaleType(ImageView.ScaleType.CENTER);
                    break;
                case 1:
                    view.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    break;
                case 2:
                    view.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    break;
                case 3:
                    view.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    break;
                case 4:
                    view.setScaleType(ImageView.ScaleType.FIT_END);
                    break;
                case 5:
                    view.setScaleType(ImageView.ScaleType.FIT_START);
                    break;
                case 6:
                    view.setScaleType(ImageView.ScaleType.FIT_XY);
                    break;
                case 7:
                    view.setScaleType(ImageView.ScaleType.MATRIX);
                    break;
            }

        }
    }

    private void createIndicator() {
        indicatorImages.clear();
        indicator.removeAllViews();
        indicatorInside.removeAllViews();
        for (int i = 0; i < count; i++) {
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mIndicatorWidth, mIndicatorHeight);
            params.leftMargin = mIndicatorMargin;
            params.rightMargin = mIndicatorMargin;
            if (i == 0) {
                imageView.setImageResource(mIndicatorSelectedResId);
            } else {
                imageView.setImageResource(mIndicatorUnselectedResId);
            }
            indicatorImages.add(imageView);
            if (bannerStyle == BannerConfig.CIRCLE_INDICATOR ||
                    bannerStyle == BannerConfig.CIRCLE_INDICATOR_TITLE)
                indicator.addView(imageView, params);
            else if (bannerStyle == BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                indicatorInside.addView(imageView, params);
        }
    }


    private void setData() {
        currentItem = 1;
        if (adapter == null) {
            adapter = new BannerPagerAdapter();
        }
        viewPager.setAdapter(adapter);
        viewPager.setFocusable(true);
        viewPager.setCurrentItem(1);
        viewPager.addOnPageChangeListener(this);
        if (gravity != -1)
            indicator.setGravity(gravity);
        if (isScroll && count > 1) {
            viewPager.setScrollable(true);
        } else {
            viewPager.setScrollable(false);
        }
    }


    public void startAutoPlay() {
        handler.removeCallbacks(task);
        handler.postDelayed(task, delayTime);
    }

    public void stopAutoPlay() {
        handler.removeCallbacks(task);
    }

    private final Runnable task = new Runnable() {
        @Override
        public void run() {
            if (count > 1 && isAutoPlay) {
                currentItem = currentItem % (count + 1) + 1;
//                Log.i(tag, "curr:" + currentItem + " count:" + count);
                if (currentItem == 1) {
                    viewPager.setCurrentItem(currentItem, false);
                    handler.post(task);
                } else {
                    viewPager.setCurrentItem(currentItem);
                    handler.postDelayed(task, delayTime);
                }
            }
        }
    };

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Log.i(tag, ev.getAction() + "--" + isAutoPlay);
        if (isAutoPlay) {
            int action = ev.getAction();
            if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL
                    || action == MotionEvent.ACTION_OUTSIDE) {
                startAutoPlay();
            } else if (action == MotionEvent.ACTION_DOWN) {
                stopAutoPlay();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 返回真实的位置
     *
     * @param position
     * @return 下标从0开始
     */
    public int toRealPosition(int position) {
        int realPosition = (position - 1) % count;
        if (realPosition < 0)
            realPosition += count;
        return realPosition;
    }

    class BannerPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            container.addView(imageViews.get(position));
            View view = imageViews.get(position);
            if (listener != null) {
                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /**
                         * 这里不再直接返回position，而是返回图片真实位置，避免越界出现
                         *
                         * 由于以前是偷懒直接返回的position，造成从下标1开始，所以这里现在也不好直接改成从0开始，
                         * 不然以前的使用者都会出问题，所以这里故意+1，希望理解，下次大版本迭代在从0开始，小版本就不改动了
                         */
                        listener.OnBannerClick(toRealPosition(position)+1);
                    }
                });
            }
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (mOnPageChangeListener != null) {
            mOnPageChangeListener.onPageScrollStateChanged(state);
        }
        currentItem = viewPager.getCurrentItem();
        switch (state) {
            case ViewPager.SCROLL_STATE_IDLE://No operation
                if (currentItem == 0) {
                    viewPager.setCurrentItem(count, false);
                } else if (currentItem == count + 1) {
                    viewPager.setCurrentItem(1, false);
                }
                break;
            case ViewPager.SCROLL_STATE_DRAGGING://start Sliding
                if (currentItem == count + 1) {
                    viewPager.setCurrentItem(1, false);
                } else if (currentItem == 0) {
                    viewPager.setCurrentItem(count, false);
                }
                break;
            case ViewPager.SCROLL_STATE_SETTLING://end Sliding
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (mOnPageChangeListener != null) {
            mOnPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }
    }

    @Override
    public void onPageSelected(int position) {
        if (mOnPageChangeListener != null) {
            mOnPageChangeListener.onPageSelected(position);
        }
        if (bannerStyle == BannerConfig.CIRCLE_INDICATOR ||
                bannerStyle == BannerConfig.CIRCLE_INDICATOR_TITLE ||
                bannerStyle == BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE) {
            indicatorImages.get((lastPosition - 1 + count) % count).setImageResource(mIndicatorUnselectedResId);
            indicatorImages.get((position - 1 + count) % count).setImageResource(mIndicatorSelectedResId);
            lastPosition = position;
        }
        if (position == 0) position = count;
        if (position > count) position = 1;
        switch (bannerStyle) {
            case BannerConfig.CIRCLE_INDICATOR:
                break;
            case BannerConfig.NUM_INDICATOR:
                numIndicator.setText(position + "/" + count);
                break;
            case BannerConfig.NUM_INDICATOR_TITLE:
                numIndicatorInside.setText(position + "/" + count);
                bannerTitle.setText(titles.get(position - 1));
                break;
            case BannerConfig.CIRCLE_INDICATOR_TITLE:
                bannerTitle.setText(titles.get(position - 1));
                break;
            case BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE:
                bannerTitle.setText(titles.get(position - 1));
                break;
        }

    }

    public BannerPager setOnBannerClickListener(OnBannerClickListener listener) {
        this.listener = listener;
        return this;
    }

    public void setOnPageChangeListener(android.support.v4.view.ViewPager.OnPageChangeListener onPageChangeListener) {
        mOnPageChangeListener = onPageChangeListener;
    }

    public void releaseBanner() {
//        handler.removeCallbacksAndMessages(null);
//        imageUrls.clear();
//        imageViews.clear();
//        titles.clear();
//        indicatorImages.clear();
//        viewPager.clearAnimation();
//        viewPager=null;
    }

    public interface OnBannerClickListener {
        public void OnBannerClick(int position);
    }

    public class BannerConfig {
        /**
         * indicator style
         */
        public static final int NOT_INDICATOR=0;
        public static final int CIRCLE_INDICATOR=1;
        public static final int NUM_INDICATOR=2;
        public static final int NUM_INDICATOR_TITLE=3;
        public static final int CIRCLE_INDICATOR_TITLE=4;
        public static final int CIRCLE_INDICATOR_TITLE_INSIDE=5;
        /**
         * indicator gravity
         */
        public static final int LEFT=5;
        public static final int CENTER=6;
        public static final int RIGHT=7;

        /**
         * banner
         */
        public static final int PADDING_SIZE=5;
        public static final int TIME=2000;
        public static final int DURATION=800;
        public static final boolean IS_AUTO_PLAY=true;
        public static final boolean IS_SCROLL=true;

        /**
         * title style
         */
        public static final int TITLE_BACKGROUND=-1;
        public static final int TITLE_HEIGHT=-1;
        public static final int TITLE_TEXT_COLOR=-1;
        public static final int TITLE_TEXT_SIZE=-1;

    }

    public class BannerScroller extends Scroller {
        private int mDuration = BannerConfig.DURATION;

        public BannerScroller(Context context) {
            super(context);
        }

        public BannerScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        public BannerScroller(Context context, Interpolator interpolator, boolean flywheel) {
            super(context, interpolator, flywheel);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        public void setDuration(int time) {
            mDuration = time;
        }

    }

    public interface ImageLoaderInterface<T extends View> extends Serializable {

        void displayImage(Context context, Object path, T imageView);

        T createImageView(Context context);
    }

    public abstract class ImageLoader implements ImageLoaderInterface<ImageView> {

        @Override
        public ImageView createImageView(Context context) {
            ImageView imageView = new ImageView(context);
            return imageView;
        }

    }

}
