package com.aezion.aecrm.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aezion.aecrm.R
import com.aezion.aecrm.model.AppointModel
import kotlinx.android.synthetic.main.item_appoint.view.*

class AppointmentAdapter(private val mCtx: Activity, private val dataSet: ArrayList<AppointModel>) :
    RecyclerView.Adapter<AppointmentAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mItemView: View = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int ): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_appoint, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, listPosition: Int) {
        holder.mItemView.time_id.text = dataSet[listPosition].mTime
        holder.mItemView.day_id.text = dataSet[listPosition].day
        holder.mItemView.month_id.text = dataSet[listPosition].month
        holder.mItemView.header_id.text = dataSet[listPosition].AppointHeader
        holder.mItemView.desc_id.text = dataSet[listPosition].AppointDesc
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}