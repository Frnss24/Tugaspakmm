import java.util.ArrayList;
import java.util.List;

public class Admin extends Pengguna {
    private int idAdmin;
    private List<Pemeliharaan> daftarPemeliharaan;

    public Admin() {
        super();
        this.daftarPemeliharaan = new ArrayList<>();
    }

    public Admin(int idPengguna, String nama, String email, String password, int idAdmin) {
        super(idPengguna, nama, email, password);
        this.idAdmin = idAdmin;
        this.daftarPemeliharaan = new ArrayList<>();
    }

    public void tambahEBike() {
        System.out.println("Admin " + getNama() + " menambahkan unit E-Bike baru ke sistem.");
    }

    public void hapusEBike(int idEBike) {
        System.out.println("Admin " + getNama() + " menghapus E-Bike dengan ID: " + idEBike);
    }

    public void ubahTarif() {
        System.out.println("Admin " + getNama() + " memperbarui tarif penyewaan.");
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public void tambahPemeliharaan(Pemeliharaan pemeliharaan) {
        this.daftarPemeliharaan.add(pemeliharaan);
    }

    public List<Pemeliharaan> getDaftarPemeliharaan() {
        return daftarPemeliharaan;
    }
}

