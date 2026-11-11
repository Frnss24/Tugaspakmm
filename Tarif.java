public class Tarif {
    private double tarifPerMenit;
    private double biayaAwal;

    public Tarif() {
    }

    public Tarif(double tarifPerMenit, double biayaAwal) {
        this.tarifPerMenit = tarifPerMenit;
        this.biayaAwal = biayaAwal;
    }

    public double hitungBiaya(int durasi) {
        return biayaAwal + (tarifPerMenit * durasi);
    }

    public void setTarif(double tarifBaru) {
        this.tarifPerMenit = tarifBaru;
        System.out.println("Tarif per menit diubah menjadi: Rp" + tarifBaru);
    }

    public double getTarifPerMenit() {
        return tarifPerMenit;
    }

    public double getBiayaAwal() {
        return biayaAwal;
    }

    public void setBiayaAwal(double biayaAwal) {
        this.biayaAwal = biayaAwal;
    }
}

