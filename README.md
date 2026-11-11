# Dokumentasi Sistem Penyewaan E-Bike (Implementasi UML Class Diagram)

Folder ini berisi implementasi lengkap **UML Class Diagram Sistem Penyewaan E-Bike** ke dalam bahasa pemrograman **Java** menggunakan prinsip-prinsip **Object-Oriented Programming (OOP)**.

---

## 1. Gambaran Besar Sistem

Sistem ini memodelkan layanan penyewaan sepeda listrik (**E-Bike**) di stasiun kampus:
- **Pengguna** terbagi menjadi dua peran: **Pelanggan** (menyewa E-Bike, isi saldo, bayar, terima notifikasi) dan **Admin** (kelola unit E-Bike, tarif, promo, dan pemeliharaan/servis).
- Setiap **Stasiun** menampung beberapa unit **EBike** (*Aggregation*), dan setiap **EBike** memiliki perangkat **GPS** yang melekat di dalamnya (*Composition*).
- Transaksi **Sewa** menghitung durasi dan biaya berdasarkan **Tarif**, dapat menggunakan **Promo**, serta dibayar menggunakan salah satu **MetodePembayaran** (**PembayaranSaldo**, **PembayaranQRIS**, atau **PembayaranKartu**).

---

## 2. Pemetaan UML Class Diagram ke Kode Java

### A. Simbol Visibilitas & Jenis Class di UML

| Notasi di Diagram UML | Arti di Java | Contoh pada Kode |
|---|---|---|
| `(A) NamaClass` *(cetak miring)* | `abstract class` | `public abstract class Pengguna` |
| `(C) NamaClass` | `class` konkret | `public class Pelanggan` |
| `- namaAtribut : tipe` | `private` field | `private double saldo;` |
| `+ namaMethod() : tipe` | `public` method | `public double getSaldo()` |

---

### B. Daftar Class, Atribut, dan Method

| No | Nama File / Class | Jenis | Atribut (`private`) | Method Utama (`public`) |
|---|---|---|---|---|
| 1 | [`Pengguna.java`](Pengguna.java) | Abstract Class | `idPengguna`, `nama`, `email`, `password` | `login()`, `logout()`, `getNama()` |
| 2 | [`Pelanggan.java`](Pelanggan.java) | Subclass `Pengguna` | `saldo`, `statusAkun`, `daftarNotifikasi`, `daftarSewa` | `tambahSaldo(double)`, `mulaiSewa(int)`, `getSaldo()` |
| 3 | [`Admin.java`](Admin.java) | Subclass `Pengguna` | `idAdmin`, `daftarPemeliharaan` | `tambahEBike()`, `hapusEBike(int)`, `ubahTarif()` |
| 4 | [`Stasiun.java`](Stasiun.java) | Class | `idStasiun`, `nama`, `lokasi`, `kapasitas`, `daftarEBike` | `cekSlotTersedia()`, `tambahEBike()`, `kembalikanEBike()` |
| 5 | [`EBike.java`](EBike.java) | Class | `idEBike`, `merk`, `status`, `baterai`, `gps` | `cekKetersediaan()`, `ubahStatus(String)`, `getBaterai()` |
| 6 | [`GPS.java`](GPS.java) | Class | `latitude`, `longitude` | `ambilLokasi()`, `lacakEBike()` |
| 7 | [`Notifikasi.java`](Notifikasi.java) | Class | `idNotifikasi`, `pesan`, `waktu` | `kirim()`, `tandaiDibaca()` |
| 8 | [`Sewa.java`](Sewa.java) | Class | `idSewa`, `waktuMulai`, `waktuSelesai`, `durasiMenit`, `totalBiaya`, `status` | `mulai()`, `akhiri()`, `hitungDurasi()`, `hitungBiaya()` |
| 9 | [`Pemeliharaan.java`](Pemeliharaan.java) | Class | `idPemeliharaan`, `tanggal`, `deskripsi`, `status` | `catatKerusakan()`, `selesaikanPerbaikan()` |
| 10 | [`Tarif.java`](Tarif.java) | Class | `tarifPerMenit`, `biayaAwal` | `hitungBiaya(int)`, `setTarif(double)` |
| 11 | [`Promo.java`](Promo.java) | Class | `idPromo`, `kodePromo`, `diskon`, `tanggalKedaluwarsa` | `validasiPromo()`, `hitungDiskon(double)` |
| 12 | [`MetodePembayaran.java`](MetodePembayaran.java) | Abstract Class | `idPembayaran`, `nominal`, `status` | `prosesPembayaran()`, `refund()` |
| 13 | [`PembayaranSaldo.java`](PembayaranSaldo.java) | Subclass `MetodePembayaran` | *(mewarisi atribut induk)* | `prosesPembayaran()`, `refund()` |
| 14 | [`PembayaranQRIS.java`](PembayaranQRIS.java) | Subclass `MetodePembayaran` | `kodeQR` | `prosesPembayaran()`, `refund()` |
| 15 | [`PembayaranKartu.java`](PembayaranKartu.java) | Subclass `MetodePembayaran` | `nomorKartu` | `prosesPembayaran()`, `refund()` |
| 16 | [`Main.java`](Main.java) | Main Program | Scanner & State Sistem | `main(String[] args)` (Menu CLI Interaktif) |

---

## 3. Penjelasan Relasi Antar-Class di Diagram

### 1. Inheritance / Generalization (`──▷`)
Panah segitiga putih menunjukkan pewarisan sifat dari parent class ke child class menggunakan kata kunci `extends`:
- `Pelanggan extends Pengguna` dan `Admin extends Pengguna`
- `PembayaranSaldo extends MetodePembayaran`, `PembayaranQRIS extends MetodePembayaran`, dan `PembayaranKartu extends MetodePembayaran`

### 2. Composition (`──◆`)
Belah ketupat hitam penuh menunjukkan kepemilikan kuat (*strong ownership*):
- **`EBike` (`1`) ◆── (`1`) `GPS`**: Objek `GPS` langsung dibuat di dalam constructor `EBike` (`this.gps = new GPS(latitude, longitude);`).
- **`Sewa` (`1`) ◆── (`0..1`) `MetodePembayaran`**: Pembayaran terikat langsung pada transaksi `Sewa`.

### 3. Aggregation (`──◇`)
Belah ketupat putih kosong menunjukkan hubungan koleksi (*whole-part*) di mana objek bagian tetap bisa eksis meskipun terlepas dari wadahnya:
- **`Stasiun` (`1`) ◇── (`0..*`) `EBike`**: `Stasiun` menyimpan `List<EBike> daftarEBike`. Unit E-Bike dapat dipinjam keluar stasiun dan dikembalikan lagi.

### 4. Association (`───` dan `───>`)
Garis penghubung antar-class dengan multiplisitas (`1`, `0..1`, `0..*`):
- `Pelanggan` (`1`) memiliki `0..*` `Notifikasi` dan `0..*` `Sewa`
- `Admin` (`1`) mencatat `0..*` `Pemeliharaan` pada `EBike` (`1`)
- `Sewa` (`0..*`) mengacu pada `1` `Tarif` dan `0..1` `Promo`

---

## 4. Empat Pilar OOP yang Diterapkan

1. **Encapsulation (Enkapsulasi)**
   Semua atribut di setiap class dideklarasikan `private` (sesuai tanda `-` pada diagram) dan diakses/diubah melalui method `public` (getter & setter).
2. **Abstraction (Abstraksi)**
   Class `Pengguna` dan `MetodePembayaran` dibuat sebagai `abstract class`. Khusus pada `MetodePembayaran`, method `prosesPembayaran()` dan `refund()` didefinisikan sebagai `abstract` karena cara bayar tiap metode berbeda.
3. **Inheritance (Pewarisan)**
   `Pelanggan` dan `Admin` mewarisi atribut `idPengguna`, `nama`, `email`, `password` serta method `login()` dan `logout()` dari `Pengguna` menggunakan `super(...)`.
4. **Polymorphism (Polimorfisme)**
   Saat menyelesaikan sewa di `Main.java`, variabel bertipe `MetodePembayaran` dapat diisi oleh objek `PembayaranSaldo`, `PembayaranQRIS`, maupun `PembayaranKartu`, lalu memanggil `metode.prosesPembayaran()` dengan perilaku berbeda sesuai class turunannya (*method overriding*).

---

## 5. Cara Compile dan Menjalankan Program

Buka terminal, masuk ke folder `Tugasdiagramclass`, lalu jalankan perintah berikut:

```bash
cd Tugasdiagramclass
javac *.java
java Main
```

### Panduan Menu Interaktif (`Main.java`)
- **Pilih `1` (Login sebagai Pelanggan)**:
  - Cek profil & saldo, top up saldo, lihat daftar E-Bike di stasiun, mulai menyewa E-Bike, akhiri sewa (input durasi, pakai kode promo `MHSHEMAT` atau `POTONG5K`, pilih bayar via Saldo/QRIS/Kartu), ajukan refund, baca notifikasi, atau lacak koordinat GPS.
- **Pilih `2` (Login sebagai Admin)**:
  - Tambah E-Bike baru ke stasiun, hapus E-Bike, ubah tarif per menit & biaya awal, catat kerusakan E-Bike (status otomatis menjadi `"Perbaikan"` sehingga tidak bisa disewa), selesaikan perbaikan, dan tambah kode promo baru.
- **Pilih `3` (Info Sistem)**:
  - Melihat ringkasan ketersediaan slot stasiun, daftar E-Bike, tarif aktif, dan daftar kode promo.

