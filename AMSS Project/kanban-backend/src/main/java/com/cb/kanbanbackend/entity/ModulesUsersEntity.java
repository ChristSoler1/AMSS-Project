package com.cb.kanbanbackend.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.Objects;


//Entity für ModuleUsers

//Repräsentiert die Entity-Klasse im modules Tabelle in der Datenbank.
//Felder entsprechen die Spalten der Tabelle
//Getter und Setter Methoden
//Konstruktor
@Entity
@Table(name = "modules_users", schema = "kanban_dashboard", catalog = "")
@NoArgsConstructor
public class ModulesUsersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "module_id", nullable = true)
    private Integer moduleId;
    @Basic
    @Column(name = "user_id", nullable = true)
    private Integer userId;

    public ModulesUsersEntity(Integer moduleId, Integer userId) {
        this.moduleId = moduleId;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModulesUsersEntity that = (ModulesUsersEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(moduleId, that.moduleId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, moduleId, userId);
    }
}
