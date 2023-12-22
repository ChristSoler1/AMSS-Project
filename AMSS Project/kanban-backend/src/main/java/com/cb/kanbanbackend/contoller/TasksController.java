package com.cb.kanbanbackend.contoller;

import com.cb.kanbanbackend.dto.req.TaskReqDto;
import com.cb.kanbanbackend.dto.res.ResponseDto;
import com.cb.kanbanbackend.service.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * TasksController ist verantwortlich für die Verarbeitung von HTTP-Anfragen,
 * die sich auf Aufgaben (Tasks) beziehen. Dieser Controller verwendet das TasksService,
 * um Aufgaben zu verwalten und bereitzustellen. Er ist ein Teil der REST-API
 * der Anwendung und ermöglicht es, Aufgaben zu erstellen, abzurufen, zu aktualisieren
 * und zu löschen.
 */

@RestController
@RequestMapping("/v1/kb/tasks")
public class TasksController {

    @Autowired
    TasksService tasksService;

    /**
     * Erstellt eine neue Aufgabe basierend auf den übergebenen Daten.
     *
     * @param entity Die Daten der Aufgabe, die erstellt werden sollen.
     * @return Eine Antwort, die den Erfolg der Operation anzeigt.
     */

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto CreateTask(@RequestBody TaskReqDto entity) throws Exception {
        System.out.println("/v1/kb/tasks/create  req : " + entity);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponseCode(HttpStatus.OK.toString());
        responseDto.setResponseMsg("TASK_CREATED");
        responseDto.setContent(tasksService.CreateTask(entity));
        return responseDto;

    }

    /**
     * Ruft alle Aufgaben ab, die einem bestimmten Modul zugeordnet sind.
     *
     * @param mid Die ID des Moduls.
     * @return Eine Liste von Aufgaben, die dem Modul zugeordnet sind.
     */

    @GetMapping(value = "/getbymodule/{mid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto GetTasksByModuleId(@PathVariable("mid") Integer mid) throws Exception {
        System.out.println("/v1/kb/tasks/getbymodule/" + mid);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponseCode(HttpStatus.OK.toString());
        responseDto.setResponseMsg("SUCCESS");
        responseDto.setContent(tasksService.GetTasksByModuleId(mid));
        return responseDto;

    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto UpdateTask(@RequestBody TaskReqDto entity) throws Exception {
        System.out.println("/v1/kb/tasks/update  req : " + entity);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponseCode(HttpStatus.OK.toString());
        responseDto.setResponseMsg("TASK_UPDATED");
        responseDto.setContent(tasksService.UpdateTask(entity));
        return responseDto;

    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto DeleteTask(@PathVariable("id") int id) throws Exception {
        System.out.println("/v1/kb/tasks/delete/" + id);

        tasksService.DeleteTask(id);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponseCode(HttpStatus.OK.toString());
        responseDto.setResponseMsg("TASK_DELETED");
        return responseDto;

    }

    @GetMapping(value = "/getopentasks/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto GetOpenTasks(@PathVariable("userId") int userId) throws Exception {
        System.out.println("/v1/kb/tasks/getopentasks/" + userId);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponseCode(HttpStatus.OK.toString());
        responseDto.setResponseMsg("SUCCESS");
        responseDto.setContent(tasksService.getOpenTasks(userId));
        return responseDto;

    }

    @GetMapping(value = "/gethighprioritytasks/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto GetHighPriorityTasks(@PathVariable("userId") int userId) throws Exception {
        System.out.println("/v1/kb/tasks/gethighprioritytasks/" + userId);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponseCode(HttpStatus.OK.toString());
        responseDto.setResponseMsg("SUCCESS");
        responseDto.setContent(tasksService.getHighPriorityTasks(userId));
        return responseDto;

    }
}

