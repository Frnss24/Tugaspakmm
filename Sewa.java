import java.time.Duration;
import java.time.LocalDateTime;

public class Sewa {
    private int idSewa;
    private LocalDateTime waktuMulai;
    private LocalDateTime waktuSelesai;
    private int durasiMenit;
    private double totalBiaya;
    private String status;

    private Pelanggan pelanggan;
    private EBike eBike;
    private Tarif tarif;
    private MetodePembayaran metodePembayaran; // Composition (0..1)
    private Promo promo; // Association (0..1)

    public Sewa() {
    }

    public Sewa(int idSewa, Pelanggan pelanggan, EBike eBike, Tarif tarif) {
        this.idSewa = idSewa;
        this.pelanggan = pelanggan;
        this.eBike = eBike;
        this.tarif = tarif;
        this.status = "Menunggu";
    }

    public void mulai() {
        this.waktuMulai = LocalDateTime.now();
        this.status = "Berlangsung";
        if (eBike != null) {
            eBike.ubahStatus("Disewa");
        }
        System.out.println("Sewa #" + idSewa + " dimulai pada " + waktuMulai);
    }

    public void akhiri() {
        if (this.waktuSelesai == null) {
            this.waktuSelesai = LocalDateTime.now();
        }
        this.status = "Selesai";
        if (eBike != null) {
            eBike.ubahStatus("Tersedia");
        }
        hitungDurasi();
        hitungBiaya();
        System.out.println("Sewa #" + idSewa + " diakhiri pada " + waktuSelesai
                + " | Durasi: " + durasiMenit + " menit | Total Biaya: Rp" + totalBiaya);
    }

    public int hitungDurasi() {
        if (waktuMulai != null && waktuSelesai != null) {
            long menit = Duration.between(waktuMulai, waktuSelesai).toMinutes();
            if (menit > 0) {
                this.durasiMenit = (int) menit;
            }
        }
        return this.durasiMenit;
    }

    public double hitungBiaya() {
        double biaya = 0;
        if (tarif != null) {
            biaya = tarif.hitungBiaya(this.durasiMenit);
        }
        if (promo != null && promo.validasiPromo()) {
            double potongan = promo.hitungDiskon(biaya);
            biaya -= potongan;
        }
        this.totalBiaya = Math.max(0, biaya);
        return this.totalBiaya;
    }

    public int getIdSewa() {
        return idSewa;
    }

    public void setIdSewa(int idSewa) {
        this.idSewa = idSewa;
    }

    public LocalDateTime getWaktuMulai() {
        return waktuMulai;
    }

    public void setWaktuMulai(LocalDateTime waktuMulai) {
        this.waktuMulai = waktuMulai;
    }

    public LocalDateTime getWaktuSelesai() {
        return waktuSelesai;
    }

    public void setWaktuSelesai(LocalDateTime waktuSelesai) {
        this.waktuSelesai = waktuSelesai;
    }

    public int getDurasiMenit() {
        return durasiMenit;
    }

    public void setDurasiMenit(int durasiMenit) {
        this.durasiMenit = durasiMenit;
    }

    public double getTotalBiaya() {
        return totalBiaya;
    }

    public void setTotalBiaya(double totalBiaya) {
        this.totalBiaya = totalBiaya;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Pelanggan getPelanggan() {
        return pelanggan;
    }

    public void setPelanggan(Pelanggan pelanggan) {
        this.pelanggan = pelanggan;
    }

    public EBike getEBike() {
        return eBike;
    }

    public void setEBike(EBike eBike) {
        this.eBike = eBike;
    }

    public Tarif getTarif() {
        return tarif;
    }

    public void setTarif(Tarif tarif) {
        this.tarif = tarif;
    }

    public MetodePembayaran getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setMetodePembayaran(MetodePembayaran metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    public Promo getPromo() {
        return promo;
    }

    public void setPromo(Promo promo) {
        this.promo = promo;
    }
}

