package com.damian.rickmorty.data.network.client

import io.ktor.client.HttpClient

interface KtorClientAdapter {
    val client: HttpClient
}