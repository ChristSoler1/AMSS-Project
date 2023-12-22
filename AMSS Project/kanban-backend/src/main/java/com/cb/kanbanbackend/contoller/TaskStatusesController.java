package com.cb.kanbanbackend.contoller;

import com.cb.kanbanbackend.dto.res.ResponseDto;
import com.cb.kanbanbackend.service.TaskStatusesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TaskStatusesController ist zuständig für die Verarbeitung von HTTP-Anfragen,
 * die sich auf den Status von Aufgaben beziehen. Dieser Controller verwendet das
 * TaskStatusesService, um Informationen über verschiedene Aufgabenstatus zu
 * verwalten und bereitzustellen. Er ist ein Teil der REST-API der Anwendung und
 * ermöglicht es, Informationen über die verschiedenen Status von Aufgaben abzurufen.
 */

@RestController
@RequestMapping("v1/kb/taskstatuses")
public class TaskStatusesController {
    @Autowired
    TaskStatusesService taskStatusesService;

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto getTaskStatus() throws Exception {

        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponseCode(HttpStatus.OK.toString());
        responseDto.setResponseMsg("SUCCESS");
        responseDto.setContent(taskStatusesService.getTaskStatus());
        return responseDto;

    }
}
