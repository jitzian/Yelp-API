package com.example.yelpcode.data.repository.business

import android.app.Application
import com.example.yelpcode.R
import com.example.yelpcode.constants.GlobalConstants
import com.example.yelpcode.data.remote.RestApi
import com.example.yelpcode.data.remote.model.ApiResult
import com.example.yelpcode.data.remote.model.Error
import com.example.yelpcode.domain.model.BusinessModel
import com.example.yelpcode.domain.repository.BusinessRepository
import com.example.yelpcode.domain.services.offline.asBusinessModel
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withTimeout
import retrofit2.HttpException
import javax.inject.Inject

class BusinessRepositoryImpl @Inject constructor(
    private val restApi: RestApi,
    private val application: Application
) : BusinessRepository {

    override suspend fun fetchBusiness(term: String): MutableList<BusinessModel?> {
        val data: ApiResult
        val listOfBusinessModel = mutableListOf<BusinessModel?>()
        try {
            withTimeout(GlobalConstants.MAX_TIME_OUT) {
                data = restApi.fetchBusiness(term)
                if (data.businesses.isNotEmpty()) {
                    for (i in data.businesses) {
                        listOfBusinessModel.add(i.asBusinessModel())
                    }
                }
            }
        } catch (tce: TimeoutCancellationException) {
            listOfBusinessModel.add(
                BusinessModel(
                    error = Error.Connectivity
                )
            )
        } catch (httpException: HttpException) {
            listOfBusinessModel.add(
                BusinessModel(
                    error = Error.Server(code = httpException.code())
                )
            )
        } catch (e: Exception) {
            listOfBusinessModel.add(
                BusinessModel(
                    error = Error.Unknown(
                        message = e.message ?: application.getString(R.string.n_a_TEXT)
                    )
                )
            )
        }
        return listOfBusinessModel
    }

    override suspend fun fetchBusinessById(id: String): BusinessModel {
        var businessData: BusinessModel
        try {
            withTimeout(GlobalConstants.MAX_TIME_OUT) {
                businessData = restApi.fetchBusinessById(id).asBusinessModel()
            }
        } catch (tce: TimeoutCancellationException) {
            businessData = BusinessModel(
                error = Error.Connectivity
            )
        } catch (httpException: HttpException) {
            businessData = BusinessModel(
                error = Error.Server(code = httpException.code())
            )
        } catch (e: Exception) {
            businessData = BusinessModel(
                error = Error.Unknown(
                    message = e.message ?: application.getString(R.string.n_a_TEXT)
                )
            )
        }
        return businessData
    }
}