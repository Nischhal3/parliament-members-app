package com.example.parliamentmembers.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.parliamentmembers.R
import com.example.parliamentmembers.fragment.PartyMemberListDirections
import com.example.parliamentmembers.model.ParliamentMember
import com.squareup.picasso.Picasso

/**
 * name: Nischhal Shrestha
 * date: 2/10/2021
 *
 * RecyclerView Adapter for partyMemberListFragment
 */

class PartyMemberAdapter : RecyclerView.Adapter<PartyMemberAdapter.ViewHolder>() {
    //Base url link of the pictures of the parliament members
    private val baseUrl = "https://avoindata.eduskunta.fi/"
    private var parliamentMemberList = emptyList<ParliamentMember>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //Getting references of field from the xml file
        val firstName: TextView = itemView.findViewById(R.id.first_name)
        val lastName: TextView = itemView.findViewById(R.id.last_name)
        val region: TextView = itemView.findViewById(R.id.region)
        val partyName: TextView = itemView.findViewById(R.id.party_name)
        val image: ImageView = itemView.findViewById(R.id.member_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMember = parliamentMemberList[position]
        val imageUrl: String = baseUrl + currentMember.picture

        holder.firstName.text = currentMember.first
        holder.lastName.text = currentMember.last
        holder.region.text = currentMember.constituency
        holder.partyName.text = currentMember.party
        //loading image to the ImageView
        Picasso.get().load(imageUrl).into(holder.image)

        //RecyclerView item click listener
        holder.itemView.setOnClickListener { view ->
            //Getting the position of item clicked in recycleView
            val itemPosition = holder.bindingAdapterPosition
            //Stores personNumber of the clicked person as personID
            val personId = parliamentMemberList[itemPosition].personNumber

            //Passing value of personId to the next fragment
            view.findNavController()
                .navigate(PartyMemberListDirections.actionMainScreenToReview(personId))
        }
    }

    override fun getItemCount(): Int {
        return parliamentMemberList.size
    }

    /**
     * @param parliamentMemberList is a list of ParliamentMember
     * @param partyName holds the value of the party name passed from previous fragment
     */
    fun setData(parliamentMemberList: List<ParliamentMember>, partyName: String) {
        //Filtering list by party name
        this.parliamentMemberList = parliamentMemberList.filter { it.party == partyName }
        notifyDataSetChanged()
    }
}

