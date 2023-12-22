package com.cb.kanbanbackend.contoller;

import com.cb.kanbanbackend.dto.res.ResponseDto;
import com.cb.kanbanbackend.service.PerformanceRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Der PerformanceRecordsController ist verantwortlich für die Verarbeitung von HTTP-Anfragen,
 * die sich auf Leistungsdatensätze (Performance Records) beziehen. Er nutzt das PerformanceRecordsService,
 * um die entsprechenden Daten abzurufen.
 */

@RestController
@RequestMapping("/v1/kb/performancerecords")
public class PerformanceRecordsController {
    @Autowired
    PerformanceRecordsService performanceRecordsService;

    /**
     * Ruft alle Leistungsdatensätze ab.
     * @return Eine Antwort mit den abgerufenen Leistungsdatensätzen.
     * @throws Exception bei Fehlern während des Abrufens der Daten.
     */

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto getPerformanceRecords() throws Exception {

        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponseCode(HttpStatus.OK.toString());
        responseDto.setResponseMsg("SUCCESS");
        responseDto.setContent(performanceRecordsService.getPerformanceRecords());
        return responseDto;

    }
}
