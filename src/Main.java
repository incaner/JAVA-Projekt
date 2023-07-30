import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello world!");
        String dateipfad = "C:\\JAVA-Projekt\\src\\productprojekt2023.txt"; // Es muss der Absolute Dateipfad entsprechend geändert werden

        //Hier wird ein Objekt vom Datentyp DatenEinlesen erstellt. datenEinlesen dient nicht nur zum Einlesen.
        //Es werden auch alle Objekte, die erstellt werden als jeweilige Map darin abgespeichert. -> dient auch später zur Suche.
        DatenEinlesen datenEinlesen = new DatenEinlesen(dateipfad);
        datenEinlesen.readData(dateipfad);
        //nach dem readData sind anhand der Textdatei alle möglichen Objekte von Personen, Produkte usw. erstellt und als Map in datenEinlesen abgespeichert.

        // Analysieren der übergebenen Argumente und Aufrufen der entsprechenden Funktionen.
        for (String arg : args) {
            if (arg.contains("--personensuche")) {
                // Nehmen des Wertes nach dem Gleichheitszeichen als den Suchbegriff
                String suchbegriff = arg.split("=")[1].replaceAll("\"", "");
                // Durchführen der Suche -> Aufruf der Methode.
                personenSuche(datenEinlesen, suchbegriff);
            } else if (arg.contains("--produktsuche")) {
                //Die slebe Logik nur für Produkte.
                String suchbegriff = arg.split("=")[1].replaceAll("\"", "");
                produktSuche(datenEinlesen, suchbegriff);
            } else if (arg.contains("--produktnetzwerk")) {
                int personID = Integer.parseInt(arg.split("=")[1].replaceAll("\"", ""));
                zeigeProduktNetzwerk(datenEinlesen, personID);
            } else if (arg.contains("--firmennetzwerk")) {
                int personIDFirma = Integer.parseInt(arg.split("=")[1].replaceAll("\"", ""));
                zeigeFirmenNetzwerk(datenEinlesen, personIDFirma);
            }
        }
        /*
        int counter = 0;
        System.out.println("Personen:");
        for (Person person : datenEinlesen.personMap.values()) {
            System.out.println(person.getPersonID() + person.getPersonName());
            counter++;
            if (counter == 10){
                break;
            }
        }

        System.out.println("\nProdukte:");
        for (Produkte produkt : datenEinlesen.produktMap.values()) {
            System.out.println(produkt.getProduktName());
        }

        System.out.println("\nFirmen:");
        for (Firma firma : datenEinlesen.firmaMap.values()) {
            System.out.println(firma.getFirmaName());
        }

         */

    }
    //Durchführen der Suche nach Personen basierend auf einem Suchbegriff.
    //Die Suche wird durch das DatenEinlesen-Objekt durchgeführt und die Ergebnisse werden hier nur ausgegeben.
    //Ich habe mich so entschieden, da im DatenEinlesen-Objekt alle Maps von Personen und Produkten enthalten sind.
    public static List<Person> personenSuche(DatenEinlesen einlesen, String suchbegriff) {
        List<Person> gesuchtePersonen = einlesen.suchePersonen(suchbegriff);
        for (Person person : gesuchtePersonen) {
            System.out.println(person);
        }
        return gesuchtePersonen;
    }
    //Durchführen einer Suche nach Produkten basierend auf einem Suchbegriff.
    //Selbe Logik wie bei Personensuche.
    public static void produktSuche(DatenEinlesen einlesen, String suchbegriff) {
        List<Produkte> gesuchteProdukte = einlesen.sucheProdukte(suchbegriff);
        for (Produkte produkt : gesuchteProdukte) {
            System.out.println(produkt);
        }
    }

    //Anzeige des Produkt-Netzwerks einer Person.
    //Zuerst wird überprüft, ob die Person existiert.
    //Wenn die Person nicht existiert, wird eine Fehlermeldung ausgegeben.
    //Wenn die Person existiert, wird das Produkt-Netzwerk für die Person erstellt.
    //Danach wird eine Zeichenkette aus den Produktnamen erstellt,
    //wobei die Namen in alphabetisch sortiert und durch Kommata getrennt sind.
    //Schließlich wird die Zeichenkette ausgegeben.
    public static void zeigeProduktNetzwerk(DatenEinlesen einlesen, int personID) {
        Person person = einlesen.suchePerson(personID); // Suche nach der Person
        if (person == null) { // Überprüfen, ob die Person existiert
            System.out.println("Person mit der ID: " + personID + " wurde nicht gefunden.");
            return;
        }
        List<Produkte> produktNetzwerk = einlesen.getProduktNetzwerk(personID);
        // Erstellen einer sortierten, durch Kommata getrennten Liste der Produktnamen
        String produktnamen = produktNetzwerk.stream()
                .map(Produkte::getProduktName)
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .collect(Collectors.joining(","));
        System.out.println(produktnamen);
    }

    //Anzeige des Firmen-Netzwerks einer Person.
    //Zuerst wird überprüft, ob die Person existiert. -> Selbe Logik wie davor.
    //Wenn die Person existiert, wird das Firmen-Netzwerk für die Person erstellt.
    //Danach wird eine Zeichenkette aus den Firmennamen.
    //Schließlich wird die Zeichenkette ausgegeben.
    public static void zeigeFirmenNetzwerk(DatenEinlesen einlesen, int personID) {
        Person person = einlesen.suchePerson(personID);
        if (person == null) {
            System.out.println("Person mit der ID " + personID + " wurde nicht gefunden.");
            return;
        }
        List<Firma> firmenNetzwerk = einlesen.getFirmenNetzwerk(personID);
        String firmenNamen = firmenNetzwerk.stream()
                .map(Firma::getFirmaName)
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .collect(Collectors.joining(","));
        System.out.println(firmenNamen);
    }
}