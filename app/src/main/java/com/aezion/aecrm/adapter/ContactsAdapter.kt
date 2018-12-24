package com.aezion.aecrm.adapter

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Transition
import com.aezion.aecrm.R
import com.aezion.aecrm.activity.ContactDetails
import com.aezion.aecrm.common.ExplodeFadeOut
import com.aezion.aecrm.model.ContactsModel
import kotlinx.android.synthetic.main.item_contacts.view.*

class ContactsAdapter(
    private val mCtx: Activity,
    private var dataSet: ArrayList<ContactsModel>,
    private var mItems: ArrayList<ContactsModel>
)
    : RecyclerView.Adapter<ContactsAdapter.MyViewHolder>(), Filterable {


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
                val query = charSequence.toString()

                var filtered: MutableList<ContactsModel> = ArrayList()

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
                dataSet = results.values as ArrayList<ContactsModel>
                notifyDataSetChanged()
            }
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mItemView: View = itemView
    }

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int ): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contacts, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, listPosition: Int) {
        holder.mItemView.name.text = dataSet[listPosition].name
        holder.mItemView.company.text = dataSet[listPosition].company
        holder.mItemView.designation.text = dataSet[listPosition].designation

        holder.mItemView.card_view.setOnClickListener {

            /*val exitTransition = getListFragmentExitTransition(itemView = it)
            exitTransition.propagation = null*/

            val i = Intent(mCtx, ContactDetails::class.java)
            // create the transition animation - the images in the layouts
            // of both activities are defined with android:transitionName="robot"
            val options = ActivityOptions
                .makeSceneTransitionAnimation(mCtx, holder.mItemView.img_profile,
                    "robot")

            // start the new activity
            mCtx.startActivity(i, options.toBundle())

        }
    }

    private fun getListFragmentExitTransition(itemView: View):Transition  {
        val epicCenterRect = Rect()
        //itemView is the full-width inbox item's view
        itemView.getGlobalVisibleRect(epicCenterRect)
        // Set Epic center to a imaginary horizontal full width line under the clicked item, so the explosion happens vertically away from it
        epicCenterRect.top = epicCenterRect.bottom
        val exitTransition = ExplodeFadeOut()
        exitTransition.epicenterCallback = object : Transition.EpicenterCallback() {
            override fun onGetEpicenter(transition: Transition): Rect {
                return epicCenterRect
            }
        }
        return exitTransition
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}