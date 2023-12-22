package com.cb.kanbanbackend.repo;

import com.cb.kanbanbackend.entity.ModulesUsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleUsersRepo extends JpaRepository<ModulesUsersEntity, Integer> {
}
