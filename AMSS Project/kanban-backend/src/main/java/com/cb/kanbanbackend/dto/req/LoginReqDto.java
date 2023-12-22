package com.cb.kanbanbackend.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// Die Annotationen von Project Lombok helfen die Boilerplate-Code, wie Getter, Setter, toString- und Konstruktorenmethoden automatisch zu generieren.

// Res = Response
// Req = Request
@AllArgsConstructor // Erzeugt einen Konstruktor mit arguments für alle Felder
@NoArgsConstructor  // Erzeugt einen leeren Konstruktor
@Data               // Erzeugt Getter, Setter und toString Methoden
@ToString           // Überschreibt die toString Methode für eine schöner Ausgabe
// Die Klasse `LoginReqDto` ist ein Data Transfer Object (DTO), welches genutzt wird um die Anmeldedaten vom Client an den Server zu übertragen.
// DTOs verbessern die Lesbarkeit und die Wartbarkeit des Codes, weil sie eine klare Trennung der Daten für jede Anwendungsschicht ermöglichen.
public class LoginReqDto {
    private String email;    // Das E-Mail, das vom Benutzer für die Anmeldung eingeben wird
    private String password; // Das Passwort, das vom Benutzer für die Anmeldung eingeben wird
}