package com.ilich.model;

public class Admin {

    private int idAdmin;
    private String adminName;
    private String password;

    public Admin() {
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Admin admin = (Admin) object;

        if (idAdmin != admin.idAdmin) return false;
        if (!adminName.equals(admin.adminName)) return false;
        return password.equals(admin.password);
    }

    @Override
    public int hashCode() {
        int result = idAdmin;
        result = 31 * result + adminName.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "idAdmin=" + idAdmin +
                ", adminName='" + adminName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}