package com.coder.vincent.smart_toast.alias.emotion

import androidx.annotation.StringRes

interface EmotionToastApi {
    fun info(msg: CharSequence)
    fun info(@StringRes msg: Int)
    fun infoLong(msg: CharSequence)
    fun infoLong(@StringRes msg: Int)
    fun warning(msg: CharSequence)
    fun warning(@StringRes msg: Int)
    fun warningLong(msg: CharSequence)
    fun warningLong(@StringRes msg: Int)
    fun success(msg: CharSequence)
    fun success(@StringRes msg: Int)
    fun successLong(msg: CharSequence)
    fun successLong(@StringRes msg: Int)
    fun error(msg: CharSequence)
    fun error(@StringRes msg: Int)
    fun errorLong(msg: CharSequence)
    fun errorLong(@StringRes msg: Int)
    fun fail(msg: CharSequence)
    fun fail(@StringRes msg: Int)
    fun failLong(msg: CharSequence)
    fun failLong(@StringRes msg: Int)
    fun complete(msg: CharSequence)
    fun complete(@StringRes msg: Int)
    fun completeLong(msg: CharSequence)
    fun completeLong(@StringRes msg: Int)
    fun forbid(msg: CharSequence)
    fun forbid(@StringRes msg: Int)
    fun forbidLong(msg: CharSequence)
    fun forbidLong(@StringRes msg: Int)
    fun waiting(msg: CharSequence)
    fun waiting(@StringRes msg: Int)
    fun waitingLong(msg: CharSequence)
    fun waitingLong(@StringRes msg: Int)
}