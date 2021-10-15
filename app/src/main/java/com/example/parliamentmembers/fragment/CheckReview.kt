package com.example.parliamentmembers.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parliamentmembers.R
import com.example.parliamentmembers.adapter.CheckReviewAdapter
import com.example.parliamentmembers.databinding.FragmentCheckReviewBinding
import com.example.parliamentmembers.viewModel.ReviewViewModel

/**
 * name: Nischhal Shrestha
 * id: 2012216
 * date: 10/10/2021
 *
 * Fragment observers ReviewViewModel class
 */
class CheckReview : Fragment() {
    private lateinit var binding: FragmentCheckReviewBinding
    private lateinit var mReviewViewModel: ReviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflating the layout for this fragment
        binding = DataBindingUtil
            .inflate(inflater,R.layout.fragment_check_review, container, false)

        //Initializing viewModel
        mReviewViewModel = ViewModelProvider(this).get(ReviewViewModel::class.java)

        //Stores the id of the personNumber passed by previous fragment
        val personId = CheckReviewArgs.fromBundle(requireArguments()).itemId

        binding.checkReviewRecyclerView.setHasFixedSize(true)
        //RecyclerView adapter initialization
        val reviewAdapter = CheckReviewAdapter()
        binding.checkReviewRecyclerView.adapter = reviewAdapter
        binding.checkReviewRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        //Observing liveData of the ReviewViewModel class
        mReviewViewModel.reviewList.observe(viewLifecycleOwner, {
            reviewAdapter.setData(it,personId)
        })

        return binding.root
    }

}