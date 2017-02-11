package com.nyi.annonymous.counselling.data.VOS;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IN-3442 on 10-Feb-17.
 */

public class User {
    private String name;
    private String password;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
