package com.damian.rickmorty.presentation.ui.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.damian.rickmorty.common.failed
import com.damian.rickmorty.domain.model.Character
import com.damian.rickmorty.domain.usecase.GetCharactersUseCase

class CharactersPagingSource(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val filter: String?,
) : PagingSource<Int, Character>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val currentPage = params.key ?: 0
        val characters = getCharactersUseCase(page = currentPage, filter = filter)
            .failed { return LoadResult.Error(it) }

        return LoadResult.Page(
            data = characters.result,
            prevKey = characters.prevPage,
            nextKey = characters.nextPage,
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition
    }
}