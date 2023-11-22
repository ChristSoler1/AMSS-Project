package com.cb.kanbanbackend.dto.req;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class TaskReqDto {
    private String title;
    private String description;
    private Integer presetEffort;
    private Integer actualEffort;
    private Integer statusId;
    private Integer priorityId;
    private String endDate;
    private Integer moduleId;
}
