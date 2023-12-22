package com.cb.kanbanbackend.service.impl;

import com.cb.kanbanbackend.dto.req.ModuleReqDto;
import com.cb.kanbanbackend.dto.res.ModuleResDto;
import com.cb.kanbanbackend.dto.res.TaskRes;
import com.cb.kanbanbackend.entity.ModulesEntity;
import com.cb.kanbanbackend.entity.ModulesUsersEntity;
import com.cb.kanbanbackend.entity.TasksEntity;
import com.cb.kanbanbackend.exception.CommonException;
import com.cb.kanbanbackend.repo.ModuleRepo;
import com.cb.kanbanbackend.service.ModuleService;
import com.cb.kanbanbackend.service.ModuleUsersService;
import com.cb.kanbanbackend.service.PrioritiesService;
import com.cb.kanbanbackend.service.TasksService;
import com.cb.kanbanbackend.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse ModuleServiceImpl implementiert das Interface ModuleService
 * Diese Implementierung beinhaltet die Erstellung, Aktualisierung, Löschung und Abfrage von
 * Modulen sowie die Verwaltung der ihnen zugeordneten Aufgaben.
 *
 * Ein zentrales Merkmal dieser Implementierung ist die Berechnung des verbleibenden Aufwands
 * für jedes Modul. Für jede Aufgabe innerhalb eines Moduls wird der tatsächliche Aufwand vom
 * verfügbaren Aufwand abgezogen. Dies ermöglicht eine effektive Überwachung und Steuerung
 * des Arbeitsaufwands im Kontext der Modulverwaltung.
 *
 * Die Klasse interagiert eng mit dem ModulRepository (ModuleRepo) und anderen Services wie
 * TasksService und PrioritiesService, um die erforderlichen Daten zu verarbeiten und bereitzustellen.
 */

@Service
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    ModuleRepo moduleRepo;

    @Autowired
    ModuleUsersService moduleUsersService;

    @Autowired
    TasksService tasksService;

    @Autowired
    PrioritiesService prioritiesService;

    @Override
    public Integer CreateModule(ModuleReqDto entity) throws Exception {
        ModulesEntity modules = new ModulesEntity(entity);
        modules.setStartDate(LocalDate.now());
        modules = moduleRepo.save(modules);
        moduleUsersService.saveModuleUsers(new ModulesUsersEntity(modules.getId(), entity.getUserId()));
        return modules.getId();
    }

    @Override
    public List<ModuleResDto> GetModulesByUserId(Integer id) throws Exception {

        // alle Module nach Modul-ID abrufen
        List<ModuleResDto> modules = moduleRepo.getModules(id);

        // Schleife für alle Module, eines nach dem anderen
        modules.forEach(e -> {

            // separate taskRes-Liste erstellen, um offene, in Arbeit befindliche, abgeschlossene und gelernte Aufgaben zu speichern
            List<TaskRes> openTaskList = new ArrayList<>(), inProgressTasksList = new ArrayList<>(), finishedTasksList = new ArrayList<>(), learned = new ArrayList<>();

            try {
                // Aufgaben jedes Moduls nach Modul-ID abrufen
                List<TasksEntity> tasksEntities = tasksService.GetTasksByModuleId(e.getEntity().getId());

                // Schleife für alle Aufgaben, eine nach der anderen
                tasksEntities.forEach(tasksEntity -> {
                    if (tasksEntity.getStatusId() == Status.TASK_STATUS_OPEN) { // wenn die Aufgabe offen ist, in die Liste der offenen Aufgaben aufnehmen
                        openTaskList.add(new TaskRes(tasksEntity, prioritiesService.getPriorityNameById(tasksEntity.getPriorityId())));
                    } else if (tasksEntity.getStatusId() == Status.TASK_STATUS_IN_PROGRESS) { // wenn die Aufgabe in Bearbeitung ist, in die Liste der in Bearbeitung befindlichen Aufgaben aufnehmen
                        inProgressTasksList.add(new TaskRes(tasksEntity, prioritiesService.getPriorityNameById(tasksEntity.getPriorityId())));
                    } else if (tasksEntity.getStatusId() == Status.TASK_STATUS_FINISHED) { // wenn die Aufgabe erledigt ist, wird sie in die Liste der erledigten Aufgaben aufgenommen
                        finishedTasksList.add(new TaskRes(tasksEntity, prioritiesService.getPriorityNameById(tasksEntity.getPriorityId())));
                    } else if (tasksEntity.getStatusId() == Status.TASK_STATUS_LEARNED) { // wenn die Aufgabe gelernt ist, in die Liste der gelernten Aufgaben aufnehmen
                        learned.add(new TaskRes(tasksEntity, prioritiesService.getPriorityNameById(tasksEntity.getPriorityId())));
                    }
                    e.setOpenTasks(openTaskList);
                    e.setInProgressTasks(inProgressTasksList);
                    e.setFinishedTasks(finishedTasksList);
                    e.setLearned(learned);
                });

                //lst-Wert berechnen
                int totalFinishedH = 0;
                int totalLearnedH = 0;
                for (int i = 0; i < finishedTasksList.size(); i++) {
                    totalFinishedH += finishedTasksList.get(i).getEntity().getActualEffort();
                }
                for (int i = 0; i < learned.size(); i++) {
                    totalLearnedH += learned.get(i).getEntity().getActualEffort();
                }
                e.setLstEffort(String.valueOf(e.getEntity().getPresetEffort() - (totalFinishedH + totalLearnedH)));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        return modules;
    }

    @Override
    public Integer UpdateModule(ModuleReqDto entity) throws Exception {
        ModulesEntity modules = new ModulesEntity(entity);
        modules.setId(entity.getId());
        modules = moduleRepo.save(modules);
        return modules.getId();
    }

    @Override
    public Integer DeleteModule(Integer id) {
        try {
            moduleRepo.deleteById(id);
        } catch (Exception e) {
            throw new CommonException("FAILED_TO_DELETE_MODULE");
        }
        return 0;
    }

    @Override
    public List<ModulesEntity> getModules() {

        return moduleRepo.findAll();
    }
}
