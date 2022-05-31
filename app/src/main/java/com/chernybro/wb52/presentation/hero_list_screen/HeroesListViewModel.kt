package com.chernybro.wb52.presentation.hero_list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.chernybro.wb52.domain.models.HeroItem
import com.chernybro.wb52.domain.models.repository.HeroesListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val ITEMS_PER_PAGE = 2

@HiltViewModel
class HeroesListViewModel @Inject constructor(
    private val repository: HeroesListRepository
) : ViewModel() {

    var items: Flow<PagingData<HeroItem>> = Pager(
        config = PagingConfig(pageSize = ITEMS_PER_PAGE, enablePlaceholders = false),
        pagingSourceFactory = { repository.getHeroesPagingSource() }
    )
        .flow
        .cachedIn(viewModelScope)

}