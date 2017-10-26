package pe.edu.upc.growent.models;


import android.os.Bundle;

public class User {
    private String name;
    private String email;
    private String password;
    private double saldo;

    public User() {
    }

    public User(String name, String email, String password, double saldo) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.saldo = saldo;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public double getSaldo() {
        return saldo;
    }

    public User setSaldo(double saldo) {
        this.saldo = saldo;
        return this;
    }
    public Bundle toBundle(){
        Bundle bundle = new Bundle();
        bundle.putString("name",name);
        bundle.putString("email",email);
        bundle.putString("password",password);
        bundle.putDouble("saldo",saldo);
        return bundle;
    }
    public static User fromBundle(Bundle bundle){
        User movement = new User();
        return movement.setName(bundle.getString("name"))
                .setEmail(bundle.getString("name"))
                .setPassword(bundle.getString("place"))
                .setSaldo(bundle.getDouble("saldo"));
    }

}
