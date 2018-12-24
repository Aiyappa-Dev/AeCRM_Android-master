package com.aezion.aecrm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aezion.aecrm.R
import com.aezion.aecrm.model.ProspectItemModel
import kotlinx.android.synthetic.main.item_pros_details.view.*

class ProspectItemAdapter(private val mCtx: Context, private val dataSet: ArrayList<ProspectItemModel>) :
    RecyclerView.Adapter<ProspectItemAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mItemView: View = itemView
    }

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int ): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pros_details, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, listPosition: Int) {
        holder.mItemView.pros_name.text = dataSet[listPosition].prosName
        holder.mItemView.pros_position.text = dataSet[listPosition].prosPosition

        /*holder.mItemView.card_view.setOnClickListener {
            val i = Intent(mCtx, ProspectItemAdapter::class.java)
            mCtx.startActivity(i)
        }*/
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}