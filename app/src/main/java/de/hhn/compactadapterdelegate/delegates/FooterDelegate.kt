package de.hhn.compactadapterdelegate.delegates

import de.hhn.compactadapterdelegate.PageModel
import de.hhn.compactadapterdelegate.R
import de.hhn.compactadapterdelegate.databinding.FooterBinding
import de.hhn.compactadapterdelegate.lib.Delegate
import de.hhn.compactadapterdelegate.lib.DelegateModel
import de.hhn.compactadapterdelegate.lib.DelegateViewHolder

class FooterDelegate : Delegate<PageModel.Footer>(R.layout.footer) {

    override fun bind(
        viewHolder: DelegateViewHolder,
        item: DelegateModel<PageModel.Footer>,
        payloads: MutableList<Any>?
    ) {
        val binding = FooterBinding.bind(viewHolder.itemView)

        binding.text1.text = item.model.text
        binding.text2.text = "${item.model.text} +++"
    }
}