package com.khayal.mvi

import androidx.lifecycle.ViewModel

abstract class BaseViewModel<T : BaseIntent> : ViewModel() {
    private var _event: T? = null

    protected val event: T?
        get() = _event

    abstract fun triggerIntent(intent: T)
}
