package com.techjet.codingtask.apiclient.list

import com.techjet.codingtask.apiclient.ApiError
import com.techjet.codingtask.apiclient.ApiResponse
import com.techjet.codingtask.apiclient.RetrofitClient
import com.techjet.codingtask.apiclient.result
import com.techjet.codingtask.model.list.ListResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ListApi(private val tags: String, private val callback: Int = 1) {

    fun call(listener: RetrofitClient.Listener<ListResponse, ApiError>) {
        CoroutineScope(Dispatchers.IO).launch {
            val response: Flow<ApiResponse<ListResponse?>> =
                result { RetrofitClient.getClient().listAPI(tags, callback) }

            response.collect { apiResponse: ApiResponse<ListResponse?> ->
                when(apiResponse) {
                    is ApiResponse.Success -> {
                        val res: ListResponse? = apiResponse.response
                        res?.let {
                            listener.onResponse(res)
                        }
                    }

                    is ApiResponse.Failure -> {
                        CoroutineScope(Dispatchers.Main).launch {
                            listener.onError(apiResponse.error)
                        }
                    }

                    is ApiResponse.Loading -> {

                    }

                }
            }
        }
    }

}