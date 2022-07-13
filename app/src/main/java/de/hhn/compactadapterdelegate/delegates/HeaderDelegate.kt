package de.hhn.compactadapterdelegate.delegates

import de.hhn.compactadapterdelegate.PageModel
import de.hhn.compactadapterdelegate.R
import de.hhn.compactadapterdelegate.databinding.HeaderBinding
import de.hhn.compactadapterdelegate.lib.AdapterDelegate
import de.hhn.compactadapterdelegate.lib.DelegateModel
import de.hhn.compactadapterdelegate.lib.DelegateViewHolder

class HeaderDelegate : AdapterDelegate<PageModel.Header>(R.layout.header) {

    override fun bind(
        viewHolder: DelegateViewHolder,
        item: DelegateModel<PageModel.Header>,
        payloads: MutableList<Any>?
    ) {
        val binding = HeaderBinding.bind(viewHolder.itemView)
        binding.title.text = item.model.title
    }
}