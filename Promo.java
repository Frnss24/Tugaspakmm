import java.time.LocalDate;

public class Promo {
    private int idPromo;
    private String kodePromo;
    private double diskon;
    private LocalDate tanggalKedaluwarsa;

    public Promo() {
    }

    public Promo(int idPromo, String kodePromo, double diskon, LocalDate tanggalKedaluwarsa) {
        this.idPromo = idPromo;
        this.kodePromo = kodePromo;
        this.diskon = diskon;
        this.tanggalKedaluwarsa = tanggalKedaluwarsa;
    }

    public boolean validasiPromo() {
        if (tanggalKedaluwarsa == null) {
            return false;
        }
        return !LocalDate.now().isAfter(tanggalKedaluwarsa);
    }

    public double hitungDiskon(double total) {
        if (!validasiPromo()) {
            return 0.0;
        }
        // Jika nilai diskon <= 1.0 dianggap persentase (misal 0.2 = 20%), jika > 1 dianggap potongan nominal
        if (diskon <= 1.0) {
            return total * diskon;
        }
        return Math.min(total, diskon);
    }

    public int getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(int idPromo) {
        this.idPromo = idPromo;
    }

    public String getKodePromo() {
        return kodePromo;
    }

    public void setKodePromo(String kodePromo) {
        this.kodePromo = kodePromo;
    }

    public double getDiskon() {
        return diskon;
    }

    public void setDiskon(double diskon) {
        this.diskon = diskon;
    }

    public LocalDate getTanggalKedaluwarsa() {
        return tanggalKedaluwarsa;
    }

    public void setTanggalKedaluwarsa(LocalDate tanggalKedaluwarsa) {
        this.tanggalKedaluwarsa = tanggalKedaluwarsa;
    }
}

