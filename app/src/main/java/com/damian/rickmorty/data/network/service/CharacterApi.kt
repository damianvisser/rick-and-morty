package com.damian.rickmorty.data.network.service

import com.damian.rickmorty.BuildConfig
import com.damian.rickmorty.common.Failure
import com.damian.rickmorty.data.network.client.KtorClientAdapter
import com.damian.rickmorty.data.network.model.GetCharactersResponseDTO
import com.damian.rickmorty.data.network.route.Route
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.JsonConvertException
import timber.log.Timber
import javax.inject.Inject

class CharacterApi @Inject constructor(
    private val httpClient: KtorClientAdapter,
) : CharacterService {
    override suspend fun getCharactersPaginated(
        page: Int,
    ): Result<GetCharactersResponseDTO> = httpClient.client.get {
        url(BuildConfig.RM_BASE_URL + Route.GET_ALL_CHARACTERS)
        parameter("page", page)
        contentType(ContentType.Application.Json)
    }.let { response ->
        if (response.status == HttpStatusCode.OK) {
            try {
                Result.success(response.body<GetCharactersResponseDTO>())
            } catch (e: JsonConvertException) {
                Timber.e("SERIALIZATION_FAILURE", e)
                Result.failure(Failure.NetworkSerializationFailure)
            }
        } else {
            Timber.e("NETWORK_FAILURE", response)
            Result.failure(Failure.NetworkFailure(response.status.value))
        }
    }
}