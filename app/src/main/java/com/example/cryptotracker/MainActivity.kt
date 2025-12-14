package com.example.cryptotracker

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    // Siapkan variabel untuk RecyclerView
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Kenalan sama RecyclerView di layar
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 2. Ambil data
        fetchCryptoData()
    }

    private fun fetchCryptoData() {
        RetrofitClient.instance.getCoins().enqueue(object : Callback<List<CryptoModel>> {
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
                if (response.isSuccessful) {
                    val cryptoList = response.body()

                    // 3. Kalau data ada, serahkan ke Adapter (Koki)
                    if (cryptoList != null) {
                        val adapter = CryptoAdapter(cryptoList)
                        recyclerView.adapter = adapter
                    }
                }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                Log.e("CEK_DATA", "Error: ${t.message}")
            }
        })
    }
}