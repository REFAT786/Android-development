package com.ibss.contactapp.domain;

public class User {
    String userId, name, email, password,  blood_group, p_email, o_email, phn1, phn2, whatsaap, skype, address, nationality,emp_id, department, designation;

    public User( String name, String blood_group, String p_email, String o_email, String phn1, String phn2, String whatsaap, String skype, String address, String nationality,String emp_id, String department, String designation) {

        this.name = name;
        this.blood_group = blood_group;
        this.p_email = p_email;
        this.o_email = o_email;
        this.phn1 = phn1;
        this.phn2 = phn2;
        this.whatsaap = whatsaap;
        this.skype = skype;
        this.address = address;
        this.nationality = nationality;
        this.emp_id = emp_id;
        this.department = department;
        this.designation = designation;
    }
    public User(String userId,String name, String blood_group, String p_email, String o_email, String phn1, String phn2, String whatsaap, String skype, String address, String nationality,String emp_id, String department, String designation){
        this.userId = userId;
        this.name = name;
        this.blood_group = blood_group;
        this.p_email = p_email;
        this.o_email = o_email;
        this.phn1 = phn1;
        this.phn2 = phn2;
        this.whatsaap = whatsaap;
        this.skype = skype;
        this.address = address;
        this.nationality = nationality;
        this.emp_id = emp_id;
        this.department = department;
        this.designation = designation;

    }
    public User(){}


    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public String getP_email() {
        return p_email;
    }

    public void setP_email(String p_email) {
        this.p_email = p_email;
    }

    public String getO_email() {
        return o_email;
    }

    public void setO_email(String o_email) {
        this.o_email = o_email;
    }

    public String getPhn1() {
        return phn1;
    }

    public void setPhn1(String phn1) {
        this.phn1 = phn1;
    }

    public String getPhn2() {
        return phn2;
    }

    public void setPhn2(String phn2) {
        this.phn2 = phn2;
    }

    public String getWhatsaap() {
        return whatsaap;
    }

    public void setWhatsaap(String whatsaap) {
        this.whatsaap = whatsaap;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
