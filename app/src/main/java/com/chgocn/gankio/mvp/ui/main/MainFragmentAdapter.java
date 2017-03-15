package com.chgocn.gankio.mvp.ui.main;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.ViewGroup;

import com.chgocn.gankio.mvp.R;
import com.chgocn.gankio.mvp.ui.explore.ExploreFragment;
import com.chgocn.gankio.mvp.ui.home.HomeFragment;
import com.chgocn.gankio.mvp.ui.personal.PersonalFragment;
import com.chgocn.lib.adapter.FragmentPagerAdapter;

/**
 * Created by chgocn.
 */

public class MainFragmentAdapter extends FragmentPagerAdapter{

    private final Resources resources;

    private Class[] fragmentArray = new Class[]{HomeFragment.class, ExploreFragment.class, PersonalFragment.class};
    private int[] titleArray = new int[]{R.string.home, R.string.explore, R.string.perm_identity};

    /**
     * Create pager adapter
     *
     * @param activity
     */
    public MainFragmentAdapter(FragmentActivity activity) {
        super(activity);

        resources = activity.getResources();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public int getCount() {
        return fragmentArray.length;
    }

    @Override
    public Fragment getItem(int position) {
        try{
            return (Fragment)fragmentArray[position].newInstance();
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
