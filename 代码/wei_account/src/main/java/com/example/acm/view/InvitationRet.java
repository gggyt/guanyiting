package com.example.acm.view;

import java.util.List;

/**
 * Created by ggg on 2019/5/17.
 */
public class InvitationRet {

    private List<String> data;
    private List<Integer> sum;

    public void setData(List<String> data) {
        this.data = data;
    }

    public void setSum(List<Integer> sum) {
        this.sum = sum;
    }

    public List<String> getData() {
        return data;
    }

    public List<Integer> getSum() {
        return sum;
    }
}
