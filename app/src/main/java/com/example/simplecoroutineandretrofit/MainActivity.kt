package com.example.simplecoroutineandretrofit

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

     val BASE_URL = "https://jsonplaceholder.typicode.com"

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service = RetrofitFactory(BASE_URL).makeRetrofitService()

        CoroutineScope(Dispatchers.IO).launch {

            withContext(Dispatchers.Main) {
                val response = async {  service.getPosts()}
                delay(8000)
                response.await()
                Toast.makeText(this@MainActivity, "Successful", Toast.LENGTH_SHORT).show()

                val response1 = async { service.getPosts1() }
                delay(5000)
                response1.await()
                Toast.makeText(this@MainActivity, "Successful1", Toast.LENGTH_SHORT).show()
            }

        }
        CoroutineScope(Dispatchers.Main).launch {
            val res = async { service.getPosts2() }
            delay(10000)
            res.await()
            Toast.makeText(this@MainActivity, "Successful2", Toast.LENGTH_SHORT).show()
        }
    }

    class RetrofitFactory(var Base: String?) {

        fun makeRetrofitService(): IRetrofitService {
            return Retrofit.Builder().baseUrl(Base)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build().create(IRetrofitService::class.java)
        }
    }
}