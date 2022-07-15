package de.hhn.compactadapterdelegate

import de.hhn.compactadapterdelegate.lib.DelegateModel

object FakeDataGenerator {

    fun create(): List<DelegateModel<PageModel>> {
        return listOf(
            DelegateModel(PageModel.Header("---Product List---"), R.layout.header),
            DelegateModel(PageModel.Product("Hanseatic", 112.1), R.layout.product),
            DelegateModel(PageModel.Product("N26", 99.9), R.layout.product),
            DelegateModel(PageModel.Product("Vivid", 123.3), R.layout.product),
            DelegateModel(PageModel.Product("Revolut", 343.4), R.layout.product),
            DelegateModel(PageModel.Product("DKB", 112.1), R.layout.product),
            DelegateModel(PageModel.Product("SSS", 99.9), R.layout.product),
            DelegateModel(PageModel.Product("ABC", 123.3), R.layout.product),
            DelegateModel(PageModel.Product("Commerz", 343.4), R.layout.product),
            DelegateModel(PageModel.Product("Barclay", 112.1), R.layout.product),
            DelegateModel(PageModel.Product("what", 99.9), R.layout.product),
            DelegateModel(PageModel.Product("no idea", 123.3), R.layout.product),
            DelegateModel(PageModel.Product("Deutsche Bank", 343.4), R.layout.product),
            DelegateModel(PageModel.Footer("Data retrieved on 13.07."), R.layout.footer),
        )
    }

    fun recreate(): List<DelegateModel<PageModel>> {
        return listOf(
            DelegateModel(PageModel.Header("---Product List---"), R.layout.header),
            DelegateModel(PageModel.Product("Vivid", 123.3), R.layout.product),
            DelegateModel(PageModel.Product("Hanseatic", 112.1), R.layout.product),
            DelegateModel(PageModel.Product("N26", 99.9), R.layout.product),
            DelegateModel(PageModel.Product("Revolut", 343.4), R.layout.product),
            DelegateModel(PageModel.Product("DKB", 112.1), R.layout.product),
            DelegateModel(PageModel.Product("SSS", 99.9), R.layout.product),
            DelegateModel(PageModel.Product("ABC", 123.3), R.layout.product),
            DelegateModel(PageModel.Product("Commerz", 343.4), R.layout.product),
            DelegateModel(PageModel.Product("Barclay", 112.1), R.layout.product),
            DelegateModel(PageModel.Product("what", 99.9), R.layout.product),
            DelegateModel(PageModel.Product("no idea", 123.3), R.layout.product),
            DelegateModel(PageModel.Product("Deutsche Bank", 343.4), R.layout.product),
            DelegateModel(PageModel.Footer("Data retrieved on 13.07."), R.layout.footer),
        )
    }
}

sealed interface PageModel {
    data class Header(val title: String) : PageModel
    data class Product(val name: String, val cost: Double) : PageModel
    data class Footer(val text: String) : PageModel
}
