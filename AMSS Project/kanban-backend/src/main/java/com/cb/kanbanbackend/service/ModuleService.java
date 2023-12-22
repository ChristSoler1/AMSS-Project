package com.cb.kanbanbackend.service;

import com.cb.kanbanbackend.dto.req.ModuleReqDto;
import com.cb.kanbanbackend.dto.res.ModuleResDto;
import com.cb.kanbanbackend.entity.ModulesEntity;

import java.util.List;

//Interface Users

public interface ModuleService {
    //CreateModule(ModuleReqDto entity): Diese Methode erwartet ein ModuleReqDto Objekt, das die benötigten Daten zum Erstellen eines Moduls enthält und gibt die ID des erstellten Moduls zurück.
    Integer CreateModule(ModuleReqDto entity) throws Exception;

    //GetModulesByUserId(Integer id): Diese Methode erwartet die ID des Benutzers und gibt eine Liste von ModuleResDto Objekten zurück, die die Module repräsentieren, die dem angegebenen Benutzer zugeordnet sind.
    List<ModuleResDto> GetModulesByUserId(Integer id) throws Exception;

    //UpdateModule(ModuleReqDto entity): Diese Methode erwartet ein ModuleReqDto Objekt, das die benötigten Daten zur Aktualisierung eines bestehenden Moduls enthält und gibt die ID des aktualisierten Moduls zurück.
    Integer UpdateModule(ModuleReqDto entity) throws Exception;

    //DeleteModule(Integer id): Diese Methode erwartet die ID des Moduls, das gelöscht werden soll.
    Integer DeleteModule(Integer id);

    List<ModulesEntity> getModules();

}
