package com.magrathea.data.repository.pagingsource

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.magrathea.data.datasource.remote.GameRemoteDataSource
import com.magrathea.data.mapper.toModel
import com.magrathea.domain.model.GameModel
import java.io.IOException

class GamesPagingSource(
    private val gameRemoteDataSource: GameRemoteDataSource,
) : PagingSource<Int, GameModel>() {
    override fun getRefreshKey(state: PagingState<Int, GameModel>): Int? {
        return state.anchorPosition
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GameModel> {
        return try {
            val currentPage = params.key ?: 0
            val movies = gameRemoteDataSource.loadGames(
                page = currentPage,
                size = 10,
            )
            LoadResult.Page(
                data = movies.content.map { it.toModel() },
                prevKey = if (params is LoadParams.Append) params.key else null,
                nextKey = if (params is LoadParams.Prepend) params.key else null
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}