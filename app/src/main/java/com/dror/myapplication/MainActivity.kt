package com.dror.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dror.myapplication.adapter.NumbersAdapter
import com.dror.myapplication.viewModel.NumbersViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: NumbersViewModel
    private var adapter: NumbersAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel =
            ViewModelProviders.of(this).get(NumbersViewModel::class.java)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.getNumbers().observe(this, Observer { numbers ->
            numbers?.let {
                setNumbersList(it)
            }
        })
        viewModel.getLoading().observe(this, Observer { loading ->
            if(loading) {
                progressBar.visibility = View.VISIBLE
            }
            else {
                progressBar.visibility = View.GONE
            }
        })
        viewModel.getError().observe(this, Observer { error ->
            error?.let {
                if(error.isNotEmpty()) {
                    Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
                }
            }
        })
    }

    private fun setNumbersList(numbers: List<Int>) {
        if (adapter == null) {
            adapter = NumbersAdapter(this@MainActivity, numbers)
            numbersRecyclerView.layoutManager = LinearLayoutManager(this)
            numbersRecyclerView.adapter = adapter
        }
        else {
            adapter!!.setNumbers(numbers)
        }

        adapter!!.notifyDataSetChanged()
    }
}
