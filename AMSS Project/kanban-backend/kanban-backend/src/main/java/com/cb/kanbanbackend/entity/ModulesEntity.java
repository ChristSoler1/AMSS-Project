package com.cb.kanbanbackend.entity;

import com.cb.kanbanbackend.dto.req.ModuleReqDto;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

/*
This code defines a JPA entity named ModulesEntity representing a module in a Spring Boot
application. It is marked with annotations such as @Entity and @Table to map it to a database table.
The class includes attributes corresponding to module properties, and a constructor to initialize the
entity from a data transfer object (ModuleReqDto). Additionally, it implements equals and hashCode methods
for proper comparison and hashing. The code follows the JavaBean convention with getter and setter methods
for each attribute, promoting encapsulation and facilitating interaction with the entity's properties.
Overall, this class is crucial for modeling and persisting module data in the application's database.
 */

@Entity
@Table(name = "modules", schema = "kanban_dashboard", catalog = "")
@ToString
@NoArgsConstructor
public class ModulesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public ModulesEntity(ModuleReqDto entity) {
        this.title = entity.getTitle();
        this.credits = entity.getCredits();
        this.presetEffort = entity.getPresetEffort();
        this.teachingResources = entity.getTeachingResources();
        this.learningObjectives = entity.getLearningObjectives();
        this.performanceRecordId = entity.getPerformanceRecordId();
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
}
