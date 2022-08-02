package de.hhn.lib

import android.view.View
import android.view.ViewGroup
import androidx.annotation.AnyRes
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.Executors

class DelegateListAdapter<I>(vararg delegate: Delegate<out I>) : ListAdapter<DelegateModel<I>, DelegateViewHolder>(
    AsyncDifferConfig
        .Builder(DelegateModelDiffCallback<I>())
        .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
        .build()
) {

    protected val delegates = mutableListOf<Delegate<out I>>()

    init {
        delegate.forEach { addDelegate(it) }
    }

    protected var onItemClick: ((item: DelegateModel<I>, view: View, position: Int) -> Unit)? = null
    fun onItemClick(block: ((item: DelegateModel<I>, view: View, position: Int) -> Unit)?) {
        onItemClick = block
    }

    protected var onFocusChange: ((item: DelegateModel<*>, view: View, hasFocus: Boolean, position: Int) -> Unit)? = null
    fun onFocusChange(block: ((item: DelegateModel<*>, view: View, hasFocus: Boolean, position: Int) -> Unit)?) {
        onFocusChange = block
    }

    var recyclerView by weak<RecyclerView?>()

    override fun getItemCount(): Int = currentList.size

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

    protected fun itemAt(position: Int): DelegateModel<I> = currentList[position]

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

    @LayoutRes
    override fun getItemViewType(position: Int): Int = getItem(position).layout

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
}