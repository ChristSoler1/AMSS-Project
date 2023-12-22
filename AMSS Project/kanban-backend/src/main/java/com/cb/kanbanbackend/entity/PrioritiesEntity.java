package com.cb.kanbanbackend.entity;

import jakarta.persistence.*;
import lombok.ToString;

import java.util.Objects;

//Entity für Priorities

//Repräsentiert die Entity-Klasse im modules Tabelle in der Datenbank.
//Felder entsprechen die Spalten der Tabelle
//Getter und Setter Methoden
//Konstruktor
@Entity
@Table(name = "priorities", schema = "kanban_dashboard", catalog = "")
@ToString
public class PrioritiesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "level", nullable = false, length = 20)
    private String level;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrioritiesEntity that = (PrioritiesEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(level, that.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, level);
    }
}
