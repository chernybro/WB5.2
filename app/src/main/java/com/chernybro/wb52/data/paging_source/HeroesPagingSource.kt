package com.chernybro.wb52.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chernybro.wb52.data.remote.models.toHeroItem
import com.chernybro.wb52.data.remote.service.HeroListApi
import com.chernybro.wb52.domain.models.HeroItem
import kotlin.math.max

class HeroesPagingSource(
    private val heroListApi: HeroListApi,
) : PagingSource<Int, HeroItem>() {

    companion object {
        private const val STARTING_KEY = 1
        private const val ITEMS_PER_PAGE = 10
    }

    private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)

    override fun getRefreshKey(state: PagingState<Int, HeroItem>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val chatPreview = state.closestItemToPosition(anchorPosition) ?: return null
        return ensureValidKey(key = chatPreview.id.toInt() - ITEMS_PER_PAGE)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HeroItem> {
        val start = params.key ?: STARTING_KEY

        val range = start.until(start + params.loadSize)

        val loadList = mutableListOf<HeroItem>()
            range.map {
                loadList.add(heroListApi.getHero(it).toHeroItem())
            }
        return LoadResult.Page(
            data = loadList,
            prevKey = when (start) {
                STARTING_KEY -> null
                else -> ensureValidKey(key = range.first - params.loadSize)
            },
            nextKey = range.last + 1
        )
    }
}