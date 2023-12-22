package com.cb.kanbanbackend.repo;

import com.cb.kanbanbackend.dto.res.ModuleResDto;
import com.cb.kanbanbackend.entity.ModulesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// Die Annotation @Repository macht Spring darauf aufmerksam, dass es sich hierbei um ein Bean handelt,
// das CRUD-Operationen (Create, Read, Update, Delete) für `ModulesEntity`-Objekte durchführt.
// Es erweitert JpaRepository, was einige Methoden wie save(), findOne(), findAll(), count(), delete() bereitstellt.
@Repository
public interface ModuleRepo extends JpaRepository<ModulesEntity, Integer> {

    @Query("select new com.cb.kanbanbackend.dto.res.ModuleResDto(m,p.name) from ModulesEntity m " +
            "inner join PerformanceRecordsEntity p on m.performanceRecordId = p.id " +
            "inner join ModulesUsersEntity mu on mu.moduleId = m.id where mu.userId = ?1" )
    List<ModuleResDto> getModules(Integer id);

}
