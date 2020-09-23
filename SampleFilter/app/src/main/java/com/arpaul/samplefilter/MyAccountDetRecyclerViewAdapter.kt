package com.arpaul.samplefilter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.arpaul.samplefilter.dummy.DummyContent.AccountDetail

/**
 * [RecyclerView.Adapter] that can display a [AccountDetail].
 * TODO: Replace the implementation with code for your data type.
 */
class MyAccountDetRecyclerViewAdapter(
    private val values: List<AccountDetail>
) : RecyclerView.Adapter<MyAccountDetRecyclerViewAdapter.ViewHolder>() {

    private var accountList = values

    fun refresh(newValues: List<AccountDetail>) {
        accountList = newValues
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_account, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = accountList[position]
        holder.idView.text = (position + 1).toString()
        holder.contentView.text = item.accountName
    }

    override fun getItemCount(): Int = accountList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.item_number)
        val contentView: TextView = view.findViewById(R.id.content)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}