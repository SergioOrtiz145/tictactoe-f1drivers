package com.example.taller1.data.network

import com.example.taller1.data.model.Driver
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.result.Result

object FuelClient {
    private const val BASE_URL = "https://api.openf1.org/v1/drivers"
    private const val FLAG_API_URL = "https://restcountries.com/v3.1/alpha/"

    fun getDrivers(sessionKey: Int, onResult: (List<Driver>?, String?) -> Unit) {
        val url = "$BASE_URL?session_key=$sessionKey"

        url.httpGet().responseObject<List<Driver>> { _, _, result ->
            when (result) {
                is Result.Success -> onResult(result.get(), null)
                is Result.Failure -> onResult(null, result.error.message)
            }
        }
    }
    fun getCountryFlag(countryCode: String, onResult: (String?) -> Unit) {
        val url = "$FLAG_API_URL$countryCode"

        url.httpGet().responseObject<List<Map<String, Any>>> { _, _, result ->
            when (result) {
                is Result.Success -> {
                    val response = result.get()
                    val flags = response.firstOrNull()?.get("flags") as? Map<*, *>
                    val flagUrl = flags?.get("png") as? String
                    onResult(flagUrl)
                }
                is Result.Failure -> onResult(null)
            }
        }
    }
}
