package de.hhn.compactadapterdelegate.delegates

import android.view.View
import de.hhn.compactadapterdelegate.PageModel
import de.hhn.compactadapterdelegate.R
import de.hhn.compactadapterdelegate.databinding.ProductBinding
import de.hhn.compactadapterdelegate.lib.AdapterDelegate
import de.hhn.compactadapterdelegate.lib.DelegateModel
import de.hhn.compactadapterdelegate.lib.DelegateViewHolder

class ProductDelegate : AdapterDelegate<PageModel.Product>(R.layout.product) {

    override fun bind(
        viewHolder: DelegateViewHolder,
        item: DelegateModel<PageModel.Product>,
        payloads: MutableList<Any>?
    ) {
        val binding = ProductBinding.bind(viewHolder.itemView)
        binding.name.text = item.model.name
        binding.cost.text = item.model.cost.toString()
    }
}