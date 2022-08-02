# Compact RecyclerView Adapter Delegate 

Convenience library to handle different view types with different delegates in a single RecyclerView. Inspired from [RecyclerView Presenter](https://github.com/kibotu/RecyclerViewPresenter) and [AdapterDelegates](https://github.com/sockeqwe/AdapterDelegates)

### How to use


1. Create a delegate

```kotlin
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
```

2. Create adapter and add delegate(s) to your adapter

```kotlin
val delegateAdapter = DelegateListAdapter(
    HeaderDelegate(),
    FooterDelegate(),
    ProductDelegate()
)

// OR

val delegateAdapter = DelegateListAdapter()
delegateAdapter.addDelegate(HeaderDelegate())
delegateAdapter.addDelegate(FooterDelegate())
delegateAdapter.addDelegate(ProductDelegate())
```

3. Submit list of models with  matching layout to adapter

```kotlin
val items = listOf(
    DelegateModel(PageModel.Header("---Product List---"), R.layout.header),
    DelegateModel(PageModel.Product("Hanseatic", 112.1), R.layout.product),
    DelegateModel(PageModel.Product("N26", 99.9), R.layout.product),
)

adapter.submitList(items)
```

4. Add click listener to adapter

```kotlin
delegateAdapter.onItemClick { item, view, position -> binding?.itemClickListener?.text = "Clicked on $position" }
```

5. Add focus listener to adapter

```kotlin
delegateAdapter.onFocusChange { item, view, hasFocus, position -> binding?.itemFocusListener?.text = "item $position hasFocus $hasFocus" }
```

#### Updating items

```kotlin
adapter.submitList(newItems)
```
