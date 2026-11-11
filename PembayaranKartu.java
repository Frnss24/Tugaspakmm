public class PembayaranKartu extends MetodePembayaran {
    private String nomorKartu;

    public PembayaranKartu() {
        super();
    }

    public PembayaranKartu(int idPembayaran, double nominal, String status, String nomorKartu) {
        super(idPembayaran, nominal, status);
        this.nomorKartu = nomorKartu;
    }

    @Override
    public boolean prosesPembayaran() {
        setStatus("Lunas (Kartu)");
        System.out.println("Pembayaran Kartu #" + getIdPembayaran() + " (No Kartu: " + nomorKartu + ") sebesar Rp" + getNominal() + " berhasil diproses.");
        return true;
    }

    @Override
    public boolean refund() {
        setStatus("Refunded");
        System.out.println("Refund Pembayaran Kartu #" + getIdPembayaran() + " ke kartu " + nomorKartu + " berhasil.");
        return true;
    }

    public String getNomorKartu() {
        return nomorKartu;
    }

    public void setNomorKartu(String nomorKartu) {
        this.nomorKartu = nomorKartu;
    }
}

