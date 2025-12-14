package com.example.cryptotracker

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// 1. Daftar Menu: Kita mau minta data apa aja?
interface CryptoApi {
    // Ini alamat lengkap request-nya (market cap, mata uang IDR, urutan teratas)
    @GET("coins/markets?vs_currency=idr&order=market_cap_desc&per_page=10&page=1&sparkline=false")
    fun getCoins(): Call<List<CryptoModel>>
}

// 2. Kantor Kurir: Settingan koneksinya
object RetrofitClient {
    private const val BASE_URL = "https://api.coingecko.com/api/v3/"

    val instance: CryptoApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(CryptoApi::class.java)
    }
}