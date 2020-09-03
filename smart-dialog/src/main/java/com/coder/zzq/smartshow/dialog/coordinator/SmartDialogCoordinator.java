package com.coder.zzq.smartshow.dialog.coordinator;

import android.util.SparseArray;

import com.coder.zzq.smartshow.dialog.base.SmartDialog;

public final class SmartDialogCoordinator {
    private static SparseArray<SmartDialog> sSmartDialogs;

    private static SparseArray<SmartDialog> getSmartDialogs() {
        if (sSmartDialogs == null) {
            sSmartDialogs = new SparseArray<>();
        }
        return sSmartDialogs;
    }

    public static void store(String identity, SmartDialog smartDialog) {
        getSmartDialogs().put(identity.hashCode(), smartDialog);
    }

    public static SmartDialog retrieve(String identity) {
        return isEmpty() ? null : getSmartDialogs().get(identity.hashCode());
    }

    public static void delete(String identity) {
        if (!isEmpty()) {
            getSmartDialogs().delete(identity.hashCode());
        }
    }


    public static boolean isEmpty() {
        return sSmartDialogs == null || sSmartDialogs.size() == 0;
    }
}
