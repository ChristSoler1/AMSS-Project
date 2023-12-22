package com.cb.kanbanbackend.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

//ModuleRequest Lombok

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ModuleReqDto {
    private Integer id;
    private String title;
    private Integer credits;
    private Integer presetEffort;
    private String teachingResources;
    private String learningObjectives;
    private Integer performanceRecordId;
    private Integer userId;
    private LocalDate startDate;
    private LocalDate endDate;
}
