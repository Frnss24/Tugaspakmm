public class PembayaranSaldo extends MetodePembayaran {
    private Pelanggan pelanggan;

    public PembayaranSaldo() {
        super();
    }

    public PembayaranSaldo(int idPembayaran, double nominal, String status) {
        super(idPembayaran, nominal, status);
    }

    public PembayaranSaldo(int idPembayaran, double nominal, String status, Pelanggan pelanggan) {
        super(idPembayaran, nominal, status);
        this.pelanggan = pelanggan;
    }

    @Override
    public boolean prosesPembayaran() {
        if (pelanggan != null) {
            if (pelanggan.getSaldo() < getNominal()) {
                setStatus("Gagal (Saldo Tidak Cukup)");
                System.out.println("Pembayaran Saldo gagal! Saldo Anda (Rp" + pelanggan.getSaldo()
                        + ") kurang dari total tagihan (Rp" + getNominal() + ").");
                return false;
            }
            pelanggan.setSaldo(pelanggan.getSaldo() - getNominal());
            setStatus("Lunas (Saldo)");
            System.out.println("Pembayaran Saldo #" + getIdPembayaran() + " sebesar Rp" + getNominal()
                    + " berhasil! Sisa saldo Anda: Rp" + pelanggan.getSaldo());
            return true;
        }
        setStatus("Lunas (Saldo)");
        System.out.println("Pembayaran Saldo #" + getIdPembayaran() + " sebesar Rp" + getNominal() + " berhasil diproses.");
        return true;
    }

    @Override
    public boolean refund() {
        if (pelanggan != null) {
            pelanggan.tambahSaldo(getNominal());
        }
        setStatus("Refunded");
        System.out.println("Refund Pembayaran Saldo #" + getIdPembayaran() + " sebesar Rp" + getNominal() + " berhasil dikembalikan ke saldo.");
        return true;
    }
}
