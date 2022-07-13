package de.hhn.compactadapterdelegate.lib

import androidx.annotation.LayoutRes

class DelegateModel<out M>(val model: M, @LayoutRes val layout: Int) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DelegateModel<*>

        if (model != other.model) return false
        if (layout != other.layout) return false

        return true
    }

    override fun hashCode(): Int {
        var result = model?.hashCode() ?: 0
        result = 31 * result + layout
        return result
    }

    override fun toString(): String = "DelegateModel(model=$model, layout=$layout)"
}