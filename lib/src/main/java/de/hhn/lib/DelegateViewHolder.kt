package de.hhn.lib

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

open class DelegateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    constructor(
        parent: ViewGroup,
        @LayoutRes layout: Int
    ) : this(LayoutInflater.from(parent.context).inflate(layout, parent, false))

    open fun onViewAttachedToWindow() {}

    open fun onViewDetachedFromWindow() {}

    open fun onViewRecycled() {}

    open fun onFailedToRecycleView(): Boolean = false
}