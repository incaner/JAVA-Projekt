import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello world!");
        String dateipfad = "C:\\JAVA-Projekt\\src\\productprojekt2023.txt"; // Es muss der Absolute Dateipfad entsprechend geändert werden
        DatenEinlesen datenEinlesen = new DatenEinlesen(dateipfad);
        datenEinlesen.readData(dateipfad);

        for (String arg : args) {
            if (arg.contains("--personensuche")) {
                // Nehmen des Wertes nach dem Gleichheitszeichen als den Suchbegriff
                String suchbegriff = arg.split("=")[1].replaceAll("\"", "");
                // Durchführen der Suche
                personenSuche(datenEinlesen, suchbegriff);
            } else if (arg.contains("--produktsuche")) {
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
            System.out.println(person.getPersonName());
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
    public static void personenSuche(DatenEinlesen einlesen, String suchbegriff) {
        List<Person> gesuchtePersonen = einlesen.suchePersonen(suchbegriff);
        for (Person person : gesuchtePersonen) {
            System.out.println(person);
        }
    }
    public static void produktSuche(DatenEinlesen einlesen, String suchbegriff) {
        List<Produkte> gesuchteProdukte = einlesen.sucheProdukte(suchbegriff);
        for (Produkte produkt : gesuchteProdukte) {
            System.out.println(produkt);
        }
    }
    public static void zeigeProduktNetzwerk(DatenEinlesen einlesen, int personID) {
        List<Produkte> produktNetzwerk = einlesen.getProduktNetzwerk(personID);
        String produktnamen = produktNetzwerk.stream()
                .map(Produkte::getProduktName)
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .collect(Collectors.joining(","));
        Person person = einlesen.suchePerson(personID);
        if (person == null) {
            System.out.println("Person mit der ID: " + personID + " wurde nicht gefunden.");
            return;
        }
        System.out.println(produktnamen);
        //System.out.println("Produktnetzwerk für "+person.getPersonName() + ": ["+ produktnamen+"]");
    }

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
        //System.out.println("Firmennetzwerk für " + person.getPersonName() + ": [" +firmenNamen+ "]");
    }
}