package com.example.cryptotracker

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CryptoAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    // Memanggil ViewModel yang sudah dibuat di file sebelah
    private val viewModel: CryptoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Setup UI
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)
        swipeRefresh = findViewById(R.id.swipeRefresh)

        recyclerView.layoutManager = LinearLayoutManager(this)

        // 2. Observasi (Pantau) Data
        observeViewModel()

        // 3. Ambil Data Awal
        viewModel.fetchCoins()

        // 4. Aksi Swipe Refresh
        swipeRefresh.setOnRefreshListener {
            viewModel.fetchCoins()
        }
    }

    private fun observeViewModel() {
        viewModel.cryptoList.observe(this) { list ->
            // Saat data masuk, masukkan ke Adapter + Fitur Klik
            adapter = CryptoAdapter(list) { coin ->
                Toast.makeText(this, "Kamu memilih ${coin.name}", Toast.LENGTH_SHORT).show()
            }
            recyclerView.adapter = adapter
        }

        viewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                progressBar.visibility = android.view.View.VISIBLE
            } else {
                progressBar.visibility = android.view.View.GONE
                swipeRefresh.isRefreshing = false
            }
        }

        viewModel.errorMessage.observe(this) { message ->
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }
}