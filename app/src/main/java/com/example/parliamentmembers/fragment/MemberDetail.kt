package com.example.parliamentmembers.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.parliamentmembers.R
import com.example.parliamentmembers.databinding.FragmentMemberDetailBinding
import com.example.parliamentmembers.viewModel.ParliamentMemberViewModel
import com.example.parliamentmembers.viewModel.ReviewViewModel
import com.squareup.picasso.Picasso

/**
 * name: Nischhal Shrestha
 * date: 9/10/2021
 *
 *Fragment Observes ParliamentMemberViewModel class and displays member details
 */
class MemberDetail : Fragment() {
    //Variable initialization
    private val baseUrl = "https://avoindata.eduskunta.fi/"
    private lateinit var binding: FragmentMemberDetailBinding
    private lateinit var mParliamentMemberViewModel: ParliamentMemberViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflating the layout for this fragment
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_member_detail, container, false)
        //Stores the id of the personNumber passed by previous fragment
        val id = MemberDetailArgs.fromBundle(requireArguments()).itemId

        //Setting onClick listener for add button which navigates to addReview fragment
        binding.addReview.setOnClickListener {
            view?.findNavController()?.navigate(MemberDetailDirections.actionReviewToAddReview(id))
        }
        //Setting onClick listener for add button which navigates to checkReview fragment
        binding.checkReview.setOnClickListener {
            view?.findNavController()
                ?.navigate(MemberDetailDirections.actionReviewToCheckReview(id))
        }

        //Initializing view models
        mParliamentMemberViewModel = ViewModelProvider(this)
            .get(ParliamentMemberViewModel::class.java)

        //Observing liveData of the ParliamentMemberViewModel class
        mParliamentMemberViewModel.parliamentMemberList.observe(viewLifecycleOwner, { response ->
            response.map {
                if (it.personNumber == id) {
                    //Checking if member is minister or not
                    val minister = if (!it.minister) {
                        "Member"
                    } else {
                        "Minister"
                    }

                    val twitter = if (it.twitter.isEmpty()) {
                        "Unavailable"
                    } else {
                        it.twitter
                    }
                    //Using string resources
                    val name = getString(R.string.name, it.first, it.last)
                    val birthday = getString(R.string.birthday, it.bornYear.toString())
                    val constituency = getString(R.string.constituency, it.constituency)
                    val party = getString(R.string.party, it.party)
                    val type = getString(R.string.type, minister)
                    val link = getString(R.string.twitter, twitter)

                    binding.name.text = name
                    binding.birthDay.text = birthday
                    binding.region.text = constituency
                    binding.partyName.text = party
                    binding.memberType.text = type
                    binding.twitterLink.text = link
                    //Uploading picture to the imageView
                    Picasso.get().load(baseUrl + it.picture).into(binding.image)
                }
            }
        })
        return binding.root
    }
}
