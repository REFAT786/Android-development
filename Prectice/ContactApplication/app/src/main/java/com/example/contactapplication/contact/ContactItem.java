package com.example.contactapplication.contact;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contacts")
public class ContactItem {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String email;
    private String phone_no;
    private String mobile_no;
    private String skype_id;

    public ContactItem(String name, String email, String phone_no, String mobile_no, String skype_id) {
        this.name = name;
        this.email = email;
        this.phone_no = phone_no;
        this.mobile_no = mobile_no;
        this.skype_id = skype_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getSkype_id() {
        return skype_id;
    }

    public void setSkype_id(String skype_id) {
        this.skype_id = skype_id;
    }
}
