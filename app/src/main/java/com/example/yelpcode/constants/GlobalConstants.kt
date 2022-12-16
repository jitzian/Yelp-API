package com.example.yelpcode.constants

class GlobalConstants {
    companion object {
        //Endpoint constants (https://api.yelp.com/v3/businesses/search)
        const val BASE_URL = "https://api.yelp.com/v3/"
        const val AUTH_HEADER = "Authorization"

        //API KEY (TODO: change location)
        const val API_KEY =
            "2ROaa2Rh9qu3WVTCms8FoVE4mSfHQHC7QJua95-kKT-PqzIlLSrs4tmHVdtdFw_66-JNfRiJmbCByHTvFNy5dQq-tpfS4FrPpupIzKlgELR3br-r5trpeFhrCRgwWnYx"

        const val MAX_TIME_OUT = 10000L
        const val EMPTY_SPACE = " "

    }
}