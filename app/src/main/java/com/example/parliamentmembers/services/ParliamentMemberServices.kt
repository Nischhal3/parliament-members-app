package com.example.parliamentmembers.services

import com.example.parliamentmembers.model.ParliamentMember
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

/**
 * name: Nischhal Shrestha
 * date: 2/10/2021
 *
 * Fetching data from the server
 * Initializing Retrofit and Moshi
 */

private const val BASE_URL = "https://users.metropolia.fi/~peterh/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ParliamentMembersService {
    @GET("mps.json")
    suspend fun getParliamentMembers():List<ParliamentMember>
}

object ParliamentMembersApi{
    val retrofitService: ParliamentMembersService by lazy {
        retrofit.create(ParliamentMembersService::class.java)
    }
}