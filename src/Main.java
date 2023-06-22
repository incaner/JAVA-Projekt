import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.out.println("2");
        String dateipfad = "C:\\Users\\rober\\Produktdatenbank\\JAVA-Projekt\\src\\productprojekt2023.txt"; // Es muss der Absolute Dateipfad entsprechend geändert werden

        DatenEinlesen dateiLeser = new DatenEinlesen(dateipfad);
        List<Entity> proukteList = dateiLeser.datenAusDateiEinlesen("produkte");
        List<Entity> personList = dateiLeser.datenAusDateiEinlesen("person");
        List<Entity> firmaList = dateiLeser.datenAusDateiEinlesen("firma");
        int counter = 0;
        for (Entity entity  : personList){
            if (entity instanceof Person) {
                Person person = (Person) entity;
                System.out.println("ID: " + person.getId() + " Name: " + person.getName() + " Geschlecht: " + person.getPersonGeschlecht());
                counter++;
                if (counter == 10) break;
            }
        }
        for (Entity entity : proukteList) {
            if (entity instanceof Produkte) {
                Produkte produkt = (Produkte) entity;
                System.out.println("ID: " + produkt.getId() + ", Name: " + produkt.getName());
            }
        }
    }
}