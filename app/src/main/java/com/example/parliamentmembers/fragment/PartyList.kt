package com.example.parliamentmembers.fragment

import com.example.parliamentmembers.adapter.PartyListAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parliamentmembers.R
import com.example.parliamentmembers.databinding.FragmentPartyListBinding
import com.example.parliamentmembers.viewModel.ParliamentMemberViewModel

/**
 * name: Nischhal Shrestha
 * date: 8/10/2021
 *
 * Fragment Observes ParliamentMemberViewModel class
 */

class PartyList : Fragment() {
    private lateinit var binding: FragmentPartyListBinding
    private lateinit var mParliamentMemberViewModel: ParliamentMemberViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflating the layout for this fragment
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_party_list, container, false)

        binding.partyListRecyclerView.setHasFixedSize(true)
        //RecyclerView adapter initialization
        val partyListAdapter = PartyListAdapter()
        binding.partyListRecyclerView.adapter = partyListAdapter
        binding.partyListRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        //Initializing ViewMode class
        mParliamentMemberViewModel = ViewModelProvider(this)
            .get(ParliamentMemberViewModel::class.java)

        //Observing liveData of the ParliamentMemberViewModel class
        mParliamentMemberViewModel.parliamentMemberList.observe(viewLifecycleOwner, { response ->
            partyListAdapter.setData(response)
        })
        return binding.root
    }
}