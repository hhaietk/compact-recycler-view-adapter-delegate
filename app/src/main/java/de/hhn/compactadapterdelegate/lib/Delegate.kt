package de.hhn.compactadapterdelegate.lib

import android.view.ViewGroup
import androidx.annotation.LayoutRes

abstract class Delegate<I>(@LayoutRes val layout: Int) {

    open fun createViewHolder(parent: ViewGroup) = DelegateViewHolder(parent, layout)

    abstract fun bind(
        viewHolder: DelegateViewHolder,
        item: DelegateModel<I>,
        payloads: MutableList<Any>?
    )

    @Suppress("UNCHECKED_CAST")
    fun bindViewHolder(
        viewHolder: DelegateViewHolder,
        item: DelegateModel<*>,
        payloads: MutableList<Any>?
    ) {
        bind(viewHolder, item as DelegateModel<I>, payloads)
    }
}