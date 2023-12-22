package com.cb.kanbanbackend.service.impl;

import com.cb.kanbanbackend.entity.ModulesUsersEntity;
import com.cb.kanbanbackend.repo.ModuleUsersRepo;
import com.cb.kanbanbackend.service.ModuleUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//Implementiert "ModuleUserService"

@Service
public class ModuleUsersServiceImpl implements ModuleUsersService {

    @Autowired
    ModuleUsersRepo moduleUsersRepo;

    @Override
    public Integer saveModuleUsers(ModulesUsersEntity entity) throws Exception {
        return moduleUsersRepo.save(entity).getId();
    }
}
