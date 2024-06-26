package com.damian.rickmorty.data.network.service

import com.damian.rickmorty.BuildConfig
import com.damian.rickmorty.common.Failure
import com.damian.rickmorty.data.network.client.KtorClientAdapter
import com.damian.rickmorty.data.network.model.CharacterDTO
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
        filter: String?,
    ): Result<GetCharactersResponseDTO> = httpClient.client.get {
        url(BuildConfig.RM_BASE_URL + Route.CHARACTERS)
        parameter("page", page)
        filter?.let { parameter("name", filter) }
        contentType(ContentType.Application.Json)
    }.let { response ->
        if (response.status == HttpStatusCode.OK) {
            try {
                Result.success(response.body<GetCharactersResponseDTO>())
            } catch (e: JsonConvertException) {
                Timber.e("SERIALIZATION_FAILURE", e)
                Result.failure(Failure.NetworkSerializationFailure)
            }
        } else if (response.status == HttpStatusCode.NotFound) {
            Result.failure(Failure.NoCharactersFound)
        } else {
            Timber.e("NETWORK_FAILURE", response)
            Result.failure(Failure.NetworkFailure(response.status.value))
        }
    }

    override suspend fun getCharacterById(characterId: Int): Result<CharacterDTO> = httpClient.client.get {
        url(BuildConfig.RM_BASE_URL + Route.CHARACTERS + characterId)
        contentType(ContentType.Application.Json)
    }.let { response ->
        if (response.status == HttpStatusCode.OK) {
            try {
                Result.success(response.body<CharacterDTO>())
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