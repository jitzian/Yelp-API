package com.example.yelpcode.utils

/**
 * Small function to retrieve TAG and avoid unnecessary declarations on each class
 * */
inline fun <reified T> T.TAG(): String = T::class.java.simpleName