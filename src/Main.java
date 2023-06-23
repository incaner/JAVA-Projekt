import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.out.println("2");
        String dateipfad = "C:\\JAVA-Projekt\\src\\productprojekt2023.txt"; // Es muss der Absolute Dateipfad entsprechend geändert werden
        DatenEinlesen datenEinlesen = new DatenEinlesen(dateipfad);
        datenEinlesen.readData(dateipfad);

        for (int i = 0; i < args.length; i++) {
            // Prüfen ob das Argument --personensuche ist
            if (args[i].equals("--personensuche")) {
                // Nehmen des nächsten Arguments als den Suchbegriff
                String suchbegriff = args[i + 1].replaceAll("\"", "");

                // Durchführen der Suche
                personenSuche(datenEinlesen, suchbegriff);
            }else if (args[i].equals("--produktsuche")) {
                // Nehmen des nächsten Arguments als den Suchbegriff
                String suchbegriff = args[i + 1].replaceAll("\"", "");

                // Durchführen der Suche
                produktSuche(datenEinlesen, suchbegriff);
            }
        }
        /*
        int counter = 0;
        System.out.println("Personen:");
        for (Person person : datenEinlesen.personMap.values()) {
            System.out.println(person.getPersonName());
            counter++;
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
    // Funktion Personen suchen
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

}