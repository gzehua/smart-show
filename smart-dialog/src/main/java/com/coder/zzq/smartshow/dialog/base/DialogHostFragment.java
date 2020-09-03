package com.coder.zzq.smartshow.dialog.base;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.coder.zzq.smartshow.dialog.coordinator.SmartDialogCoordinator;

public class DialogHostFragment extends Fragment {
    private static final String ARGUMENT_IDENTITY = "identity";

    private String mIdentity;
    private SmartDialog mSmartDialog;


    public DialogHostFragment() {

    }

    public static void addInstance(FragmentManager lifecycleBinder, String businessTag, String identity) {
        DialogHostFragment fragment = new DialogHostFragment();
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_IDENTITY, identity);
        fragment.setArguments(arguments);
        lifecycleBinder.beginTransaction()
                .add(fragment, businessTag)
                .commitNow();
    }

    public String getIdentity() {
        return getArguments().getString(ARGUMENT_IDENTITY);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIdentity = getIdentity();
        mSmartDialog = SmartDialogCoordinator.retrieve(mIdentity);
        mSmartDialog.getDialogProvider().buildNestedDialog(getActivity(), savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        mSmartDialog.getDialogProvider().onHostStart();
    }


    @Override
    public void onStop() {
        super.onStop();
        mSmartDialog.getDialogProvider().onHostStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mSmartDialog.getDialogProvider().onHostSaveInstanceState(outState);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mSmartDialog.getDialogProvider().onHostDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mSmartDialog.getDialogProvider().onHostDestroyed(getIdentity())) {
            mSmartDialog.setDialogProvider(null);
            mSmartDialog = null;
            mIdentity = null;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mSmartDialog.getDialogProvider().onConfigurationChanged(newConfig);
    }
}
