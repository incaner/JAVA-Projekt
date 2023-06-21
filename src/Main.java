import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.out.println("2");
        String dateipfad = "C:\\Users\\rober\\Produktdatenbank\\src\\productprojekt2023.txt"; // Es muss der Absolute Dateipfad entsprechend geändert werden

        DatenEinlesen dateiLeser = new DatenEinlesen(dateipfad);
        List<Produkte> proukteList = dateiLeser.produkteAusDateiEinlesen();
        List<Person> personList =dateiLeser.personenAusDateiEinlesen();
        int counter = 0;
        for (Person person : personList){
            System.out.println("ID: "+ person.getPersonID() + " Name: "+person.getPersonName() + " Gesc: " + person.getPersonGeschlecht());
            counter++;
            if (counter == 10) break;

        }
        for (Produkte produkt : proukteList) {
            System.out.println("ID: " + produkt.getProduktID() + ", Name: " + produkt.getProduktName());
        }
    }
}