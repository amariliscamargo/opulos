package org.labkas.opulos.models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashSet;
import java.util.Set;

@IgnoreExtraProperties
public class User {

    public String username;
    public String email;
    public String aboutMe;
    public String phone;
    public String city;
    public String state;
    public String country;
    public Set<String> badges;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
        this.badges = new HashSet<String>();
    }

    public void setExtraData(String aboutMe, String phone, String city, String state, String country) {
        this.aboutMe = aboutMe;
        this.phone = phone;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public void addBadge(String badge) {
        this.badges.add(badge);
    }

    public void delBadge(String badge) {
        this.badges.remove(badge);
    }

}