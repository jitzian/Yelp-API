package com.example.yelpcode.domain.services.offline

import com.example.yelpcode.data.remote.model.Businesse
import com.example.yelpcode.domain.model.BusinessModel

interface Mapper {
    fun toBusinessModel(
        businessResponse: Businesse
    ): BusinessModel
}