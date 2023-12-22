package com.cb.kanbanbackend.contoller;

import com.cb.kanbanbackend.dto.res.ResponseDto;
import com.cb.kanbanbackend.service.PrioritiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PrioritiesController ist zuständig für die Verarbeitung von HTTP-Anfragen,
 * die sich auf Prioritäten beziehen. Dieser Controller verwendet das PrioritiesService,
 * um Prioritätsdaten zu verwalten und bereitzustellen. Er ist ein Teil der REST-API
 * der Anwendung und ermöglicht es, Prioritätsinformationen abzurufen.
 */

@RestController
@RequestMapping("v1/kb/priorities")
public class PrioritiesController {
    @Autowired
    PrioritiesService prioritiesService;

    /**
     * Ruft die Liste aller verfügbaren Prioritäten ab.
     *
     * @return Eine ResponseDto mit der Liste der Prioritäten.
     * @throws Exception bei Fehlern während des Abrufens der Prioritätsdaten.
     */

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto getPriorities() throws Exception {

        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponseCode(HttpStatus.OK.toString());
        responseDto.setResponseMsg("SUCCESS");
        responseDto.setContent(prioritiesService.getPriorities());
        return responseDto;

    }

}
