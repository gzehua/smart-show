package com.coder.zzq.smartshow.dialog.delegate;

import com.coder.zzq.smartshow.dialog.data_item.DataItem;

public interface Delegate {
    void onDataChanged(DataItem dataItem);

    void destroy();
}
