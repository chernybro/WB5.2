package com.chernybro.wb52.data.remote.service

import com.chernybro.wb52.data.remote.models.HeroDTO
import com.chernybro.wb52.domain.models.HeroDetailsItem
import com.chernybro.wb52.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path

interface HeroListApi {
    @GET("/api/${Constants.TOKEN}/{id}")
    suspend fun getHero(@Path("id") id: Int): HeroDTO
}