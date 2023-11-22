package com.cb.kanbanbackend.dto.res;

import com.cb.kanbanbackend.entity.ModulesEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// Used for tansfering data between layers of application

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ModuleResDto {
    private ModulesEntity entity;
    private String performanceRecordsName;
}
