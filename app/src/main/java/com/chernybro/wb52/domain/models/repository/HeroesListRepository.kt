package com.chernybro.wb52.domain.models.repository

import com.chernybro.wb52.data.paging_source.HeroesPagingSource
import com.chernybro.wb52.data.remote.service.HeroListApi
import javax.inject.Inject


class HeroesListRepository @Inject constructor(
    private val heroListApi: HeroListApi,
) {
    fun getHeroesPagingSource() = HeroesPagingSource(heroListApi)
}