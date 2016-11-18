package com.chgocn.gankio.mvp.login.view.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.chgocn.gankio.mvp.R;
import com.chgocn.gankio.mvp.login.inject.ActivityModule;
import com.chgocn.gankio.mvp.login.inject.DaggerActivityComponent;
import com.chgocn.gankio.mvp.login.view.presenter.LoginPresenter;
import com.chgocn.lib.activity.BaseActivity;
import com.chgocn.lib.presenter.Presenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by chgocn.
 */
public class LoginActivity extends BaseActivity implements LoginPresenter.View {

    @Inject
    public LoginPresenter mPresenter;
    @BindView(R.id.edtName) EditText name;
    @BindView(R.id.edtPwd) EditText pwd;



    @BindView(R.id.btnSure) Button sure;
    @OnClick(R.id.btnSure)
    public void submit() {
        mPresenter.validateCredentials(getPhoneText(),getPwdText());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        name.setText("15000000002");
        pwd.setText("123456");
        mPresenter.create();
    }

    @Override
    public void initInjector() {
        DaggerActivityComponent.builder().activityModule(new ActivityModule(this)).build().inject(this);
    }

    @Override
    public Presenter getPresenter() {
        return mPresenter;
    }

    @Override
    public int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    public String getPhoneText() {
        return name.getText().toString();
    }

    public String getPwdText() {
        return pwd.getText().toString();
    }

    @Override
    public void showLoginSuccessTip() {
        Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoginFailedTip() {
        Toast.makeText(this, "登陆失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showPhoneUnLeagalTip() {
        Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
    }
}