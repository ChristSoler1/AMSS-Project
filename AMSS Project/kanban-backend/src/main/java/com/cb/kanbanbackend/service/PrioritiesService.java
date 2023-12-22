package com.cb.kanbanbackend.service;

import com.cb.kanbanbackend.entity.PrioritiesEntity;

import java.util.List;

//Interface Priorities
public interface PrioritiesService {
    List<PrioritiesEntity> getPriorities() throws Exception;

    String getPriorityNameById(Integer Id);

}
