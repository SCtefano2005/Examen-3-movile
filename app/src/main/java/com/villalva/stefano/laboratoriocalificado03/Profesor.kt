package com.villalva.stefano.laboratoriocalificado03

data class Profesor(
    val name: String,
    val last_name: String,
    val phone_number: String,
    val email: String,
    val image_url: String
)

data class ResponseWrapper(
    val teachers: List<Profesor>
)
