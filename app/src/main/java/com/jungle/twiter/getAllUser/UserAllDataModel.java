package com.jungle.twiter.getAllUser;

public class UserAllDataModel {
    String name;
    String role;
    String status;
    String credit;
    String id;
    String email;
    String exposer;
    String username;
    String balance;




    public UserAllDataModel(String id, String name, String role, String status, String credit, String email, String exposer, String username, String balance) {
        this.name = name;
        this.role = role;
        this.status = status;
        this.credit = credit;
        this.exposer=exposer;
        this.id= id;
        this.email=email;
        this.username=username;
        this.balance=balance;
    }
    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExposer() {
        return exposer;
    }

    public void setExposer(String exposer) {
        this.exposer = exposer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}


