package com.aezion.aecrm.fragment.tapLayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.aezion.aecrm.R
import com.aezion.aecrm.adapter.PropAndDealAdapter
import com.aezion.aecrm.model.ProposalModel
import kotlinx.android.synthetic.main.fragment_prop_deal.view.*

class ProposalAndDealFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_prop_deal, container, false)

        data = ArrayList()
        for (i in PropDealName.indices) {
            data!!.add(
                ProposalModel(
                    PropDealName[i], "",
                    price[i]
                )
            )
        }

        val layoutManager = LinearLayoutManager(activity)
        view.prop_deal_item_list!!.layoutManager = layoutManager

        view.prop_deal_item_list!!.adapter = PropAndDealAdapter(this.activity!!, data!!)
        return view
    }

    companion object {
        private var data: ArrayList<ProposalModel>? = null
        internal var PropDealName = arrayOf("Paladin Web", "Dena R. Baker", "Isabelle Carvalho Lima", "Saevar Valdimarsson", "Paladin Web", "Dena R. Baker", "Isabelle Carvalho Lima", "Saevar Valdimarsson")
        internal var price = arrayOf("$45654", "$56435", "$324535", "$34535", "$45654", "$56435", "$324535", "$34535")
    }
}