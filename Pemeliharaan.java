import java.time.LocalDate;

public class Pemeliharaan {
    private int idPemeliharaan;
    private LocalDate tanggal;
    private String deskripsi;
    private String status;
    private Admin admin;
    private EBike eBike;

    public Pemeliharaan() {
        this.tanggal = LocalDate.now();
    }

    public Pemeliharaan(int idPemeliharaan, LocalDate tanggal, String deskripsi, String status, Admin admin, EBike eBike) {
        this.idPemeliharaan = idPemeliharaan;
        this.tanggal = tanggal;
        this.deskripsi = deskripsi;
        this.status = status;
        this.admin = admin;
        this.eBike = eBike;
    }

    public void catatKerusakan() {
        this.status = "Dalam Perbaikan";
        if (eBike != null) {
            eBike.ubahStatus("Perbaikan");
        }
        System.out.println("Pemeliharaan #" + idPemeliharaan + " dicatat pada " + tanggal + ": " + deskripsi);
    }

    public void selesaikanPerbaikan() {
        this.status = "Selesai";
        if (eBike != null) {
            eBike.ubahStatus("Tersedia");
        }
        System.out.println("Pemeliharaan #" + idPemeliharaan + " telah selesai diperbaiki.");
    }

    public int getIdPemeliharaan() {
        return idPemeliharaan;
    }

    public void setIdPemeliharaan(int idPemeliharaan) {
        this.idPemeliharaan = idPemeliharaan;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public EBike getEBike() {
        return eBike;
    }

    public void setEBike(EBike eBike) {
        this.eBike = eBike;
    }
}

