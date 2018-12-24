package com.aezion.aecrm.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aezion.aecrm.R
import com.aezion.aecrm.adapter.MyPagerAdapter
import com.aezion.aecrm.adapter.ProspectItemAdapter
import com.aezion.aecrm.model.ProspectItemModel
import kotlinx.android.synthetic.main.details_prospect.*

class ProspectDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_prospect)

        pros_item_list!!.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        pros_item_list!!.layoutManager = layoutManager
        pros_item_list!!.itemAnimator = DefaultItemAnimator()
        data = ArrayList()
        for (i in nameArr.indices) {
            data!!.add(
                ProspectItemModel(
                    nameArr[i],
                    posArr[i]
                )
            )
        }
        pros_item_list!!.adapter = ProspectItemAdapter(this, data!!)

        val fragmentAdapter = MyPagerAdapter(supportFragmentManager)
        viewpager_main.adapter = fragmentAdapter
        tabs_main.setupWithViewPager(viewpager_main)
    }

    companion object {
        private var data: ArrayList<ProspectItemModel>? = null
        internal var nameArr= arrayOf("April Walker","Dena R. Baker","Isabelle Carvalho Lima","Saevar Valdimarsson","April Walker","Dena R. Baker","Isabelle Carvalho Lima","Saevar Valdimarsson")
        internal  var posArr= arrayOf("IT Director", "UI Lead", "IT Director", "UI Lead", "IT Director","PitStop Auto","Huang Acrylic","Unity")
    }
}
