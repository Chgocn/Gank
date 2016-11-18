package com.chgocn.gankio.mvp.login.view.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.chgocn.gankio.mvp.main.domain.UseCase;
import com.chgocn.gankio.mvp.login.data.repository.LoginRepository;
import com.chgocn.gankio.mvp.login.domain.AuthAccount;
import com.chgocn.gankio.mvp.util.Tools;
import com.chgocn.lib.presenter.PresenterImpl;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;

import static android.content.ContentValues.TAG;

/**
 * Created by chgocn.
 */
public class LoginPresenter extends PresenterImpl<LoginPresenter.View> {

    @Inject
    public LoginRepository repository;

    // TODO: Add your useCase
    //private UseCase<AuthAccount> useCase;

    private View mView;

    @Inject
    public LoginPresenter() {

    }

    @Override
    public void create() {

    }

    @Override
    public void setView(View view) {
        this.mView = view;
    }


    public interface View {
        //TODO: Create methods to implements in Activity or Fragment
        void showProgress();

        void hideProgress();

        void showLoginSuccessTip();

        void showLoginFailedTip();

        void showPhoneUnLeagalTip();

    }

    public void validateCredentials(String phone, String pwd) {

        if (inputLeagal(phone,pwd)) {
            mView.showProgress();
            new UseCase<AuthAccount>(){

                @Override
                public Observable buildUseCase() {
                    return repository.userLogin(phone, Tools.getInstance().getMD5(pwd));
                }
            }.execute(new Action1<AuthAccount>() {
                @Override
                public void call(AuthAccount authAccount) {
                    mView.hideProgress();
                    mView.showLoginSuccessTip();
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    Log.e(TAG, "call: throwable" + throwable.getMessage() + "\n");
                    throwable.printStackTrace();
                    mView.hideProgress();
                    mView.showLoginFailedTip();
                }
            });
        }
    }

    public boolean inputLeagal(String phone, String pwd){
        if (TextUtils.isEmpty(phone)) {
            mView.showPhoneUnLeagalTip();
            return false;
        }
        if (TextUtils.isEmpty(pwd)) {
            mView.showPhoneUnLeagalTip();
            return false;
        }
        return true;
    }

}
