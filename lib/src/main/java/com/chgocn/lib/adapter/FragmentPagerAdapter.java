package com.chgocn.lib.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.ViewGroup;

import com.chgocn.lib.fragment.FragmentProvider;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Pager adapter that provides the current fragment
 *
 * @author chgocn (chgocn@gmail.com)
 */
public abstract class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter implements FragmentProvider {

    private final FragmentActivity activity;

    private Fragment selected;

    /**
     * @param activity
     */
    public FragmentPagerAdapter(FragmentActivity activity) {
        super(activity.getSupportFragmentManager());

        this.activity = activity;
    }

    /**
     * @param fragment
     */
    public FragmentPagerAdapter(Fragment fragment) {
        super(fragment.getChildFragmentManager());

        this.activity = fragment.getActivity();
    }

    @Override
    public Fragment getSelected() {
        return selected;
    }

    @Override
    public void setPrimaryItem(final ViewGroup container, final int position, final Object object) {
        super.setPrimaryItem(container, position, object);

        boolean changed = false;
        if (object instanceof Fragment) {
            changed = object != selected;
            selected = (Fragment) object;
        } else {
            changed = object != null;
            selected = null;
        }

        if (changed) {
            activity.invalidateOptionsMenu();
        }
    }

    // [+] save status
    private List<WeakReference<Fragment>> fragmentList = new ArrayList<>();

    public List<WeakReference<Fragment>> getFragments() {
        for (int i = fragmentList.size() - 1; i >= 0; --i) {
            if (null == fragmentList.get(i).get()) {
                fragmentList.remove(i);
            }
        }
        return fragmentList;
    }

    protected void saveFragment(Fragment fragment) {
        if (fragment == null) {
            return;
        }

        for (WeakReference<Fragment> item : fragmentList) {
            if (item.get() == fragment) {
                return;
            }
        }

        fragmentList.add(new WeakReference<>(fragment));
    }
    // [-] save status

}

