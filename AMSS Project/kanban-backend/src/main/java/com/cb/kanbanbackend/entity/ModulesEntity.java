package com.cb.kanbanbackend.entity;

import com.cb.kanbanbackend.dto.req.ModuleReqDto;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Objects;

//Entity für Modules

//Repräsentiert die Entity-Klasse im modules Tabelle in der Datenbank.
//Felder entsprechen die Spalten der Tabelle
//Getter und Setter Methoden
//Konstruktor


@Entity

//Definiert Datenbank
@Table(name = "modules", schema = "kanban_dashboard", catalog = "")
//Lombok
@ToString
@NoArgsConstructor
public class ModulesEntity {

    //ID wird automatisch generiert
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Primärschlüssel der Tabelle
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "title", nullable = false, length = 255)
    private String title;
    @Basic
    @Column(name = "credits", nullable = true)
    private Integer credits;
    @Basic
    @Column(name = "preset_effort", nullable = true)
    private Integer presetEffort;
    @Basic
    @Column(name = "teaching_resources", nullable = true, length = -1)
    private String teachingResources;
    @Basic
    @Column(name = "learning_objectives", nullable = true, length = -1)
    private String learningObjectives;
    @Basic
    @Column(name = "performance_record_id", nullable = true)
    private Integer performanceRecordId;
    @Basic
    @Column(name = "created_date", nullable = true)
    private LocalDate startDate;
    @Basic
    @Column(name = "end_date", nullable = true)
    private LocalDate endDate;

    //Wandelt ModuleqDto zu ModulesEntity-Objekt um.
    public ModulesEntity(ModuleReqDto entity) {
        this.title = entity.getTitle();
        this.credits = entity.getCredits();
        this.presetEffort = entity.getPresetEffort();
        this.teachingResources = entity.getTeachingResources();
        this.learningObjectives = entity.getLearningObjectives();
        this.performanceRecordId = entity.getPerformanceRecordId();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Integer getPresetEffort() {
        return presetEffort;
    }

    public void setPresetEffort(Integer presetEffort) {
        this.presetEffort = presetEffort;
    }

    public String getTeachingResources() {
        return teachingResources;
    }

    public void setTeachingResources(String teachingResources) {
        this.teachingResources = teachingResources;
    }

    public String getLearningObjectives() {
        return learningObjectives;
    }

    public void setLearningObjectives(String learningObjectives) {
        this.learningObjectives = learningObjectives;
    }

    public Integer getPerformanceRecordId() {
        return performanceRecordId;
    }

    public void setPerformanceRecordId(Integer performanceRecordId) {
        this.performanceRecordId = performanceRecordId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModulesEntity that = (ModulesEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(credits, that.credits) && Objects.equals(presetEffort, that.presetEffort) && Objects.equals(teachingResources, that.teachingResources) && Objects.equals(learningObjectives, that.learningObjectives) && Objects.equals(performanceRecordId, that.performanceRecordId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, credits, presetEffort, teachingResources, learningObjectives, performanceRecordId);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
