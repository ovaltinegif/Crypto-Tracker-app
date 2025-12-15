package com.example.cryptotracker

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.text.NumberFormat
import java.util.Locale

// Tambahkan parameter 'onItemClick' di sini agar Activity bisa menangani klik
class CryptoAdapter(
    private val cryptoList: List<CryptoModel>,
    private val onItemClick: (CryptoModel) -> Unit // Callback function
) : RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>() {

    class CryptoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvSymbol: TextView = itemView.findViewById(R.id.tvSymbol)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        val tvChange: TextView = itemView.findViewById(R.id.tvChange)
        val imgLogo: ImageView = itemView.findViewById(R.id.imgLogo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_crypto, parent, false)
        return CryptoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        val coin = cryptoList[position]

        holder.tvName.text = coin.name
        holder.tvSymbol.text = coin.symbol.uppercase()

        // Format Harga
        val formatRp = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
        holder.tvPrice.text = formatRp.format(coin.current_price)

        // --- BAGIAN BARU: Logika Warna Dinamis ---
        val change = coin.price_change_percentage_24h

        if (change >= 0) {
            // NAIK: Warna Hijau Neon (#00E676) & Simbol Panah Atas
            holder.tvChange.text = "+${String.format("%.2f", change)}% ▲"
            holder.tvChange.setTextColor(Color.parseColor("#00E676"))
        } else {
            // TURUN: Warna Merah Terang (#FF5252) & Simbol Panah Bawah
            holder.tvChange.text = "${String.format("%.2f", change)}% ▼"
            holder.tvChange.setTextColor(Color.parseColor("#FF5252"))
        }
        // ------------------------------------------

        Glide.with(holder.itemView.context)
            .load(coin.image)
            .into(holder.imgLogo)

        holder.itemView.setOnClickListener {
            onItemClick(coin)
        }
    }

    override fun getItemCount(): Int = cryptoList.size
}