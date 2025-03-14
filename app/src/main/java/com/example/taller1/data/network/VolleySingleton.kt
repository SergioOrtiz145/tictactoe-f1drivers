package com.example.taller1.data.network

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

object VolleySingleton {
    private var requestQueue: RequestQueue? = null

    fun getInstance(context: Context): RequestQueue {
        return requestQueue ?: Volley.newRequestQueue(context.applicationContext).also {
            requestQueue = it
        }
    }
}