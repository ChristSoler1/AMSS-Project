package com.cb.kanbanbackend.service;

import com.cb.kanbanbackend.entity.PerformanceRecordsEntity;

import java.util.List;

//Interface PerformanceRecords
public interface PerformanceRecordsService {
    List<PerformanceRecordsEntity> getPerformanceRecords();
}
