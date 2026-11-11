import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Stasiun {
    private int idStasiun;
    private String nama;
    private String lokasi;
    private int kapasitas;
    private List<EBike> daftarEBike; // Aggregation (0..*)

    public Stasiun() {
        this.daftarEBike = new ArrayList<>();
    }

    public Stasiun(int idStasiun, String nama, String lokasi, int kapasitas) {
        this.idStasiun = idStasiun;
        this.nama = nama;
        this.lokasi = lokasi;
        this.kapasitas = kapasitas;
        this.daftarEBike = new ArrayList<>();
    }

    public int cekSlotTersedia() {
        return kapasitas - daftarEBike.size();
    }

    public void tambahEBike() {
        System.out.println("Menambahkan E-Bike ke " + nama + "...");
    }

    public void tambahEBike(EBike eBike) {
        if (cekSlotTersedia() > 0) {
            daftarEBike.add(eBike);
            System.out.println("E-Bike ID " + eBike.getIdEBike() + " (" + eBike.getMerk() + ") ditambahkan ke " + nama);
        } else {
            System.out.println(nama + " sudah penuh! Kapasitas maksimal: " + kapasitas);
        }
    }

    public void kembalikanEBike() {
        System.out.println("Mengembalikan E-Bike ke " + nama + "...");
    }

    public void kembalikanEBike(EBike eBike) {
        if (!daftarEBike.contains(eBike)) {
            if (cekSlotTersedia() > 0) {
                daftarEBike.add(eBike);
            } else {
                System.out.println("Slot di " + nama + " penuh!");
                return;
            }
        }
        eBike.ubahStatus("Tersedia");
        System.out.println("E-Bike ID " + eBike.getIdEBike() + " telah dikembalikan ke " + nama);
    }

    public EBike cariEBike(int idEBike) {
        for (EBike b : daftarEBike) {
            if (b.getIdEBike() == idEBike) {
                return b;
            }
        }
        return null;
    }

    public boolean hapusEBikeDariStasiun(int idEBike) {
        Iterator<EBike> it = daftarEBike.iterator();
        while (it.hasNext()) {
            EBike b = it.next();
            if (b.getIdEBike() == idEBike) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public int getIdStasiun() {
        return idStasiun;
    }

    public void setIdStasiun(int idStasiun) {
        this.idStasiun = idStasiun;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }

    public List<EBike> getDaftarEBike() {
        return daftarEBike;
    }
}
