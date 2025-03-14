package com.example.taller1.data.network

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.example.taller1.data.model.Driver
import org.json.JSONArray
import org.json.JSONObject

object VolleyClient {
    private const val BASE_URL = "https://api.openf1.org/v1/drivers"
    private const val FLAG_API_URL = "https://restcountries.com/v3.1/alpha/"

    fun getDrivers(context: Context, sessionKey: Int, onResult: (List<Driver>?, String?) -> Unit) {
        val url = "$BASE_URL?session_key=$sessionKey"

        val request = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response: JSONArray ->
                try {
                    val drivers = mutableListOf<Driver>()
                    for (i in 0 until response.length()) {
                        val jsonObject: JSONObject = response.getJSONObject(i)
                        val driver = Driver(
                            driver_number = jsonObject.getInt("driver_number"),
                            full_name = jsonObject.getString("full_name"),
                            country_code = jsonObject.getString("country_code"),
                            name_acronym = jsonObject.getString("name_acronym"),
                            team_colour = jsonObject.getString("team_colour"),
                            team_name = jsonObject.getString("team_name"),
                            headshot_url = jsonObject.getString("headshot_url")
                        )
                        drivers.add(driver)
                    }
                    onResult(drivers, null)
                } catch (e: Exception) {
                    onResult(null, e.message)
                }
            },
            { error ->
                onResult(null, error.message)
            }
        )

        VolleySingleton.getInstance(context).add(request)
    }

    fun getCountryFlag(context: Context, countryCode: String, onResult: (String?) -> Unit) {
        val url = "$FLAG_API_URL$countryCode"

        val request = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response: JSONArray ->
                try {
                    val flags = response.getJSONObject(0).getJSONObject("flags")
                    val flagUrl = flags.getString("png")
                    onResult(flagUrl)
                } catch (e: Exception) {
                    onResult(null)
                }
            },
            { _ -> onResult(null) }
        )

        VolleySingleton.getInstance(context).add(request)
    }
}