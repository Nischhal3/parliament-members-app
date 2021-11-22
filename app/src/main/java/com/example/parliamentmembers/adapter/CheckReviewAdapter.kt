package com.example.parliamentmembers.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.parliamentmembers.R
import com.example.parliamentmembers.model.Review

/**
 * name: Nischhal Shrestha
 * date: 9/10/2021
 *
 * RecyclerView Adapter for CheckReview Fragment
 */

class CheckReviewAdapter : RecyclerView.Adapter<CheckReviewAdapter.ViewHolder>() {
    private var reviewList = emptyList<Review>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val comment: TextView = itemView.findViewById(R.id.user_comment)
        val rating: TextView = itemView.findViewById(R.id.user_rating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_review_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = reviewList[position]
        val userComment = "Comment: ${currentItem.comment}"
        val userRating = "Rating: ${currentItem.rating}"
        holder.comment.text = userComment
        holder.rating.text = userRating
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    /**
     * @param reviewList holds the value of reviewList
     * @param personId holds the value of personNumber passed from previous fragment
     */
    fun setData(reviewList: List<Review>, personId: Int) {
        //Filtering review list through person id
        this.reviewList = reviewList.filter { it.personId == personId }
        notifyDataSetChanged()
    }
}