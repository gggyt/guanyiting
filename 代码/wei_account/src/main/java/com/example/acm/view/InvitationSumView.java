package com.example.acm.view;

/**
 * Created by ggg on 2019/5/17.
 */
public class InvitationSumView {

    private String data;
    private int sum;

    public InvitationSumView(String data, int sum) {
        this.data = data;
        this.sum = sum;
    }

    public int getSum() {
        return sum;
    }

    public String getData() {
        return data;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public void setData(String data) {
        this.data = data;
    }
}
