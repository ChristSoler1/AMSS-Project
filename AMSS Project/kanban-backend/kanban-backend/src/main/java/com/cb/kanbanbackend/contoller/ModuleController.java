package com.cb.kanbanbackend.contoller;

import com.cb.kanbanbackend.dto.req.ModuleReqDto;
import com.cb.kanbanbackend.dto.res.ResponseDto;
import com.cb.kanbanbackend.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

//Spring Boot Controller to handle HTTP requests and interacts with ModulServices includes:
// Annotations
// Dependency Injections
// Endpoint Methods
// Logging
// ResponseDto
// Exception Handling

@RestController
@RequestMapping("/v1/kb/module")
public class ModuleController {
    @Autowired
    ModuleService moduleService;

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto CreateModule(@RequestBody ModuleReqDto entity) throws Exception {
        System.out.println("/v1/kb/module/create  req : " + entity);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponseCode(HttpStatus.OK.toString());
        responseDto.setResponseMsg("MODULE_CREATED");
        responseDto.setContent(moduleService.CreateModule(entity));
        return responseDto;

    }

    @GetMapping(value = "/getall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto GetModules() throws Exception {
        System.out.println("/v1/kb/tasks/getall");

        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponseCode(HttpStatus.OK.toString());
        responseDto.setResponseMsg("SUCCESS");
        responseDto.setContent(moduleService.GetModules());
        return responseDto;

    }
}
