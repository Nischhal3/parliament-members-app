package com.example.parliamentmembers.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parliamentmembers.R
import com.example.parliamentmembers.adapter.PartyMemberAdapter
import com.example.parliamentmembers.databinding.FragmentMainScreenBinding
import com.example.parliamentmembers.viewModel.ParliamentMemberViewModel

/**
 * name: Nischhal Shrestha
 * id: 2012216
 * date: 8/10/2021
 *
 * Fragment Observes ParliamentMemberViewModel class
 */

class PartyMemberList : Fragment() {
    private lateinit var binding: FragmentMainScreenBinding
    private lateinit var mParliamentMemberViewModel: ParliamentMemberViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflating the layout for this fragment
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_main_screen, container, false)

        binding.mainRecyclerView.setHasFixedSize(true)
        //RecyclerView adapter initialization
        val partyMemberAdapter = PartyMemberAdapter()
        binding.mainRecyclerView.adapter = partyMemberAdapter
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        //Storing value of the party name from PartyList fragment
        val partyName = PartyMemberListArgs.fromBundle(requireArguments()).itemName

        //Initializing ViewModel class
        mParliamentMemberViewModel = ViewModelProvider(this)
            .get(ParliamentMemberViewModel::class.java)

        //Observing liveData of the ParliamentMemberViewModel class
        mParliamentMemberViewModel.parliamentMemberList.observe(
            viewLifecycleOwner,
            { parliamentMember ->
                partyMemberAdapter.setData(parliamentMember, partyName)
            })
        return binding.root
    }
}