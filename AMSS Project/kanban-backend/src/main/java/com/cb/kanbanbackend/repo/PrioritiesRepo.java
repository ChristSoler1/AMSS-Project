package com.cb.kanbanbackend.repo;

import com.cb.kanbanbackend.entity.PrioritiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PrioritiesRepo extends JpaRepository<PrioritiesEntity, Integer> {
    @Query("select p.level from PrioritiesEntity p where p.id=?1")
    String getLevelById(Integer id);
}
