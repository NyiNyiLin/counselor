package com.nyi.annonymous.counselling.data.VOS;

/**
 * Created by IN-3442 on 10-Feb-17.
 */

public class Counsellor {
    private String name;
    private String position;
    private int price;

    public Counsellor(String name, String position, int price) {
        this.name = name;
        this.position = position;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public int getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
