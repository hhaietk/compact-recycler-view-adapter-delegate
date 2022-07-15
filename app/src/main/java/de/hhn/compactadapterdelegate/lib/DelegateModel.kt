package de.hhn.compactadapterdelegate.lib

import androidx.annotation.LayoutRes

class DelegateModel<M>(
    val model: M,
    @LayoutRes val layout: Int,
    val uuid: String = UUIDGenerator.newUUID().toString(),
    private val changedPayload: ((new: M) -> Any?)? = null
) {

    @Suppress("UNCHECKED_CAST")
    internal fun changedPayload(new: Any): Any? = changedPayload?.invoke(new as M)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DelegateModel<*>

        if (model != other.model) return false
        if (layout != other.layout) return false
        if (uuid != other.uuid) return false

        return true
    }

    override fun hashCode(): Int {
        var result = model?.hashCode() ?: 0
        result = 31 * result + layout
        result = 31 * result + uuid.hashCode()
        return result
    }

    override fun toString(): String = "DelegateModel(model=$model, layout=$layout, uuid=$uuid)"
}