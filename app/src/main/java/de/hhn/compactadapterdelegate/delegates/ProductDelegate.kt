package de.hhn.compactadapterdelegate.delegates

import de.hhn.compactadapterdelegate.PageModel
import de.hhn.compactadapterdelegate.R
import de.hhn.compactadapterdelegate.databinding.ProductBinding
import de.hhn.lib.Delegate
import de.hhn.lib.DelegateModel
import de.hhn.lib.DelegateViewHolder

class ProductDelegate : Delegate<PageModel.Product>(R.layout.product) {

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