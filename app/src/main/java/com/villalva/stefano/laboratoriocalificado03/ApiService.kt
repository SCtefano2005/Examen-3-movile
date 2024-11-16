package com.villalva.stefano.laboratoriocalificado03

import retrofit2.http.GET

interface ApiService {
    @GET("list/teacher-b")
    suspend fun getProfesores(): ResponseWrapper

}
