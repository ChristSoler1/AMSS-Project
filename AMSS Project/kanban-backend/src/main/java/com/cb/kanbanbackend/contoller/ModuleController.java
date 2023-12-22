package com.cb.kanbanbackend.contoller;

import com.cb.kanbanbackend.dto.req.ModuleReqDto;
import com.cb.kanbanbackend.dto.res.ResponseDto;
import com.cb.kanbanbackend.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Der ModuleController ist verantwortlich für die Verarbeitung von HTTP-Anfragen,
 * die sich auf Module beziehen. Er nutzt das ModulService, um Geschäftslogikoperationen
 * auszuführen. Dieser Controller ist ein Teil der REST-API der Anwendung.
 */

@RestController
@RequestMapping("/v1/kb/module")
public class ModuleController {
    @Autowired
    ModuleService moduleService;

    /**
     * Erstellt ein neues Modul basierend auf den übergebenen Daten.
     * @param entity Die Daten des Moduls, die erstellt werden sollen.
     * @return Eine Antwort, die den Erfolg der Operation anzeigt.
     */

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto CreateModule(@RequestBody ModuleReqDto entity) throws Exception {
        System.out.println("/v1/kb/module/create  req : " + entity);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponseCode(HttpStatus.OK.toString());
        responseDto.setResponseMsg("MODULE_CREATED");
        responseDto.setContent(moduleService.CreateModule(entity));
        return responseDto;

    }

    /**
     * Ruft alle Module ab, die einem bestimmten Benutzer zugeordnet sind.
     * @param id Die ID des Benutzers.
     * @return Eine Liste von Modulen, die dem Benutzer zugeordnet sind.
     */

    @GetMapping(value = "/getallbyuserid/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto GetModulesByUserId(@PathVariable("id") Integer id) throws Exception {
        System.out.println("/v1/kb/tasks/getallbyuserid/"+id);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponseCode(HttpStatus.OK.toString());
        responseDto.setResponseMsg("SUCCESS");
        responseDto.setContent(moduleService.GetModulesByUserId(id));
        return responseDto;

    }

    /**
     * Aktualisiert die Daten eines bestehenden Moduls.
     * @param entity Die aktualisierten Moduldaten.
     * @return Eine Antwort, die den Erfolg der Aktualisierung anzeigt.
     */

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto UpdateModule(@RequestBody ModuleReqDto entity) throws Exception {
        System.out.println("/v1/kb/module/update  req : " + entity);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponseCode(HttpStatus.OK.toString());
        responseDto.setResponseMsg("MODULE_UPDATED");
        responseDto.setContent(moduleService.UpdateModule(entity));
        return responseDto;

    }

    /**
     * Löscht ein Modul basierend auf seiner ID.
     * @param id Die ID des zu löschenden Moduls.
     * @return Eine Antwort, die den Erfolg des Löschvorgangs anzeigt.
     */

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto DeleteModule(@PathVariable("id") Integer id) throws Exception {
        System.out.println("/v1/kb/module/delete" + id);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponseCode(HttpStatus.OK.toString());
        responseDto.setResponseMsg("MODULE_DELETED");
        responseDto.setContent(moduleService.DeleteModule(id));
        return responseDto;

    }
}
