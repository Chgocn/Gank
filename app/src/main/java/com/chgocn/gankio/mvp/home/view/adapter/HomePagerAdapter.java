package com.chgocn.gankio.mvp.home.view.adapter;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.chgocn.gankio.mvp.R;
import com.chgocn.gankio.mvp.homepager.view.fragment.HomePagerFragment;
import com.chgocn.lib.adapter.FragmentPagerAdapter;

/**
 * Created by chgocn.
 */
public class HomePagerAdapter extends FragmentPagerAdapter {
    private final Resources resources;

    private Fragment[] fragmentArray = new Fragment[]{HomePagerFragment.newInstance("all"),HomePagerFragment.newInstance("Android"),HomePagerFragment.newInstance("福利"),HomePagerFragment.newInstance("iOS")};
    private int[] titleArray = new int[]{R.string.home,R.string.android, R.string.welfare, R.string.ios};

    private final FragmentManager fragmentManager;

    public HomePagerAdapter(Fragment fragment) {
        super(fragment);
        fragmentManager = fragment.getChildFragmentManager();
        resources = fragment.getResources();
    }

    private SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();

    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    @Override
    public int getCount() {
        return fragmentArray.length;
    }

    @Override
    public Fragment getItem(int position) {
        try{
            return fragmentArray[position];
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        //return position < fragmentArray.length ? fragmentArray[position] : null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return position < titleArray.length ? resources.getString(titleArray[position]) : null;
    }
}
