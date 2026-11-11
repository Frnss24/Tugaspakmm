public abstract class Pengguna {
    private int idPengguna;
    private String nama;
    private String email;
    private String password;

    public Pengguna() {
    }

    public Pengguna(int idPengguna, String nama, String email, String password) {
        this.idPengguna = idPengguna;
        this.nama = nama;
        this.email = email;
        this.password = password;
    }

    public boolean login() {
        System.out.println(nama + " (" + email + ") berhasil login.");
        return true;
    }

    public void logout() {
        System.out.println(nama + " telah logout.");
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(int idPengguna) {
        this.idPengguna = idPengguna;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

