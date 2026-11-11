import java.util.ArrayList;
import java.util.List;

public class EBike {
    private int idEBike;
    private String merk;
    private String status;
    private int baterai;
    private GPS gps; // Composition (1)
    private List<Sewa> daftarSewa;
    private List<Pemeliharaan> daftarPemeliharaan;

    public EBike() {
        this.gps = new GPS(0.0, 0.0);
        this.daftarSewa = new ArrayList<>();
        this.daftarPemeliharaan = new ArrayList<>();
    }

    public EBike(int idEBike, String merk, String status, int baterai, double latitude, double longitude) {
        this.idEBike = idEBike;
        this.merk = merk;
        this.status = status;
        this.baterai = baterai;
        this.gps = new GPS(latitude, longitude); // Composition: dibuat bersama EBike
        this.daftarSewa = new ArrayList<>();
        this.daftarPemeliharaan = new ArrayList<>();
    }

    public boolean cekKetersediaan() {
        return "Tersedia".equalsIgnoreCase(status) && baterai > 15;
    }

    public void ubahStatus(String statusBaru) {
        this.status = statusBaru;
        System.out.println("Status E-Bike ID " + idEBike + " diubah menjadi: " + statusBaru);
    }

    public int getBaterai() {
        return baterai;
    }

    public void setBaterai(int baterai) {
        this.baterai = baterai;
    }

    public int getIdEBike() {
        return idEBike;
    }

    public void setIdEBike(int idEBike) {
        this.idEBike = idEBike;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getStatus() {
        return status;
    }

    public GPS getGps() {
        return gps;
    }

    public List<Sewa> getDaftarSewa() {
        return daftarSewa;
    }

    public List<Pemeliharaan> getDaftarPemeliharaan() {
        return daftarPemeliharaan;
    }
}

