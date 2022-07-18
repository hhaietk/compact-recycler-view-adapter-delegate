package de.hhn.compactadapterdelegate.lib

import android.view.View
import android.view.ViewGroup
import androidx.annotation.AnyRes
import androidx.recyclerview.widget.RecyclerView

open class DelegateAdapter<I>(vararg delegate: Delegate<out I>) : RecyclerView.Adapter<DelegateViewHolder>() {

    /**
     * Callback for [RecyclerView.ViewHolder.itemView.setOnClickListener].
     *
     * @param item  Model of the adapter.
     * @param view  View that has been clicked.
     * @param position Adapter position of the clicked element.
     */
    protected var onItemClick: ((item: DelegateModel<I>, view: View, position: Int) -> Unit)? = null
    fun onItemClick(block: ((item: DelegateModel<I>, view: View, position: Int) -> Unit)?) {
        onItemClick = block
    }

    /**
     * Callback for [RecyclerView.ViewHolder.itemView.setOnFocusChangeListener].
     *
     * @param item     Model of the adapter.
     * @param view     View that has been clicked.
     * @param hasFocus `True` if it is focused.
     */
    protected var onFocusChange: ((item: DelegateModel<*>, view: View, hasFocus: Boolean, position: Int) -> Unit)? = null
    fun onFocusChange(block: ((item: DelegateModel<*>, view: View, hasFocus: Boolean, position: Int) -> Unit)?) {
        onFocusChange = block
    }

    private var items = emptyList<DelegateModel<I>>()

    protected val delegates = mutableListOf<Delegate<out I>>()

    var recyclerView by weak<RecyclerView?>()

    init {
        delegate.forEach { addDelegate(it) }
    }

    fun addDelegate(delegate: Delegate<out I>) {
        if (delegates.firstOrNull { it.layout == delegate.layout } != null)
            throw IllegalArgumentException("Layout '${resName(delegate.layout)}' already added, each delegate needs to be unique.")

        delegates.add(delegate)
    }

    private fun resName(@AnyRes id: Int): String = recyclerView?.resources?.getResourceEntryName(id) ?: "$id"

    fun removeDelegate(delegate: Delegate<out I>) {
        delegates.remove(delegate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DelegateViewHolder {
        return delegateByViewType(viewType).createViewHolder(parent)
    }

    protected fun delegateByViewType(viewType: Int) =
        delegates.firstOrNull { it.layout == viewType }
            ?: throw IllegalArgumentException("No delegate added for '${resName(viewType)}'. Please add delegate using adapter#addDelegate.")


    override fun onBindViewHolder(holder: DelegateViewHolder, position: Int) {
        val item = itemAt(position)

        onItemClick?.let { itemClick ->
            holder.itemView.setOnClickListener { view -> itemClick.invoke(item, view, position) }
        }

        onFocusChange?.let { focusChange ->
            holder.itemView.setOnFocusChangeListener { view, hasFocus -> focusChange.invoke(item, view, hasFocus, position) }
        }

        delegateAtAdapterPosition(position).bindViewHolder(holder, item, null)
    }

    override fun onBindViewHolder(
        holder: DelegateViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        val item = itemAt(position)

        onItemClick?.let { itemClick ->
            holder.itemView.setOnClickListener { view -> itemClick.invoke(item, view, position) }
        }

        onFocusChange?.let { focusChange ->
            holder.itemView.setOnFocusChangeListener { view, hasFocus -> focusChange.invoke(item, view, hasFocus, position) }
        }

        delegateAtAdapterPosition(position).bindViewHolder(holder, item, payloads)
    }

    protected fun delegateAtAdapterPosition(position: Int) =
        delegates.first { it.layout == getItemViewType(position) }

    override fun getItemViewType(position: Int) = itemAt(position).layout

    protected fun itemAt(position: Int) = items[position]

    override fun getItemCount() = items.size

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        this.recyclerView = null
    }

    override fun onViewRecycled(holder: DelegateViewHolder) {
        with(holder) {
            onViewRecycled()
            itemView.setOnClickListener(null)
            itemView.onFocusChangeListener = null
        }

        super.onViewRecycled(holder)
    }

    override fun onFailedToRecycleView(holder: DelegateViewHolder): Boolean {
        return holder.onFailedToRecycleView()
    }

    override fun onViewAttachedToWindow(holder: DelegateViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.onViewAttachedToWindow()
    }

    override fun onViewDetachedFromWindow(holder: DelegateViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.onViewDetachedFromWindow()
    }

    fun submitList(newItems: List<DelegateModel<I>>) {
        items = newItems
        notifyDataSetChanged()
    }

}