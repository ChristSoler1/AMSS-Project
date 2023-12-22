package com.cb.kanbanbackend.contoller;

import com.cb.kanbanbackend.dto.req.LoginReqDto;
import com.cb.kanbanbackend.dto.res.ResponseDto;
import com.cb.kanbanbackend.entity.UsersEntity;
import com.cb.kanbanbackend.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * UsersController ist zuständig für die Verarbeitung von HTTP-Anfragen,
 * die sich auf Benutzerkonten beziehen. Dieser Controller verwendet das
 * UsersService, um Benutzerkonten zu verwalten. Er ist ein Teil der REST-API
 * der Anwendung und ermöglicht es, Benutzerkonten zu erstellen, zu aktualisieren,
 * sich anzumelden und Benutzerinformationen abzurufen.
 */

@RestController
@RequestMapping("/v1/kb/users")
public class UsersController {

    @Autowired
    UsersService usersService;

    /**
     * Erstellt ein neues Benutzerkonto.
     *
     * @param entity Die Daten des Benutzers, die für die Kontoerstellung verwendet werden.
     * @return Eine Antwort, die den Erfolg der Kontoerstellung anzeigt.
     */

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto SignUp(@RequestBody UsersEntity entity) throws Exception {

        usersService.CreateUser(entity);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponseCode(HttpStatus.OK.toString());
        responseDto.setResponseMsg("ACCOUNT_CREATED");
        responseDto.setContent(null);
        return responseDto;

    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto Update(@RequestBody UsersEntity entity) throws Exception {

        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponseCode(HttpStatus.OK.toString());
        responseDto.setResponseMsg("ACCOUNT_UPDATED");
        responseDto.setContent(usersService.Update(entity));
        return responseDto;

    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto login(@RequestBody LoginReqDto entity) throws Exception {

        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponseCode(HttpStatus.OK.toString());
        responseDto.setResponseMsg("USER_AUTHENTICATED");
        responseDto.setContent(usersService.LoginUser(entity));
        return responseDto;

    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto getUserByUserID(@PathVariable("id") Integer id) throws Exception {

        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponseCode(HttpStatus.OK.toString());
        responseDto.setResponseMsg("SUCCESS");
        responseDto.setContent(usersService.getUserByUserID(id));
        return responseDto;

    }

}
