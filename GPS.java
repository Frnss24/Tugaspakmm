public class GPS {
    private double latitude;
    private double longitude;

    public GPS() {
    }

    public GPS(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String ambilLokasi() {
        return latitude + ", " + longitude;
    }

    public String lacakEBike() {
        return "E-Bike terlacak pada koordinat: (" + ambilLokasi() + ")";
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}

