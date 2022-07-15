package de.hhn.compactadapterdelegate.lib

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class DelegateModelDiffCallback : DiffUtil.ItemCallback<DelegateModel<*>>() {

    override fun areItemsTheSame(oldItem: DelegateModel<*>, newItem: DelegateModel<*>): Boolean {
        return oldItem.uuid == newItem.uuid
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: DelegateModel<*>, newItem: DelegateModel<*>): Boolean {
        return oldItem.model == newItem.model
    }

    override fun getChangePayload(oldItem: DelegateModel<*>, newItem: DelegateModel<*>): Any? {
        return oldItem.changedPayload(newItem)
    }
}