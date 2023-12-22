package com.cb.kanbanbackend.util;

import com.cb.kanbanbackend.entity.TasksEntity;
import com.cb.kanbanbackend.service.ModuleService;
import com.cb.kanbanbackend.service.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * ScheduledTask ist eine Komponente, die für die Ausführung regelmässiger, zeitgesteuerter Aufgaben
 * in der Anwendung zuständig ist. Diese Klasse nutzt die @Scheduled-Annotation von Spring, um
 * bestimmte Methoden zu festgelegten Zeiten auszuführen.
 *
 * Die Methode runScheduledTaskReminders wird täglich um Mitternacht ausgeführt (konfiguriert durch
 * den CRON-Ausdruck in 'scheduled.task.cron.expression'). Ihre Hauptaufgabe ist es, die Priorität
 * von Aufgaben zu aktualisieren, wenn das Enddatum des zugehörigen Moduls nahe ist.
 *
 * Funktionssicht: wenn ein Task 7 Tage vor Ablauf des erreicht, wird die Prioriät des Tasks automatisch auf hoch
 * eingestellt.
 */

@Component
public class ScheduledTask {

    @Autowired
    TasksService tasksService;

    @Autowired
    ModuleService moduleService;

    @Scheduled(cron = "${scheduled.task.cron.expression}")
    public void runScheduledTaskReminders() {
        System.out.println("Scheduled task executing at: " + LocalDateTime.now());

        // alle Module abrufen und eine Schleife nach der anderen durchlaufen
        moduleService.getModules().forEach(modulesEntity -> {
            try {
                // Aufgabenliste für jedes Modul abrufen.
                for (TasksEntity tasksEntity : tasksService.GetTasksByModuleId(modulesEntity.getId())) {
                    // prüfen, ob die Daten nicht null sind
                    if (null != tasksEntity.getEndDate() || null != modulesEntity.getEndDate()) {
                        // Aktualisierung der Prioritätsstufe auf hoch, wenn der heutige Tag + 7 Tage größer ist als das Enddatum des Moduls
                        if (LocalDate.now().plusDays(7).isAfter(modulesEntity.getEndDate())) {
                            // Prioritätsstufe auf hoch setzen
                            tasksEntity.setPriorityId(Status.TASK_PRIORITY_HIGH);
                            tasksService.UpdateTaskPriority(tasksEntity);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        System.out.println("Scheduled task executed at: " + LocalDateTime.now());
    }
}
