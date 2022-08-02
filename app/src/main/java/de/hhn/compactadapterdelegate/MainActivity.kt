package de.hhn.compactadapterdelegate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import de.hhn.compactadapterdelegate.databinding.ActivityMainBinding
import de.hhn.compactadapterdelegate.delegates.FooterDelegate
import de.hhn.compactadapterdelegate.delegates.HeaderDelegate
import de.hhn.compactadapterdelegate.delegates.ProductDelegate
import de.hhn.lib.DelegateListAdapter

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setUpRecyclerView()
    }

    private var isReset = false

    private fun setUpRecyclerView() {
        val delegateAdapter = DelegateListAdapter(
            HeaderDelegate(),
            FooterDelegate(),
            ProductDelegate()
        )

        delegateAdapter.onItemClick { item, view, position -> binding?.itemClickListener?.text = "Clicked on $position" }
        delegateAdapter.onFocusChange { item, view, hasFocus, position -> binding?.itemFocusListener?.text = "item $position hasFocus $hasFocus" }

        binding?.products?.run {
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            layoutManager = LinearLayoutManager(context)
            adapter = delegateAdapter.apply { submitList(FakeDataGenerator.create()) }
        }


        binding?.shuffle?.setOnClickListener {
            val items = if (isReset) {
                binding?.shuffle?.text = "SHUFFLE"
                isReset = false
                FakeDataGenerator.create()
            } else {
                binding?.shuffle?.text = "RESET"
                isReset = true
                FakeDataGenerator.recreate()
            }
             delegateAdapter.submitList(items)
        }
    }
}