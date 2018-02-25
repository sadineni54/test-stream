package com.test.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonDeserialize
@JsonSerialize
public class Key {
    private String key1;
    private String key2;
    public Key(){

    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
    return ((Key)obj).getKey1().equals(this.getKey1()) && ((Key)obj).getKey2().equals(this.getKey2() ) ;

    }

    public String getKey2() {
        return key2;
    }

    public void setKey2(String key2) {
        this.key2 = key2;
    }

    public Key(String key1, String key2) {
        this.key1 = key1;
        this.key2 = key2;
    }

    public String getKey1() {

        return key1;
    }

    public void setKey1(String key1) {
        this.key1 = key1;
    }
}
