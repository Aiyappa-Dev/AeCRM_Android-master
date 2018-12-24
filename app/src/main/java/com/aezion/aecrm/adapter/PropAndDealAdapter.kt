package com.aezion.aecrm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aezion.aecrm.R
import com.aezion.aecrm.model.ProposalModel
import kotlinx.android.synthetic.main.item_prop_deal.view.*


class PropAndDealAdapter(private val mCtx: Context, private val dataSet: ArrayList<ProposalModel>) :
    RecyclerView.Adapter<PropAndDealAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mItemView: View = itemView
    }

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int ): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_prop_deal, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, listPosition: Int) {
        holder.mItemView.name.text = dataSet[listPosition].name
        holder.mItemView.designation.text = dataSet[listPosition].price

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}