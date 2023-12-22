package com.cb.kanbanbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Objects;

//Entity für Users

//Repräsentiert die Entity-Klasse im modules Tabelle in der Datenbank.
//Felder entsprechen die Spalten der Tabelle
//Getter und Setter Methoden
//Konstruktor
@Entity
@Table(name = "users", schema = "kanban_dashboard", catalog = "")
@ToString
public class UsersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "userID", nullable = false)
    private Integer userId;
    @Basic
    @Column(name = "username", nullable = false, length = 50)
    private String username;
    @Basic
    @Column(name = "password", nullable = false, length = 255)
    private String password;
    @Basic
    @Column(name = "email", nullable = true, length = 200)
    private String email;
    @Basic
    @Column(name = "phone", nullable = true, length = 20)
    private String phone;
    @Basic
    @Column(name = "calander_url", nullable = true, length = -1)
    private String calenderUrl;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCalenderUrl() {
        return calenderUrl;
    }

    public void setCalenderUrl(String calenderUrl) {
        this.calenderUrl = calenderUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return Objects.equals(userId, that.userId) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(email, that.email) && Objects.equals(phone, that.phone) && Objects.equals(calenderUrl, that.calenderUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, password, email, phone, calenderUrl);
    }
}
