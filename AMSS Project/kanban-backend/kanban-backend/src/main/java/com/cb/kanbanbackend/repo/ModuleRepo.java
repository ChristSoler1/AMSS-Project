package com.cb.kanbanbackend.repo;

import com.cb.kanbanbackend.dto.res.ModuleResDto;
import com.cb.kanbanbackend.entity.ModulesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepo extends JpaRepository<ModulesEntity, Integer> {

    @Query("select new com.cb.kanbanbackend.dto.res.ModuleResDto(m,p.name) from ModulesEntity m inner join PerformanceRecordsEntity p on m.performanceRecordId = p.id")
    List<ModuleResDto> getModules();

}
