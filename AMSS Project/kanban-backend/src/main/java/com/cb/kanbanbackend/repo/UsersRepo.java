package com.cb.kanbanbackend.repo;

import com.cb.kanbanbackend.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepo extends JpaRepository<UsersEntity,Integer> {
    int countByEmail(String email);
    Optional<UsersEntity> getUsersEntityByPasswordAndEmail(String pass, String email);
}
