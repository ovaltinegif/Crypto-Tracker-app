package com.example.cryptotracker

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide // Import Glide
import java.text.NumberFormat
import java.util.Locale

class CryptoAdapter(private val cryptoList: List<CryptoModel>) :
    RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>() {

    class CryptoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvSymbol: TextView = itemView.findViewById(R.id.tvSymbol)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        val tvChange: TextView = itemView.findViewById(R.id.tvChange) // Baru
        val imgLogo: ImageView = itemView.findViewById(R.id.imgLogo)   // Baru
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

        // 1. Format Harga (Rupiah)
        val formatRp = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
        holder.tvPrice.text = formatRp.format(coin.current_price)

        // 2. Logika Warna & Persen (Hijau vs Merah)
        val change = coin.price_change_percentage_24h
        holder.tvChange.text = String.format("%.2f%%", change)

        if (change >= 0) {
            holder.tvChange.setTextColor(Color.parseColor("#4CAF50")) // Hijau
            holder.tvChange.text = "+${holder.tvChange.text}" // Tambah tanda +
        } else {
            holder.tvChange.setTextColor(Color.parseColor("#F44336")) // Merah
        }

        // 3. Load Gambar dari Internet pake Glide
        Glide.with(holder.itemView.context)
            .load(coin.image)
            .into(holder.imgLogo)
    }

    override fun getItemCount(): Int = cryptoList.size
}