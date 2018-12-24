package com.aezion.aecrm.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.aezion.aecrm.R
import com.aezion.aecrm.adapter.ProposalItemAdapter
import com.aezion.aecrm.model.ProposalItemModel
import kotlinx.android.synthetic.main.details_proposl.*

class ProposalDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_proposl)


        pro_item_list!!.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        pro_item_list!!.layoutManager = layoutManager
        pro_item_list!!.itemAnimator = DefaultItemAnimator()
        data = ArrayList()
        for (i in nameArr.indices) {
            data!!.add(
                ProposalItemModel(
                    nameArr[i],
                    descArr[i],
                    qtyArr[i],
                    unitPriceArr[i],
                    totalPriceArr[i]
                )
            )
        }
        pro_item_list!!.adapter = ProposalItemAdapter(this, data!!)

    }



    companion object {
        private var data: ArrayList<ProposalItemModel>? = null
        internal var nameArr = arrayOf("Service One", "Service A2BC6", "Service Two", "Service New")
        internal var descArr = arrayOf("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis ullamcorper neque arcu, vel fermentum quam viverra id. In porttitor efficitur erat eu aliquet.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis ullamcorper neque arcu, vel fermentum quam viverra id. In porttitor efficitur erat eu aliquet.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis ullamcorper neque arcu, vel fermentum quam viverra id. In porttitor efficitur erat eu aliquet.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis ullamcorper neque arcu, vel fermentum quam viverra id. In porttitor efficitur erat eu aliquet.")
        internal var qtyArr = arrayOf("QTY 5", "QTY 6", "QTY 10", "QTY 5")
        internal var unitPriceArr =
            arrayOf("UNIT PRICE $100", "UNIT PRICE $50", "UNIT PRICE $45", "UNIT PRICE $50")
        internal var totalPriceArr = arrayOf("TOTAL PRICE $500", "TOTAL PRICE $300", "TOTAL PRICE $450", "TOTAL PRICE $250")

    }

}
