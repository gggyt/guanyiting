package com.example.acm.view;

/**
 * Created by ggg on 2019/4/9.
 */
public class ChartView {

    private String name;
    private int y;

    public ChartView(String name, int y) {
        this.name = name;
        this.y = y;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public int getY() {
        return y;
    }
}
