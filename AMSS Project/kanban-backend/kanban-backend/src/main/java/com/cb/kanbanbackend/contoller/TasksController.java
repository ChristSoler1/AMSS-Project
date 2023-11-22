package com.cb.kanbanbackend.contoller;

import com.cb.kanbanbackend.dto.req.TaskReqDto;
import com.cb.kanbanbackend.dto.res.ResponseDto;
import com.cb.kanbanbackend.service.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

//Controller for the tasks

@RestController
@RequestMapping("/v1/kb/tasks")
public class TasksController {

    @Autowired
    TasksService tasksService;

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto CreateTask(@RequestBody TaskReqDto entity) throws Exception {
        System.out.println("/v1/kb/tasks/create  req : " + entity);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponseCode(HttpStatus.OK.toString());
        responseDto.setResponseMsg("TASK_CREATED");
        responseDto.setContent(tasksService.CreateTask(entity));
        return responseDto;

    }

    @GetMapping(value = "/getbymodule/{mid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto GetTasksByModuleId(@PathVariable("mid") Integer mid) throws Exception {
        System.out.println("/v1/kb/tasks/getbymodule/" + mid);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponseCode(HttpStatus.OK.toString());
        responseDto.setResponseMsg("SUCCESS");
        responseDto.setContent(tasksService.GetTasksByModuleId(mid));
        return responseDto;

    }
}

