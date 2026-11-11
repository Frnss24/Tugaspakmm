import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Notifikasi {
    private int idNotifikasi;
    private String pesan;
    private LocalDateTime waktu;
    private boolean sudahDibaca;

    public Notifikasi() {
        this.waktu = LocalDateTime.now();
        this.sudahDibaca = false;
    }

    public Notifikasi(int idNotifikasi, String pesan, LocalDateTime waktu) {
        this.idNotifikasi = idNotifikasi;
        this.pesan = pesan;
        this.waktu = waktu;
        this.sudahDibaca = false;
    }

    public void kirim() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.println("[Notifikasi #" + idNotifikasi + " | " + waktu.format(fmt) + "] " + pesan);
    }

    public void tandaiDibaca() {
        this.sudahDibaca = true;
        System.out.println("[Notifikasi #" + idNotifikasi + "] Status diubah menjadi: Sudah Dibaca.");
    }

    public boolean isSudahDibaca() {
        return sudahDibaca;
    }

    public int getIdNotifikasi() {
        return idNotifikasi;
    }

    public void setIdNotifikasi(int idNotifikasi) {
        this.idNotifikasi = idNotifikasi;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public LocalDateTime getWaktu() {
        return waktu;
    }

    public void setWaktu(LocalDateTime waktu) {
        this.waktu = waktu;
    }
}
