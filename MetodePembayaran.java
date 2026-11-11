public abstract class MetodePembayaran {
    private int idPembayaran;
    private double nominal;
    private String status;

    public MetodePembayaran() {
    }

    public MetodePembayaran(int idPembayaran, double nominal, String status) {
        this.idPembayaran = idPembayaran;
        this.nominal = nominal;
        this.status = status;
    }

    public abstract boolean prosesPembayaran();

    public abstract boolean refund();

    public int getIdPembayaran() {
        return idPembayaran;
    }

    public void setIdPembayaran(int idPembayaran) {
        this.idPembayaran = idPembayaran;
    }

    public double getNominal() {
        return nominal;
    }

    public void setNominal(double nominal) {
        this.nominal = nominal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

