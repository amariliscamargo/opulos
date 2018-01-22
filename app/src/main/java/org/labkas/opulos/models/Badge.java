package org.labkas.opulos.models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashSet;
import java.util.Set;

@IgnoreExtraProperties
public class Badge {

    public String uid;
    public String name;
    public Set<String> requirements; // a set of uids to other badges

    public Badge() {
        // Default constructor required for calls to DataSnapshot.getValue(Comment.class)
    }

    public Badge(String uid, String name) {
        this.uid = uid;
        this.name = name;
        this.requirements = new HashSet<String>();
    }

}
