package com.coder.zzq.smartshow.dialog.input;

public interface InputCheckCallback {
    boolean isValid(String input);

    String errorInfoWhenInputInvalid();
}
