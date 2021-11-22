package com.example.parliamentmembers.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.parliamentmembers.R
import com.example.parliamentmembers.model.ParliamentMember
import com.example.parliamentmembers.fragment.PartyListDirections

/**
 * name: Nischhal Shrestha
 * date: 8/10/2021
 *
 * RecyclerView Adapter for partyList Fragment
 */

class PartyListAdapter : RecyclerView.Adapter<PartyListAdapter.ViewHolder>() {
    //Stores the party name without duplicates
    private var partyName = mutableSetOf<String>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //Getting references of field from the xml file
        val partyList: TextView = itemView.findViewById(R.id.party_list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.party_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.partyList.text = partyName.elementAt(position)

        //RecyclerView item click listener
        holder.itemView.setOnClickListener { view ->
            //stores the position of the item clicked in recyclerView
            val itemPosition = holder.bindingAdapterPosition
            //Passing name of  the party on click item to next fragment
            view.findNavController().navigate(
                PartyListDirections.actionPartyListToMainScreen(partyName.elementAt(itemPosition))
            )
        }
    }

    override fun getItemCount(): Int {
        return partyName.size
    }

    /**
     * @param parliamentMemberList holds the list of ParliamentMember
     */
    fun setData(parliamentMemberList: List<ParliamentMember>) {
        //Storing party names as List
        parliamentMemberList.forEach {
            partyName.add(it.party)
        }
        notifyDataSetChanged()
    }
}