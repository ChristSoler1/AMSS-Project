package com.cb.kanbanbackend.service.impl;

import com.cb.kanbanbackend.entity.PerformanceRecordsEntity;
import com.cb.kanbanbackend.repo.PerformanceRecordsRepo;
import com.cb.kanbanbackend.service.PerformanceRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Implementiert PerformanceRecordsService

@Service
public class PerformanceRecordsServiceImpl implements PerformanceRecordsService {

    @Autowired
    PerformanceRecordsRepo performanceRecordsRepo;

    @Override
    public List<PerformanceRecordsEntity> getPerformanceRecords() {
        return performanceRecordsRepo.findAll();
    }
}
