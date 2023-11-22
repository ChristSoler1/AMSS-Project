package com.cb.kanbanbackend.service;

import com.cb.kanbanbackend.dto.req.ModuleReqDto;
import com.cb.kanbanbackend.dto.res.ModuleResDto;
import com.cb.kanbanbackend.entity.ModulesEntity;

import java.util.List;

public interface ModuleService {
    Integer CreateModule(ModuleReqDto entity);

    List<ModuleResDto> GetModules();
}
