package com.collekarry.globlockhackthon.Model;

public class Order {

    private String type, name, timesatmp, email, org, status, address, contact, item;

    public Order() {

    }

    public Order(String type, String name, String timesatmp, String email, String org, String status, String address, String contact, String item) {
        this.type = type;
        this.name = name;
        this.timesatmp = timesatmp;
        this.email = email;
        this.org = org;
        this.status = status;
        this.address = address;
        this.contact = contact;
        this.item = item;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimesatmp() {
        return timesatmp;
    }

    public void setTimesatmp(String timesatmp) {
        this.timesatmp = timesatmp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
