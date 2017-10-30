package pe.edu.upc.growent.models;


import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String id;
    private String name;
    private String email;
    private String password;
    private int income;

    public User() {
    }

    public User(String id, String name, String email, String password, int income) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.income = income;
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


    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public int getIncome() {
        return income;
    }

    public User setIncome(int income) {
        this.income = income;
        return this;
    }

    public Bundle toBundle(){
        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        bundle.putString("name",name);
        bundle.putString("email",email);
        bundle.putString("password",password);
        bundle.putInt("income",income);
        return bundle;
    }
    public static User fromBundle(Bundle bundle){
        User user = new User();
         user.setId(bundle.getString("id"))
                .setName(bundle.getString("name"))
                .setEmail(bundle.getString("email"))
                .setPassword(bundle.getString("password"))
                .setIncome(bundle.getInt("income"));
        return user;

    }

    public static User from(JSONObject jsonUser) {
        User user = new User();
        try {
            user.setId(jsonUser.getString("id"))
                    .setName(jsonUser.getString("name"))
                    .setEmail(jsonUser.getString("email"))
                    .setPassword(jsonUser.getString("password"))
                    .setIncome(jsonUser.getInt("income"));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }

    public static List<User> from(JSONArray jsonUsers) {
        List<User> users = new ArrayList<>();
        for(int i = 0; i < jsonUsers.length(); i++)
            try {
                users.add(User.from(jsonUsers.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        return users;
    }

}
