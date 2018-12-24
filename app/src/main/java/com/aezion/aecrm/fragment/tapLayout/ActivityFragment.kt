package com.aezion.aecrm.fragment.tapLayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.aezion.aecrm.R
import com.aezion.aecrm.adapter.AppointmentAdapter
import com.aezion.aecrm.model.AppointModel
import kotlinx.android.synthetic.main.fragment_activity.view.*

class ActivityFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_activity, container, false)

        view.activity_item_list!!.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(activity)
        view.activity_item_list!!.layoutManager = layoutManager
        view.activity_item_list!!.itemAnimator = DefaultItemAnimator()
        data = ArrayList()
        for (i in timeArr.indices) {
            data!!.add(
                AppointModel(
                    timeArr[i],
                    dateArr[i],
                    monthArr[i],
                    headerArr[i],
                    descArr[i]
                )
            )
        }
        view.activity_item_list!!.adapter = AppointmentAdapter(this.activity!!, data!!)

        return view
    }



    companion object {
        private var data: ArrayList<AppointModel>? = null
        internal var timeArr = arrayOf("14:20", "08:00", "15:45", "23:15")
        internal var dateArr = arrayOf("20", "21", "01", "30")
        internal var monthArr = arrayOf("OCT", "OCT", "NOV", "NOV")
        internal var headerArr =
            arrayOf("ECI Patient portal discovery", "ECIP Proposed", "Cases module", "Follow up and final review")
        internal var descArr = arrayOf("First time", "Proposal review", "Follow-up Session", "Dealbreak")

    }

}