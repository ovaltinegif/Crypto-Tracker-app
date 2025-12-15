package com.example.cryptotracker

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CryptoViewModel : ViewModel() {
    private val _cryptoList = MutableLiveData<List<CryptoModel>>()
    val cryptoList: LiveData<List<CryptoModel>> = _cryptoList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun fetchCoins() {
        _isLoading.value = true // Tampilkan loading
        RetrofitClient.instance.getCoins().enqueue(object : Callback<List<CryptoModel>> {
            override fun onResponse(call: Call<List<CryptoModel>>, response: Response<List<CryptoModel>>) {
                _isLoading.value = false // Sembunyikan loading
                if (response.isSuccessful) {
                    _cryptoList.value = response.body()
                } else {
                    _errorMessage.value = "Gagal memuat data: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                _isLoading.value = false
                _errorMessage.value = "Koneksi Error: ${t.message}"
                Log.e("API_ERROR", t.message.toString())
            }
        })
    }
}