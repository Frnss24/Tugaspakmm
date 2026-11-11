import java.util.ArrayList;
import java.util.List;

public class Pelanggan extends Pengguna {
    private double saldo;
    private String statusAkun;
    private List<Notifikasi> daftarNotifikasi;
    private List<Sewa> daftarSewa;

    public Pelanggan() {
        super();
        this.daftarNotifikasi = new ArrayList<>();
        this.daftarSewa = new ArrayList<>();
    }

    public Pelanggan(int idPengguna, String nama, String email, String password, double saldo, String statusAkun) {
        super(idPengguna, nama, email, password);
        this.saldo = saldo;
        this.statusAkun = statusAkun;
        this.daftarNotifikasi = new ArrayList<>();
        this.daftarSewa = new ArrayList<>();
    }

    public void tambahSaldo(double nominal) {
        if (nominal > 0) {
            this.saldo += nominal;
            System.out.println("Saldo berhasil ditambahkan sebesar Rp" + nominal + ". Total saldo: Rp" + this.saldo);
        }
    }

    public Sewa mulaiSewa(int idEBike) {
        Sewa sewaBaru = new Sewa();
        sewaBaru.setIdSewa(daftarSewa.size() + 1);
        sewaBaru.setPelanggan(this);
        sewaBaru.mulai();
        daftarSewa.add(sewaBaru);
        System.out.println(getNama() + " memulai penyewaan untuk EBike ID: " + idEBike);
        return sewaBaru;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getStatusAkun() {
        return statusAkun;
    }

    public void setStatusAkun(String statusAkun) {
        this.statusAkun = statusAkun;
    }

    public void tambahNotifikasi(Notifikasi notifikasi) {
        this.daftarNotifikasi.add(notifikasi);
    }

    public List<Notifikasi> getDaftarNotifikasi() {
        return daftarNotifikasi;
    }

    public List<Sewa> getDaftarSewa() {
        return daftarSewa;
    }
}

