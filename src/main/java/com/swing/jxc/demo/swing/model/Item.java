package com.swing.jxc.demo.swing.model;

public class Item {
    private Long key;

    private String value;

    public Item(Long key, String value){
        this.key = key;
        this.value = value;
    }


    public void setKey(Long key) {
        this.key = key;
    }


    public Long getKey() {
        return key;
    }


    public void setValue(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }

    public String toString(){
        return value;
    }

}
