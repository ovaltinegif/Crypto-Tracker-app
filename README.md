# 🪙 Crypto Tracker Android App

Aplikasi monitoring harga aset kripto (Bitcoin, Ethereum, dll) secara *real-time* dengan konversi Rupiah (IDR), tampilan modern, dan arsitektur MVVM.

## ✨ Fitur Utama (New)
* 🚀 **Real-time Data:** Mengambil data harga live dari CoinGecko API.
* 🔄 **Swipe to Refresh:** Tarik layar ke bawah untuk memperbarui data seketika.
* 📱 **Detail Screen:** Klik koin untuk melihat info lengkap (Market Cap, Volume, High/Low 24h).
* 🎨 **Modern UI:** Tampilan kartu dengan efek gradasi "glowing" dan indikator warna dinamis (Hijau/Merah).
* 🏗️ **MVVM Architecture:** Kode lebih rapi, stabil, dan tahan terhadap rotasi layar.

## 🛠️ Tech Stack & Library
Project ini dibangun menggunakan teknologi Android modern:
* **Bahasa:** Kotlin
* **Arsitektur:** MVVM (Model-View-ViewModel)
* **Networking:** Retrofit + Gson
* **Image Loading:** Glide
* **Asynchronous:** LiveData & Coroutines
* **Plugin:** Kotlin Parcelize (untuk pengiriman data antar layar)

## 📋 Prasyarat (Requirements)
Sebelum menjalankan project, pastikan sudah terinstall:
* **Android Studio** (Versi terbaru disarankan).
* **JDK 17** (Sesuai konfigurasi Gradle).
* **Koneksi Internet** (Wajib, untuk fetch data API).

## 🚀 Cara Menjalankan
1. Clone repository ini.
2. Buka di Android Studio.
3. Tunggu proses **Gradle Sync** selesai.
4. Tekan tombol **Run** ▶️.
