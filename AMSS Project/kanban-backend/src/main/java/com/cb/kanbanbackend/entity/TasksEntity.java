package com.cb.kanbanbackend.entity;

import com.cb.kanbanbackend.dto.req.TaskReqDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

//Entity für Tasks

//Repräsentiert die Entity-Klasse im modules Tabelle in der Datenbank.
//Felder entsprechen die Spalten der Tabelle
//Getter und Setter Methoden
//Konstruktor
@Entity
@Table(name = "tasks", schema = "kanban_dashboard", catalog = "")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TasksEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "title", nullable = false, length = 255)
    private String title;
    @Basic
    @Column(name = "description", nullable = true, length = -1)
    private String description;
    @Basic
    @Column(name = "preset_effort", nullable = true)
    private Integer presetEffort;
    @Basic
    @Column(name = "actual_effort", nullable = true)
    private Integer actualEffort;
    @Basic
    @Column(name = "status_id", nullable = true)
    private Integer statusId;
    @Basic
    @Column(name = "priority_id", nullable = true)
    private Integer priorityId;
    @Basic
    @Column(name = "end_date", nullable = true)
    private String endDate;
    @Basic
    @Column(name = "module_id", nullable = true)
    private Integer moduleId;
    @Basic
    @Column(name = "created_date", nullable = true)
    private LocalDate createdDate;

    public TasksEntity(TaskReqDto entity) {
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.presetEffort = entity.getPresetEffort();
        this.actualEffort = entity.getActualEffort();
        this.statusId = entity.getStatusId();
        this.priorityId = entity.getPriorityId();
        this.endDate = entity.getEndDate();
        this.moduleId = entity.getModuleId();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPresetEffort() {
        return presetEffort;
    }

    public void setPresetEffort(Integer presetEffort) {
        this.presetEffort = presetEffort;
    }

    public Integer getActualEffort() {
        return actualEffort;
    }

    public void setActualEffort(Integer actualEffort) {
        this.actualEffort = actualEffort;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Integer priorityId) {
        this.priorityId = priorityId;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TasksEntity that = (TasksEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(presetEffort, that.presetEffort) && Objects.equals(actualEffort, that.actualEffort) && Objects.equals(statusId, that.statusId) && Objects.equals(priorityId, that.priorityId) && Objects.equals(endDate, that.endDate) && Objects.equals(moduleId, that.moduleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, presetEffort, actualEffort, statusId, priorityId, endDate, moduleId);
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
