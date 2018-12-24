package com.aezion.aecrm.fragment.bottomNav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aezion.aecrm.R
import com.aezion.aecrm.activity.MainActivity
import com.aezion.aecrm.adapter.ProspectAdapter
import com.aezion.aecrm.model.ProspectModel
import kotlinx.android.synthetic.main.fragment_prospects.view.*
import java.util.*

class ProspectsFragment : Fragment() {

    var mAdapter: ProspectAdapter? = null

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_prospects, container, false)
        data = ArrayList()
        for (i in name.indices) {
            data!!.add(
                ProspectModel(
                    name[i],
                    "lorem ipsum",
                    weather[i], thumbs[i], sync[i], date[i], search[i], assignment[i]
                )
            )
        }

        (activity as MainActivity).addAnimationToList(view.prospect_recyclerView!!, activity as MainActivity)

        mAdapter = ProspectAdapter(this.activity!!, data!!, data!!)
        view.prospect_recyclerView!!.adapter = mAdapter

        return view
    }

    companion object {
        private var data: ArrayList<ProspectModel>? = null
        internal var name = arrayOf(
            "Paladin Web",
            "Huang Acrylic",
            "ECI E-Teams",
            "WalletPoll",
            "Paladin Web",
            "Huang Acrylic",
            "ECI E-Teams",
            "WalletPoll"
        )
        internal var weather = arrayOf("cold", "warm", "hot", "hot", "cold", "warm", "hot", "hot")
        internal var thumbs =
            arrayOf("inactive", "active", "inactive", "inactive", "inactive", "active", "inactive", "inactive")
        internal var sync =
            arrayOf("active", "inactive", "inactive", "inactive", "active", "inactive", "inactive", "inactive")
        internal var date =
            arrayOf("active", "inactive", "active", "inactive", "active", "inactive", "active", "inactive")
        internal var search =
            arrayOf("inactive", "inactive", "inactive", "inactive", "inactive", "inactive", "inactive", "inactive")
        internal var assignment =
            arrayOf("inactive", "active", "inactive", "inactive", "inactive", "active", "inactive", "inactive")
    }
}// Required empty public constructor
