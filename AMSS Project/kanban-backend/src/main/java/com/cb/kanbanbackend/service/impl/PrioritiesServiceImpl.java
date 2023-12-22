package com.cb.kanbanbackend.service.impl;

import com.cb.kanbanbackend.entity.PrioritiesEntity;
import com.cb.kanbanbackend.repo.PrioritiesRepo;
import com.cb.kanbanbackend.service.PrioritiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Implementiert PrioritiesService

@Service
public class PrioritiesServiceImpl implements PrioritiesService {

    @Autowired
    PrioritiesRepo prioritiesRepo;

    @Override
    public List<PrioritiesEntity> getPriorities() throws Exception {
        return prioritiesRepo.findAll();
    }

    @Override
    public String getPriorityNameById(Integer id) {
        return prioritiesRepo.getLevelById(id);
    }
}
