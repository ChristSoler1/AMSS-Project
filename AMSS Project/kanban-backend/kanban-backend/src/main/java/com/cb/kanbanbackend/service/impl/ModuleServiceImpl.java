package com.cb.kanbanbackend.service.impl;

import com.cb.kanbanbackend.dto.req.ModuleReqDto;
import com.cb.kanbanbackend.dto.res.ModuleResDto;
import com.cb.kanbanbackend.entity.ModulesEntity;
import com.cb.kanbanbackend.repo.ModuleRepo;
import com.cb.kanbanbackend.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    ModuleRepo moduleRepo;

    @Override
    public Integer CreateModule(ModuleReqDto entity) {
        ModulesEntity modules = new ModulesEntity(entity);
        modules  = moduleRepo.save(modules);
        return modules.getId();
    }

    @Override
    public List<ModuleResDto> GetModules() {
        return moduleRepo.getModules();
    }
}
