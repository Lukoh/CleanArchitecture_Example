package com.goforer.base.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.goforer.base.presentation.view.activity.BaseActivity;

import butterknife.ButterKnife;

public class BaseFragment extends Fragment {
    protected BaseActivity mActivity;
    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mActivity = (BaseActivity)context;
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @SuppressWarnings("unused")
    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    public Context getContext() {
        return mContext;
    }
}
