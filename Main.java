import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Stasiun stasiun;
    private static Tarif tarif;
    private static List<Promo> daftarPromo = new ArrayList<>();
    private static Pelanggan pelanggan;
    private static Admin admin;
    private static int counterNotifikasi = 1;
    private static int counterPembayaran = 500;
    private static int counterPemeliharaan = 300;

    public static void main(String[] args) {
        inisialisasiDataAwal();

        boolean berjalan = true;
        while (berjalan) {
            System.out.println("\n=============================================");
            System.out.println("       SISTEM PENYEWAAN E-BIKE KAMPUS        ");
            System.out.println("=============================================");
            System.out.println("1. Login sebagai Pelanggan (" + pelanggan.getNama() + ")");
            System.out.println("2. Login sebagai Admin (" + admin.getNama() + ")");
            System.out.println("3. Lihat Info Stasiun, E-Bike, Tarif & Promo");
            System.out.println("0. Keluar Program");
            System.out.print("Pilih menu [0-3]: ");

            int pilihan = bacaInt();
            switch (pilihan) {
                case 1:
                    pelanggan.login();
                    menuPelanggan();
                    break;
                case 2:
                    admin.login();
                    menuAdmin();
                    break;
                case 3:
                    tampilkanInfoSistem();
                    break;
                case 0:
                    System.out.println("Terima kasih telah menggunakan Sistem Penyewaan E-Bike!");
                    berjalan = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private static void inisialisasiDataAwal() {
        admin = new Admin(1, "Budi Admin", "admin@ebike.id", "admin123", 101);
        pelanggan = new Pelanggan(2, "Fransiskus F. Sinaga", "frans@student.id", "pass123", 50000.0, "Aktif");

        stasiun = new Stasiun(1, "Stasiun Kampus UNDIP", "Tembalang, Semarang", 10);
        stasiun.tambahEBike(new EBike(201, "Polygon Kalosi", "Tersedia", 95, -7.0505, 110.4381));
        stasiun.tambahEBike(new EBike(202, "United Mini IO", "Tersedia", 80, -7.0510, 110.4390));
        stasiun.tambahEBike(new EBike(203, "Selis E-Max", "Tersedia", 60, -7.0515, 110.4395));

        tarif = new Tarif(500.0, 2000.0); // Biaya awal Rp2.000 + Rp500/menit
        daftarPromo.add(new Promo(1, "MHSHEMAT", 0.20, LocalDate.now().plusDays(14))); // Diskon 20%
        daftarPromo.add(new Promo(2, "POTONG5K", 5000.0, LocalDate.now().plusDays(7))); // Potongan Rp5.000
    }

    // =========================================================
    // MENU PELANGGAN
    // =========================================================
    private static void menuPelanggan() {
        boolean diMenuPelanggan = true;
        while (diMenuPelanggan) {
            System.out.println("\n---------------------------------------------");
            System.out.println(" MENU PELANGGAN - " + pelanggan.getNama());
            System.out.println(" Saldo: Rp" + pelanggan.getSaldo() + " | Status: " + pelanggan.getStatusAkun());
            System.out.println("---------------------------------------------");
            System.out.println("1. Cek Profil & Saldo");
            System.out.println("2. Top Up / Tambah Saldo");
            System.out.println("3. Lihat Daftar E-Bike di Stasiun");
            System.out.println("4. Mulai Sewa E-Bike");
            System.out.println("5. Akhiri Sewa & Pembayaran");
            System.out.println("6. Ajukan Refund Pembayaran Terakhir");
            System.out.println("7. Lihat Notifikasi (" + pelanggan.getDaftarNotifikasi().size() + ")");
            System.out.println("8. Lacak GPS E-Bike");
            System.out.println("0. Logout");
            System.out.print("Pilih fitur [0-8]: ");

            int pilih = bacaInt();
            switch (pilih) {
                case 1:
                    System.out.println("\n--- PROFIL PELANGGAN ---");
                    System.out.println("ID Pelanggan : " + pelanggan.getIdPengguna());
                    System.out.println("Nama         : " + pelanggan.getNama());
                    System.out.println("Email        : " + pelanggan.getEmail());
                    System.out.println("Status Akun  : " + pelanggan.getStatusAkun());
                    System.out.println("Saldo        : Rp" + pelanggan.getSaldo());
                    System.out.println("Total Riwayat Sewa: " + pelanggan.getDaftarSewa().size());
                    break;

                case 2:
                    System.out.print("Masukkan nominal Top Up Saldo (Rp): ");
                    double nominal = bacaDouble();
                    pelanggan.tambahSaldo(nominal);
                    buatNotifikasi("Top up saldo sebesar Rp" + nominal + " berhasil. Saldo kini: Rp" + pelanggan.getSaldo());
                    break;

                case 3:
                    tampilkanDaftarEBike();
                    break;

                case 4:
                    fiturMulaiSewa();
                    break;

                case 5:
                    fiturAkhiriSewaDanBayar();
                    break;

                case 6:
                    fiturRefund();
                    break;

                case 7:
                    fiturLihatNotifikasi();
                    break;

                case 8:
                    fiturLacakGPS();
                    break;

                case 0:
                    pelanggan.logout();
                    diMenuPelanggan = false;
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private static void fiturMulaiSewa() {
        // Cek apakah ada sewa yang masih berlangsung
        for (Sewa s : pelanggan.getDaftarSewa()) {
            if ("Berlangsung".equalsIgnoreCase(s.getStatus())) {
                System.out.println("Anda masih memiliki sewa aktif (Sewa #" + s.getIdSewa()
                        + " - E-Bike ID " + s.getEBike().getIdEBike() + "). Akhiri sewa tersebut terlebih dahulu!");
                return;
            }
        }

        tampilkanDaftarEBike();
        System.out.print("Masukkan ID E-Bike yang ingin disewa: ");
        int idEBike = bacaInt();

        EBike eBike = stasiun.cariEBike(idEBike);
        if (eBike == null) {
            System.out.println("E-Bike dengan ID " + idEBike + " tidak ditemukan di stasiun.");
            return;
        }

        if (!eBike.cekKetersediaan()) {
            System.out.println("E-Bike ID " + idEBike + " sedang tidak tersedia (Status: "
                    + eBike.getStatus() + ", Baterai: " + eBike.getBaterai() + "%).");
            return;
        }

        Sewa sewaBaru = pelanggan.mulaiSewa(idEBike);
        sewaBaru.setEBike(eBike);
        sewaBaru.setTarif(tarif);
        eBike.ubahStatus("Disewa");
        eBike.getDaftarSewa().add(sewaBaru);

        buatNotifikasi("Penyewaan #" + sewaBaru.getIdSewa() + " untuk E-Bike " + eBike.getMerk() + " (ID " + idEBike + ") telah dimulai.");
    }

    private static void fiturAkhiriSewaDanBayar() {
        Sewa sewaAktif = null;
        for (Sewa s : pelanggan.getDaftarSewa()) {
            if ("Berlangsung".equalsIgnoreCase(s.getStatus())) {
                sewaAktif = s;
                break;
            }
        }

        if (sewaAktif == null) {
            System.out.println("Tidak ada penyewaan yang sedang berlangsung saat ini.");
            return;
        }

        System.out.println("\n--- AKHIRI SEWA #" + sewaAktif.getIdSewa() + " (" + sewaAktif.getEBike().getMerk() + ") ---");
        System.out.print("Masukkan durasi sewa (dalam menit, misal 30): ");
        int durasiInput = bacaInt();
        if (durasiInput <= 0) {
            durasiInput = 1;
        }

        // Set waktuMulai & waktuSelesai sesuai durasi agar hitungDurasi() menghitung akurat
        sewaAktif.setWaktuMulai(LocalDateTime.now().minusMinutes(durasiInput));
        sewaAktif.setWaktuSelesai(LocalDateTime.now());

        // Tanya kode promo
        if (!daftarPromo.isEmpty()) {
            System.out.println("\nDaftar Kode Promo Tersedia:");
            for (Promo p : daftarPromo) {
                System.out.println("- " + p.getKodePromo() + " (Diskon: "
                        + (p.getDiskon() <= 1.0 ? (int) (p.getDiskon() * 100) + "%" : "Rp" + p.getDiskon())
                        + ", Berlaku s/d: " + p.getTanggalKedaluwarsa() + ")");
            }
            System.out.print("Masukkan Kode Promo (atau tekan Enter untuk lewati): ");
            String kode = scanner.nextLine().trim();
            if (!kode.isEmpty()) {
                Promo promoDipilih = cariPromo(kode);
                if (promoDipilih != null && promoDipilih.validasiPromo()) {
                    sewaAktif.setPromo(promoDipilih);
                    System.out.println("Kode promo '" + promoDipilih.getKodePromo() + "' berhasil diterapkan!");
                } else {
                    System.out.println("Kode promo tidak ditemukan atau sudah kedaluwarsa.");
                }
            }
        }

        // Hitung durasi & biaya
        sewaAktif.hitungDurasi();
        double totalTagihan = sewaAktif.hitungBiaya();
        System.out.println("\nRingkasan Tagihan:");
        System.out.println("- Durasi Sewa : " + sewaAktif.getDurasiMenit() + " menit");
        System.out.println("- Biaya Awal  : Rp" + tarif.getBiayaAwal());
        System.out.println("- Tarif/Menit : Rp" + tarif.getTarifPerMenit());
        System.out.println("- Total Bayar : Rp" + totalTagihan);

        // Pilih Metode Pembayaran
        System.out.println("\nPilih Metode Pembayaran:");
        System.out.println("1. Pembayaran Saldo (Saldo Anda: Rp" + pelanggan.getSaldo() + ")");
        System.out.println("2. Pembayaran QRIS");
        System.out.println("3. Pembayaran Kartu");
        System.out.print("Pilihan [1-3]: ");
        int pilihBayar = bacaInt();

        counterPembayaran++;
        MetodePembayaran metode = null;

        switch (pilihBayar) {
            case 1:
                metode = new PembayaranSaldo(counterPembayaran, totalTagihan, "Pending", pelanggan);
                break;
            case 2:
                System.out.print("Masukkan / Scan Kode QR (contoh: QRIS-UNDIP-01): ");
                String kodeQR = scanner.nextLine().trim();
                if (kodeQR.isEmpty()) {
                    kodeQR = "QRIS-" + counterPembayaran;
                }
                metode = new PembayaranQRIS(counterPembayaran, totalTagihan, "Pending", kodeQR);
                break;
            case 3:
                System.out.print("Masukkan Nomor Kartu (contoh: 4111-2222-3333-4444): ");
                String noKartu = scanner.nextLine().trim();
                if (noKartu.isEmpty()) {
                    noKartu = "4111-XXXX-XXXX-1234";
                }
                metode = new PembayaranKartu(counterPembayaran, totalTagihan, "Pending", noKartu);
                break;
            default:
                System.out.println("Pilihan tidak valid, menggunakan Pembayaran QRIS default.");
                metode = new PembayaranQRIS(counterPembayaran, totalTagihan, "Pending", "QRIS-" + counterPembayaran);
                break;
        }

        if (metode.prosesPembayaran()) {
            sewaAktif.setMetodePembayaran(metode);
            sewaAktif.akhiri();
            // Kurangi baterai sedikit setelah disewa & kembalikan ke stasiun
            EBike bike = sewaAktif.getEBike();
            bike.setBaterai(Math.max(10, bike.getBaterai() - Math.min(30, durasiInput / 2)));
            stasiun.kembalikanEBike(bike);

            buatNotifikasi("Sewa #" + sewaAktif.getIdSewa() + " selesai. Pembayaran Rp" + totalTagihan
                    + " (" + metode.getStatus() + ") berhasil.");
        } else {
            System.out.println("Pembayaran gagal! Silakan top up saldo atau gunakan metode pembayaran lain.");
        }
    }

    private static void fiturRefund() {
        List<Sewa> riwayat = pelanggan.getDaftarSewa();
        if (riwayat.isEmpty()) {
            System.out.println("Belum ada riwayat transaksi sewa.");
            return;
        }

        Sewa sewaTerakhir = riwayat.get(riwayat.size() - 1);
        MetodePembayaran mp = sewaTerakhir.getMetodePembayaran();
        if (mp == null) {
            System.out.println("Sewa terakhir belum memiliki pembayaran yang selesai.");
            return;
        }
        if ("Refunded".equalsIgnoreCase(mp.getStatus())) {
            System.out.println("Pembayaran untuk Sewa #" + sewaTerakhir.getIdSewa() + " sudah pernah di-refund.");
            return;
        }

        if (mp.refund()) {
            buatNotifikasi("Refund untuk Sewa #" + sewaTerakhir.getIdSewa() + " sebesar Rp" + mp.getNominal() + " berhasil diproses.");
        }
    }

    private static void fiturLihatNotifikasi() {
        List<Notifikasi> list = pelanggan.getDaftarNotifikasi();
        if (list.isEmpty()) {
            System.out.println("Belum ada notifikasi.");
            return;
        }
        System.out.println("\n--- DAFTAR NOTIFIKASI ---");
        for (Notifikasi n : list) {
            String statusBaca = n.isSudahDibaca() ? "[Sudah Dibaca]" : "[BARU]";
            System.out.print(statusBaca + " ");
            n.kirim();
        }
        System.out.print("\nTandai semua notifikasi sebagai sudah dibaca? (y/n): ");
        String jawab = scanner.nextLine().trim();
        if (jawab.equalsIgnoreCase("y")) {
            for (Notifikasi n : list) {
                if (!n.isSudahDibaca()) {
                    n.tandaiDibaca();
                }
            }
        }
    }

    private static void fiturLacakGPS() {
        tampilkanDaftarEBike();
        System.out.print("Masukkan ID E-Bike yang ingin dilacak GPS-nya: ");
        int id = bacaInt();
        EBike bike = stasiun.cariEBike(id);
        if (bike != null) {
            System.out.println("Merk E-Bike : " + bike.getMerk());
            System.out.println("Koordinat   : " + bike.getGps().ambilLokasi());
            System.out.println("Status GPS  : " + bike.getGps().lacakEBike());
        } else {
            System.out.println("E-Bike dengan ID " + id + " tidak ditemukan.");
        }
    }

    // =========================================================
    // MENU ADMIN
    // =========================================================
    private static void menuAdmin() {
        boolean diMenuAdmin = true;
        while (diMenuAdmin) {
            System.out.println("\n---------------------------------------------");
            System.out.println(" MENU ADMIN - " + admin.getNama() + " (ID Admin: " + admin.getIdAdmin() + ")");
            System.out.println("---------------------------------------------");
            System.out.println("1. Lihat Semua E-Bike & Slot Stasiun");
            System.out.println("2. Tambah E-Bike Baru ke Stasiun");
            System.out.println("3. Hapus E-Bike dari Stasiun");
            System.out.println("4. Ubah Tarif Penyewaan");
            System.out.println("5. Catat Kerusakan / Pemeliharaan E-Bike");
            System.out.println("6. Selesaikan Perbaikan E-Bike");
            System.out.println("7. Lihat Riwayat Pemeliharaan");
            System.out.println("8. Tambah Kode Promo Baru");
            System.out.println("0. Logout");
            System.out.print("Pilih fitur [0-8]: ");

            int pilih = bacaInt();
            switch (pilih) {
                case 1:
                    tampilkanDaftarEBike();
                    break;

                case 2:
                    if (stasiun.cekSlotTersedia() <= 0) {
                        System.out.println("Stasiun penuh! Tidak dapat menambah E-Bike baru.");
                        break;
                    }
                    System.out.print("Masukkan ID E-Bike baru: ");
                    int idBaru = bacaInt();
                    if (stasiun.cariEBike(idBaru) != null) {
                        System.out.println("E-Bike dengan ID " + idBaru + " sudah terdaftar!");
                        break;
                    }
                    System.out.print("Masukkan Merk E-Bike: ");
                    String merk = scanner.nextLine().trim();
                    System.out.print("Masukkan Kapasitas Baterai (1-100): ");
                    int baterai = bacaInt();
                    System.out.print("Masukkan Latitude GPS (contoh -7.0505): ");
                    double lat = bacaDouble();
                    System.out.print("Masukkan Longitude GPS (contoh 110.4381): ");
                    double lon = bacaDouble();

                    admin.tambahEBike();
                    EBike bikeBaru = new EBike(idBaru, merk, "Tersedia", baterai, lat, lon);
                    stasiun.tambahEBike(bikeBaru);
                    break;

                case 3:
                    tampilkanDaftarEBike();
                    System.out.print("Masukkan ID E-Bike yang akan dihapus: ");
                    int idHapus = bacaInt();
                    if (stasiun.hapusEBikeDariStasiun(idHapus)) {
                        admin.hapusEBike(idHapus);
                    } else {
                        System.out.println("E-Bike dengan ID " + idHapus + " tidak ditemukan.");
                    }
                    break;

                case 4:
                    System.out.println("Tarif Saat Ini -> Biaya Awal: Rp" + tarif.getBiayaAwal()
                            + " | Tarif/Menit: Rp" + tarif.getTarifPerMenit());
                    System.out.print("Masukkan Tarif Per Menit Baru (Rp): ");
                    double tarifBaru = bacaDouble();
                    System.out.print("Masukkan Biaya Awal Baru (Rp): ");
                    double biayaAwalBaru = bacaDouble();
                    admin.ubahTarif();
                    tarif.setTarif(tarifBaru);
                    tarif.setBiayaAwal(biayaAwalBaru);
                    break;

                case 5:
                    tampilkanDaftarEBike();
                    System.out.print("Masukkan ID E-Bike yang mengalami kerusakan: ");
                    int idRusak = bacaInt();
                    EBike bikeRusak = stasiun.cariEBike(idRusak);
                    if (bikeRusak == null) {
                        System.out.println("E-Bike tidak ditemukan.");
                        break;
                    }
                    System.out.print("Masukkan deskripsi kerusakan: ");
                    String deskripsi = scanner.nextLine().trim();
                    counterPemeliharaan++;
                    Pemeliharaan p = new Pemeliharaan(counterPemeliharaan, LocalDate.now(), deskripsi, "Terjadwal", admin, bikeRusak);
                    p.catatKerusakan();
                    admin.tambahPemeliharaan(p);
                    bikeRusak.getDaftarPemeliharaan().add(p);
                    break;

                case 6:
                    List<Pemeliharaan> daftarP = admin.getDaftarPemeliharaan();
                    boolean adaYangDiperbaiki = false;
                    for (Pemeliharaan item : daftarP) {
                        if ("Dalam Perbaikan".equalsIgnoreCase(item.getStatus())) {
                            System.out.println("- ID Pemeliharaan #" + item.getIdPemeliharaan()
                                    + " | E-Bike ID " + item.getEBike().getIdEBike()
                                    + " (" + item.getEBike().getMerk() + ") | " + item.getDeskripsi());
                            adaYangDiperbaiki = true;
                        }
                    }
                    if (!adaYangDiperbaiki) {
                        System.out.println("Tidak ada E-Bike yang sedang dalam status perbaikan.");
                        break;
                    }
                    System.out.print("Masukkan ID Pemeliharaan yang telah selesai diperbaiki: ");
                    int idSelesai = bacaInt();
                    Pemeliharaan target = null;
                    for (Pemeliharaan item : daftarP) {
                        if (item.getIdPemeliharaan() == idSelesai) {
                            target = item;
                            break;
                        }
                    }
                    if (target != null) {
                        target.selesaikanPerbaikan();
                        target.getEBike().setBaterai(100); // Isi penuh baterai seusai servis
                    } else {
                        System.out.println("ID Pemeliharaan tidak ditemukan.");
                    }
                    break;

                case 7:
                    if (admin.getDaftarPemeliharaan().isEmpty()) {
                        System.out.println("Belum ada riwayat pemeliharaan.");
                    } else {
                        System.out.println("\n--- RIWAYAT PEMELIHARAAN ---");
                        for (Pemeliharaan item : admin.getDaftarPemeliharaan()) {
                            System.out.println("#" + item.getIdPemeliharaan() + " | Tanggal: " + item.getTanggal()
                                    + " | E-Bike ID: " + item.getEBike().getIdEBike()
                                    + " | Status: " + item.getStatus()
                                    + " | Ket: " + item.getDeskripsi());
                        }
                    }
                    break;

                case 8:
                    System.out.print("Masukkan Kode Promo Baru (contoh: DISKON50): ");
                    String kodePromo = scanner.nextLine().trim().toUpperCase();
                    System.out.print("Masukkan Nilai Diskon (<= 1.0 untuk persentase misal 0.25, atau > 1 untuk nominal Rp): ");
                    double diskon = bacaDouble();
                    System.out.print("Masa berlaku berapa hari ke depan?: ");
                    int hari = bacaInt();
                    Promo promoBaru = new Promo(daftarPromo.size() + 1, kodePromo, diskon, LocalDate.now().plusDays(hari));
                    daftarPromo.add(promoBaru);
                    System.out.println("Promo '" + kodePromo + "' berhasil ditambahkan!");
                    break;

                case 0:
                    admin.logout();
                    diMenuAdmin = false;
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    // =========================================================
    // HELPER METHODS
    // =========================================================
    private static void tampilkanDaftarEBike() {
        System.out.println("\n--- STASIUN: " + stasiun.getNama() + " (" + stasiun.getLokasi() + ") ---");
        System.out.println("Kapasitas Total: " + stasiun.getKapasitas()
                + " | Terisi: " + stasiun.getDaftarEBike().size()
                + " | Slot Kosong Tersedia: " + stasiun.cekSlotTersedia());
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("%-6s | %-18s | %-12s | %-8s | %-10s | %-20s%n",
                "ID", "Merk", "Status", "Baterai", "Siap Sewa?", "Koordinat GPS");
        System.out.println("-------------------------------------------------------------------------");
        for (EBike b : stasiun.getDaftarEBike()) {
            System.out.printf("%-6d | %-18s | %-12s | %-7d%% | %-10s | %-20s%n",
                    b.getIdEBike(),
                    b.getMerk(),
                    b.getStatus(),
                    b.getBaterai(),
                    b.cekKetersediaan() ? "Ya" : "Tidak",
                    b.getGps().ambilLokasi());
        }
        System.out.println("-------------------------------------------------------------------------");
    }

    private static void tampilkanInfoSistem() {
        tampilkanDaftarEBike();
        System.out.println("\n--- INFO TARIF ---");
        System.out.println("Biaya Awal      : Rp" + tarif.getBiayaAwal());
        System.out.println("Tarif Per Menit : Rp" + tarif.getTarifPerMenit());

        System.out.println("\n--- DAFTAR PROMO ---");
        for (Promo p : daftarPromo) {
            System.out.println("- Kode: " + p.getKodePromo()
                    + " | Potongan: " + (p.getDiskon() <= 1.0 ? (int) (p.getDiskon() * 100) + "%" : "Rp" + p.getDiskon())
                    + " | Kedaluwarsa: " + p.getTanggalKedaluwarsa()
                    + " | Valid: " + (p.validasiPromo() ? "Aktif" : "Expired"));
        }
    }

    private static Promo cariPromo(String kode) {
        for (Promo p : daftarPromo) {
            if (p.getKodePromo().equalsIgnoreCase(kode)) {
                return p;
            }
        }
        return null;
    }

    private static void buatNotifikasi(String pesan) {
        Notifikasi n = new Notifikasi(counterNotifikasi++, pesan, LocalDateTime.now());
        pelanggan.tambahNotifikasi(n);
        n.kirim();
    }

    private static int bacaInt() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Input harus berupa angka bulat! Masukkan lagi: ");
            }
        }
    }

    private static double bacaDouble() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.print("Input harus berupa angka! Masukkan lagi: ");
            }
        }
    }
}
