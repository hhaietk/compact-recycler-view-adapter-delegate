package de.hhn.compactadapterdelegate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.hhn.compactadapterdelegate.databinding.ActivityMainBinding
import de.hhn.compactadapterdelegate.delegates.FooterDelegate
import de.hhn.compactadapterdelegate.delegates.HeaderDelegate
import de.hhn.compactadapterdelegate.delegates.ProductDelegate
import de.hhn.compactadapterdelegate.lib.DelegateAdapter

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val delegateAdapter = DelegateAdapter(
            HeaderDelegate(),
            FooterDelegate(),
            ProductDelegate()
        ).apply {
            val items = FakeDataGenerator.create()
            submitList(items)
        }

        delegateAdapter.onItemClick { item, view, position -> binding?.itemClickListener?.text = "Clicked on $position" }
        delegateAdapter.onFocusChange { item, view, hasFocus, position -> binding?.itemFocusListener?.text = "item $position hasFocus $hasFocus" }

        binding?.products?.run {
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            layoutManager = LinearLayoutManager(context)
            adapter = delegateAdapter
        }
    }
}