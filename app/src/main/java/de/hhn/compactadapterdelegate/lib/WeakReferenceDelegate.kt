package de.hhn.compactadapterdelegate.lib

import java.lang.ref.WeakReference
import kotlin.reflect.KProperty

internal inline fun <reified T> weak() = WeakReferenceDelegate<T>()

internal class WeakReferenceDelegate<T> {

    private var weakReference: WeakReference<T>? = null

    operator fun getValue(thisRef: Any, property: KProperty<*>): T? = weakReference?.get()

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        weakReference = WeakReference(value)
    }
}