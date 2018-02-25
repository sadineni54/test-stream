package com.test.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
@JsonDeserialize
@JsonSerialize
public class Value {
    private String val1;
    private String val2;
    public Value(){

    }
    public String getVal1() {
        return val1;
    }

    public void setVal1(String val1) {
        this.val1 = val1;
    }

    public String getVal2() {
        return val2;
    }

    public void setVal2(String val2) {
        this.val2 = val2;
    }

    public Value(String val1, String val2) {

        this.val1 = val1;
        this.val2 = val2;
    }
}
