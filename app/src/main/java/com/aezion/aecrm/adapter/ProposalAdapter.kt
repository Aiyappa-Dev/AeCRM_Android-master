package com.aezion.aecrm.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.aezion.aecrm.R
import com.aezion.aecrm.activity.ProposalDetails
import com.aezion.aecrm.model.ProposalModel
import kotlinx.android.synthetic.main.item_proposal.view.*


class ProposalAdapter(
    private val mCtx: Context,
    private var dataSet: ArrayList<ProposalModel>,
    private var mItems: ArrayList<ProposalModel>
) :
    RecyclerView.Adapter<ProposalAdapter.MyViewHolder>(), Filterable {


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
                val query = charSequence.toString()

                var filtered: MutableList<ProposalModel> = ArrayList()

                if (query.isEmpty()) {
                    filtered = mItems
                } else {
                    for (movie in mItems) {
                        if (movie.name!!.toLowerCase().contains(query.toLowerCase())) {
                            filtered.add(movie)
                        }
                    }
                }

                val results = Filter.FilterResults()
                results.count = filtered.size
                results.values = filtered
                return results
            }

            override fun publishResults(charSequence: CharSequence, results: Filter.FilterResults) {
                dataSet = results.values as ArrayList<ProposalModel>
                notifyDataSetChanged()
            }
        }
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mItemView: View = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_proposal, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, listPosition: Int) {
        holder.mItemView.name.text = dataSet[listPosition].name
        holder.mItemView.company.text = dataSet[listPosition].companyName
        holder.mItemView.designation.text = dataSet[listPosition].price

        holder.mItemView.card_view.setOnClickListener {
            val i = Intent(mCtx, ProposalDetails::class.java)
            mCtx.startActivity(i)
        }

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}