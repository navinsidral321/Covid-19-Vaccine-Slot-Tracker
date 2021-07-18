package com.example.anew;

import java.util.ArrayList;

public class listformat {
    Integer center_id=0;
    Integer dose1=0;
    Integer dose2=0;
    String name;

    public void setCenter_id(Integer center_id) {
        this.center_id = center_id;
    }

    public void setDose1(Integer dose1) {
        this.dose1 = dose1;
    }

    public void setDose2(Integer dose2) {
        this.dose2 = dose2;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBlock_name(String block_name) {
        this.block_name = block_name;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public void setAge_limit(Integer age_limit) {
        this.age_limit = age_limit;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    public void setSlots(ArrayList<Integer> slots) {
        this.slots = slots;
    }

    String address;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getBlock_name() {
        return block_name;
    }

    public Integer getPincode() {
        return pincode;
    }

    public String getFee_type() {
        return fee_type;
    }

    public String getSession_id() {
        return session_id;
    }

    public Integer getAge_limit() {
        return age_limit;
    }

    public String getVaccine() {
        return vaccine;
    }

    public ArrayList<Integer> getSlots() {
        return slots;
    }

    String block_name;
    Integer pincode;
    String fee_type;

    public Integer getCenter_id() {
        return center_id;
    }

    public Integer getDose1() {
        return dose1;
    }

    public Integer getDose2() {
        return dose2;
    }

    String session_id;
    Integer age_limit;
    String vaccine;
    ArrayList<Integer> slots;

    String from;
    String to;


    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
