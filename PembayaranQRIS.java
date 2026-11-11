public class PembayaranQRIS extends MetodePembayaran {
    private String kodeQR;

    public PembayaranQRIS() {
        super();
    }

    public PembayaranQRIS(int idPembayaran, double nominal, String status, String kodeQR) {
        super(idPembayaran, nominal, status);
        this.kodeQR = kodeQR;
    }

    @Override
    public boolean prosesPembayaran() {
        setStatus("Lunas (QRIS)");
        System.out.println("Pembayaran QRIS #" + getIdPembayaran() + " (Kode QR: " + kodeQR + ") sebesar Rp" + getNominal() + " berhasil diproses.");
        return true;
    }

    @Override
    public boolean refund() {
        setStatus("Refunded");
        System.out.println("Refund Pembayaran QRIS #" + getIdPembayaran() + " sebesar Rp" + getNominal() + " berhasil.");
        return true;
    }

    public String getKodeQR() {
        return kodeQR;
    }

    public void setKodeQR(String kodeQR) {
        this.kodeQR = kodeQR;
    }
}

