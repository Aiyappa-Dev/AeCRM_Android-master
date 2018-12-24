package com.aezion.aecrm.fragment.bottomNav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aezion.aecrm.R
import com.aezion.aecrm.activity.MainActivity
import com.aezion.aecrm.adapter.ProposalAdapter
import com.aezion.aecrm.model.ProposalModel
import kotlinx.android.synthetic.main.fragment_proposals2.view.*

class ProposalsFragment : Fragment() {


    var mAdapter: ProposalAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_proposals2, container, false)

        (activity as MainActivity).addAnimationToList(view.proposals_recyclerView!!, activity as MainActivity)

        data = ArrayList()
        for (i in name.indices) {
            data!!.add(
                ProposalModel(
                    name[i], company[i],
                    price[i]
                )
            )
        }
        mAdapter = ProposalAdapter(this.activity!!, data!!, data!!)
        view.proposals_recyclerView!!.adapter = mAdapter
        return view
    }

    companion object {
        private var data: ArrayList<ProposalModel>? = null
        internal var name = arrayOf("Paladin Web", "Dena R. Baker", "Isabelle Carvalho Lima", "Saevar Valdimarsson", "Paladin Web", "Dena R. Baker", "Isabelle Carvalho Lima", "Saevar Valdimarsson")
        internal var company = arrayOf("PitStop Auto Google feeds", "PitStop Auto", "Huang Acrylic", "Unity", "PitStop Auto Google feeds", "PitStop Auto", "Huang Acrylic", "Unity")
        internal var price = arrayOf("$45654", "$56435", "$324535", "$34535", "$45654", "$56435", "$324535", "$34535")
    }
}