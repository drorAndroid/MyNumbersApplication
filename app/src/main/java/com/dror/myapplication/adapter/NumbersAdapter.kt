package com.dror.myapplication.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dror.myapplication.R

class NumbersAdapter(val c: Activity, var numbersList: List<Int>): RecyclerView.Adapter<NumbersAdapter.ViewHolder>() {
    private var checkedNumbers: List<Int> = listOf()
    private val itemViewTypeSum0 = 100
    private val itemViewTypeNonSum0 = 200
    private val targetSum = 0

    override fun getItemViewType(position: Int): Int {
        if(numbersList[position] != 0 && numbersList.toHashSet().contains(targetSum - numbersList[position])) {
            return itemViewTypeSum0
        }

        return itemViewTypeNonSum0
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun setNumbers(numbers: List<Int>) {
        this.checkedNumbers = listOf()
        this.numbersList = numbers
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if(viewType == itemViewTypeSum0) {
            return ViewHolder(
                LayoutInflater.from(c).inflate(R.layout.number_cell_sum_0, parent, false)
            )
        }

        return ViewHolder(
            LayoutInflater.from(c).inflate(R.layout.number_cell_non_sum_0, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return numbersList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mTextView?.text = numbersList[position].toString()
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        var mTextView: TextView? = view.findViewById(R.id.textView)
    }
}