package com.aezion.aecrm.fragment.bottomNav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aezion.aecrm.R
import com.aezion.aecrm.activity.MainActivity
import com.aezion.aecrm.adapter.ContactsAdapter
import com.aezion.aecrm.model.ContactsModel
import kotlinx.android.synthetic.main.fragment_contacts.view.*

class ContactsFragment : Fragment() {


    var data: ArrayList<ContactsModel>? = null
    var name= arrayOf("April Walker","Dena R. Baker","Isabelle Carvalho Lima","Saevar Valdimarsson","April Walker","Dena R. Baker","Isabelle Carvalho Lima","Saevar Valdimarsson")
    var company= arrayOf("Eligibilty Consultant Inc.","PitStop Auto","Huang Acrylic","Unity", "Eligibilty Consultant Inc.","PitStop Auto","Huang Acrylic","Unity")
    var designation= arrayOf("IT Director","Operations Manager","Lead Architect","Design Lead", "IT Director","Operations Manager","Lead Architect","Design Lead")


    var mAdapter: ContactsAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_contacts, container, false)

        ///exitTransition = ExplodeFadeOut()
        //enterTransition = Slide()

        (activity as MainActivity).addAnimationToList(view.contacts_recyclerView!!, activity as MainActivity)

        data =ArrayList()
        for (i in name.indices) {
            data!!.add(
                ContactsModel(
                    name[i], company[i],
                    designation[i]
                    )
            )
        }
        mAdapter = ContactsAdapter(this.activity!!, data!!, data!!)
        view.contacts_recyclerView!!.adapter = mAdapter

        //detailFragment.enterTransition = Slide()
        // If you don't want the Fade on the way back
        /*detailFragment.returnTransition = null
        listFragment.exitTransition = Explode()*/
        //listFragment.reenterTransition is auto generated reversed


        return view
    }




    companion object {
        //
        //
       }
}
