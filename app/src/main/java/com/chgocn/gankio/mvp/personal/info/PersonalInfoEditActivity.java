package com.chgocn.gankio.mvp.personal.info;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chgocn.gankio.mvp.R;
import com.chgocn.lib.activity.BaseActivity;
import com.chgocn.lib.presenter.Presenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by chgocn.
 */
public class PersonalInfoEditActivity extends BaseActivity {

    @Inject
    PersonalInfoEditPresenter presenter;
    @BindView(R.id.list)
    RecyclerView infoEditList;

    InfoEditRecyclerAdapter adapter;

    public static Intent createIntent(Context context) {
        return new Intent(context, PersonalInfoEditActivity.class);
    }

    @Override
    public void initInjector() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_personal_info_edit;
    }

    @Override
    public Presenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        infoEditList.setLayoutManager(new LinearLayoutManager(this));
        //adapter = new PersonalInfoEditListAdapter(this, mockInfoList());
        adapter = new InfoEditRecyclerAdapter(this, mockInfoList());
        infoEditList.setAdapter(adapter);
    }

    private List<PersonalInfoItem> mockInfoList() {
        List<PersonalInfoItem> dataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            PersonalInfoItem personalInfoItem = new PersonalInfoItem();
            if (i >= 5 && i < 8) {
                personalInfoItem.setViewType(InfoEditRecyclerAdapter.TYPE_TWO);
            } else {
                personalInfoItem.setViewType(InfoEditRecyclerAdapter.TYPE_ONE);
            }
            dataList.add(personalInfoItem);
        }
        return dataList;
    }
}
