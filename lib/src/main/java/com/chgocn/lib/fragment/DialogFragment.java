package com.chgocn.lib.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.StringRes;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.chgocn.lib.view.DialogResultListener;

/**
 * Created by chgocn.
 */

public abstract class DialogFragment extends BaseFragment implements DialogResultListener{

    /**
     * View finder bound to the value last specified to
     * {@link #onViewCreated(View, Bundle)}
     */
    private MaterialDialog progressDialog;

    /**
     * Is this fragment usable from the UI-thread
     *
     * @return true if usable, false otherwise
     */
    protected boolean isUsable() {
        return getActivity() != null;
    }

    @Override
    public void onDialogResult(int requestCode, int resultCode, Bundle arguments) {
        // Intentionally left blank
    }

    /**
     * Get serializable extra from activity's intent
     *
     * @param name
     * @return extra
     */
    @SuppressWarnings("unchecked")
    protected <V extends Parcelable> V getParcelableExtra(final String name) {
        Activity activity = getActivity();
        if (activity != null) {
            return (V) activity.getIntent().getParcelableExtra(name);
        }else {
            return null;
        }
    }

    /**
     * Get string extra from activity's intent
     *
     * @param name
     * @return extra
     */
    protected String getStringExtra(final String name) {
        Activity activity = getActivity();
        if (activity != null) {
            return activity.getIntent().getStringExtra(name);
        } else {
            return null;
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    /**
     * Dismiss and clear progress dialog field
     */
    protected void dismissProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    /**
     * Show indeterminate progress dialog with given message
     *
     * @param message
     */
    protected void showProgressIndeterminate(final CharSequence message) {
        dismissProgress();
        progressDialog = new MaterialDialog.Builder(getActivity())
                .content(message)
                .progress(true, 0)
                .build();
        progressDialog.show();
    }

    /**
     * Show indeterminate progress dialog with given message
     *
     * @param resId
     */
    protected void showProgressIndeterminate(@StringRes final int resId) {
        dismissProgress();
        progressDialog = new MaterialDialog.Builder(getActivity())
                .content(resId)
                .progress(true, 0)
                .build();
        progressDialog.show();
    }
}
