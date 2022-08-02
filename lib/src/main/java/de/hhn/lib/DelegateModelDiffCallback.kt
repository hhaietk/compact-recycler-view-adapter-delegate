package de.hhn.lib

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class DelegateModelDiffCallback<M> : DiffUtil.ItemCallback<DelegateModel<M>>() {

    override fun areItemsTheSame(oldItem: DelegateModel<M>, newItem: DelegateModel<M>): Boolean {
        return oldItem.uuid == newItem.uuid
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: DelegateModel<M>, newItem: DelegateModel<M>): Boolean {
        return oldItem.model == newItem.model
    }

    override fun getChangePayload(oldItem: DelegateModel<M>, newItem: DelegateModel<M>): Any? {
        return oldItem.changedPayload(newItem)
    }
}