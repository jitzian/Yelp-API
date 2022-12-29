package com.example.yelpcode.domain.services.offline

import com.example.yelpcode.data.remote.model.ApiBusiness
import com.example.yelpcode.domain.model.BusinessModel

fun ApiBusiness.asBusinessModel(): BusinessModel = BusinessModel(
    id = id,
    name = name,
    imageUrl = imageUrl,
    isClosed = isClosed,
    categories = categories.map { item ->
        item.title
    },
    price = price ?: "$",
    rating = rating,
    phone = phone,
    address = apiLocation?.address1
)