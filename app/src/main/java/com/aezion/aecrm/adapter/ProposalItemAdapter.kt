package com.aezion.aecrm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aezion.aecrm.R
import com.aezion.aecrm.model.ProposalItemModel

class ProposalItemAdapter(private val mCtx: Context, private val dataSet: ArrayList<ProposalItemModel>) :
    RecyclerView.Adapter<ProposalItemAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mItemView: View = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int ): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pro_detail, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, listPosition: Int) {
        }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}