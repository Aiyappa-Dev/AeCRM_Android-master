package com.aezion.aecrm.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.aezion.aecrm.R
import com.aezion.aecrm.activity.ProspectDetails
import com.aezion.aecrm.model.ContactsModel
import com.aezion.aecrm.model.ProspectModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_prospects.view.*
import java.util.*

class ProspectAdapter(
    private val mCtx: Context,
    private var dataSet: ArrayList<ProspectModel>,
    private val mItems: ArrayList<ProspectModel>
) :
    RecyclerView.Adapter<ProspectAdapter.MyViewHolder>(), Filterable {



    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
                val query = charSequence.toString()

                var filtered: MutableList<ProspectModel> = ArrayList()

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
                dataSet = results.values as ArrayList<ProspectModel>
                notifyDataSetChanged()
            }
        }
    }



    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mItemView: View = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_prospects, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, listPosition: Int) {

        holder.mItemView.prospect_name.text = dataSet[listPosition].name
        holder.mItemView.lorem_ipsum.text = dataSet[listPosition].subName

        when (dataSet[listPosition].weather) {
            "cold" -> holder.mItemView.weather.setBackgroundColor(Color.parseColor("#67cdfc"))
            "warm" -> holder.mItemView.weather.setBackgroundColor(Color.parseColor("#e8c344"))
            "hot" -> holder.mItemView.weather.setBackgroundColor(Color.parseColor("#e75980"))
        }
        if (dataSet[listPosition].thumbs == "inactive") {
            Glide.with(mCtx).load(R.drawable.thumb_inactive).into(holder.mItemView.thumbs_up)
        } else {
            Glide.with(mCtx).load(R.drawable.ic_thumb_up_black_24dp).into(holder.mItemView.thumbs_up)
        }
        if (dataSet[listPosition].sync == "inactive") {
            Glide.with(mCtx).load(R.drawable.sync_inactive).into(holder.mItemView.sync)
        } else {
            Glide.with(mCtx).load(R.drawable.ic_sync_black_24dp).into(holder.mItemView.sync)
        }
        if (dataSet[listPosition].date == "inactive") {
            Glide.with(mCtx).load(R.drawable.date_inactive).into(holder.mItemView.calendar)
        } else {
            Glide.with(mCtx).load(R.drawable.date_inactive).into(holder.mItemView.calendar)
        }
        if (dataSet[listPosition].search == "inactive") {
            Glide.with(mCtx).load(R.drawable.search_inactive).into(holder.mItemView.search)
        } else {
            Glide.with(mCtx).load(R.drawable.ic_search_black_24dp).into(holder.mItemView.search)
        }
        if (dataSet[listPosition].assignment == "inactive") {
            Glide.with(mCtx).load(R.drawable.assignment_inactive).into(holder.mItemView.assignment)
        } else {
            Glide.with(mCtx).load(R.drawable.ic_assignment_black_24dp).into(holder.mItemView.assignment)
        }

        holder.mItemView.weather.text = dataSet[listPosition].weather

        holder.mItemView.textViewOptions.setOnClickListener {
            //creating a popup menu
            val popup = PopupMenu(mCtx, holder.mItemView.textViewOptions)
            //inflating menu from xml resource
            popup.inflate(R.menu.options_menu)
            //adding click listener
            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.menu1 -> {
                    }
                    R.id.menu2 -> {
                    }
                    R.id.menu3 -> {
                    }
                }//handle menu1 click
                //handle menu2 click
                //handle menu3 click
                false
            }
            //displaying the popup
            popup.show()
        }


        holder.mItemView.card_view.setOnClickListener {
            val i = Intent(mCtx, ProspectDetails::class.java)
            mCtx.startActivity(i)
        }

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}
