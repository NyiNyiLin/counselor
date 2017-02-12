package com.nyi.annonymous.counselling.data.VOS;

/**
 * Created by IN-3442 on 10-Feb-17.
 */

public class MyCounsellor {
    private String name;
    private String position;
    private String descrip;

    public MyCounsellor(String name, String position, String descrip) {
        this.name = name;
        this.position = position;
        this.descrip = descrip;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }
}
