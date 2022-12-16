package com.example.yelpcode.domain.model

data class BusinessModel(
    val id: String? = "",
    val name: String? = "",
    val imageUrl: String? = "",
    val isClosed: Boolean? = false,
    val categories: List<String>? = emptyList(),
    val price: String = "$-",
    val rating: Double? = 0.0,
    val phone: String? = "N/A",
    val address: String? = "N/A"
)