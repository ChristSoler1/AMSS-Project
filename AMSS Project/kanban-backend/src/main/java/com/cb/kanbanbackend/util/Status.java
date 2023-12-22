package com.cb.kanbanbackend.util;

/**
 * Die Klasse Status definiert Konstanten für verschiedene Status- und Prioritätswerte,
 * die in der Anwendung verwendet werden, insbesondere im Zusammenhang mit der Verwaltung
 * von Aufgaben. Diese Konstanten dienen dazu, den Code lesbarer und wartbarer zu machen,
 * indem sie klare, aussagekräftige Bezeichner für verschiedene Zustände und Prioritäten
 * von Aufgaben bereitstellen.
 *
 * TASK_STATUS_* Konstanten repräsentieren die verschiedenen möglichen Zustände einer Aufgabe.
 * TASK_PRIORITY_HIGH repräsentiert eine hohe Priorität für eine Aufgabe.
 */
public class Status {
    public static int TASK_STATUS_OPEN = 1;
    public static int TASK_STATUS_IN_PROGRESS = 2;
    public static int TASK_STATUS_FINISHED = 3;
    public static int TASK_STATUS_LEARNED = 4;
    public static int TASK_PRIORITY_HIGH = 1;
}
