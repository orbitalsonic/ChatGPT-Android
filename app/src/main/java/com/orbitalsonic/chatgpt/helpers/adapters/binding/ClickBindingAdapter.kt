package com.orbitalsonic.chatgpt.helpers.adapters.binding

import android.view.View
import androidx.databinding.BindingAdapter
import com.orbitalsonic.chatgpt.helpers.listeners.DebounceListener.setDebounceClickListener

@BindingAdapter("debounceClick")
fun setDebouncedClick(view: View, debounceClick: () -> Unit) {
    view.setDebounceClickListener {
        debounceClick.invoke()
    }
}