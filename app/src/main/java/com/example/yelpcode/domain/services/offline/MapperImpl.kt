package com.example.yelpcode.domain.services.offline

import com.example.yelpcode.data.remote.model.Businesse
import com.example.yelpcode.domain.model.BusinessModel

/**
 * Object that will parse Api model to DTO model class [BusinessModel]
 * */

object MapperImpl : Mapper {
    override fun toBusinessModel(businessResponse: Businesse): BusinessModel {
        return BusinessModel(
            id = businessResponse.id,
            name = businessResponse.name,
            imageUrl = businessResponse.imageUrl,
            isClosed = businessResponse.isClosed,
            categories = businessResponse.categories.map { item ->
                item.title
            },
            price = businessResponse.price ?: "$",
            rating = businessResponse.rating,
            phone = businessResponse.phone,
            address = businessResponse.location?.address1
        )
    }
}
