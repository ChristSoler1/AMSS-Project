package com.cb.kanbanbackend.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//TaskRequest Lombok
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class TaskReqDto {
    private Integer id;
    private String title;
    private String description;
    private Integer presetEffort;
    private Integer actualEffort;
    private Integer statusId;
    private Integer priorityId;
    private String endDate;
    private Integer moduleId;
}
