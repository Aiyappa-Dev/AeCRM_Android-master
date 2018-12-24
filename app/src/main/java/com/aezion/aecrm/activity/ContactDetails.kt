package com.aezion.aecrm.activity

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Slide
import com.aezion.aecrm.R
import com.aezion.aecrm.adapter.AppointmentAdapter
import com.aezion.aecrm.model.AppointModel
import kotlinx.android.synthetic.main.details_contact.*

class ContactDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_contact)

        //requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        /*val enterTransition = Slide()
        enterTransition.propagation = null*/

        appointment_list!!.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        appointment_list!!.layoutManager = layoutManager
        appointment_list!!.itemAnimator = DefaultItemAnimator()
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
        appointment_list!!.adapter = AppointmentAdapter(this, data!!)

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
