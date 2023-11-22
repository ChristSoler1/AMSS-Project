package com.cb.kanbanbackend.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ModuleReqDto {
    private String title;
    private Integer credits;
    private Integer presetEffort;
    private String teachingResources;
    private String learningObjectives;
    private Integer performanceRecordId;
}
