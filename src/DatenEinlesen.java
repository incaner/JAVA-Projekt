import java.io.*;
import java.util.*;

public class DatenEinlesen {
    String dateipfad;
    Map<Integer, Person> personMap = new HashMap<>();
    Map<Integer, Produkte> produktMap = new HashMap<>();
    Map<Integer, Firma> firmaMap = new HashMap<>();
    // Konstruktor der Klasse
    public DatenEinlesen(String dateiPfad){
        this.dateipfad = dateiPfad;
    }
    public void readData(String filepath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            String entityType = "";

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("New_Entity: ")) {
                    entityType = line.replace("New_Entity: ", "").trim();
                } else {
                    // Aufteilen der Zeile in ihre Bestandteile und Entfernen von Anführungszeichen
                    String[] data = line.replace("\"", "").split(",");
                    //Bei Jeder neuen Entität wird der erste String zusammengeschnitten und überprüft, welche Art von Objet erstellt werden muss.
                    switch(entityType) {
                        case "\"person_id\", \"person_name\", \"person_gender\"":
                            //Erstellen einer Person und speichern in der Hashmap personMap
                            int personId = Integer.parseInt(data[0]);
                            if (!personMap.containsKey(personId)) {
                                Person person = new Person(personId, data[1], data[2]);
                                personMap.put(person.getPersonID(), person);
                            }
                            break;
                        case "\"product_id\",\"product_name\"":
                            int productId = Integer.parseInt(data[0]);
                            if (!produktMap.containsKey(productId)) {
                                Produkte produkt = new Produkte(productId, data[1]);
                                produktMap.put(produkt.getProduktID(), produkt);
                            }
                            break;
                        case "\"company_id\",\"company_name\"":
                            int companyId = Integer.parseInt(data[0]);
                            if (!firmaMap.containsKey(companyId)) {
                                Firma firma = new Firma(companyId, data[1]);
                                firmaMap.put(firma.getFirmaID(), firma);
                            }
                            break;
                        case "\"person1_id\",\"person2_id\"":
                            //Hier werden die Beziehungen zwischen Personen festgelegt mit der funktion addFreund.
                            //Es werden beide Personen anhand der ID gesucht und dann equivalente Freundschaften verteilt.
                            Person person1 = personMap.get(Integer.parseInt(data[0]));
                            Person person2 = personMap.get(Integer.parseInt(data[1]));
                            if (person1 != null && person2 != null) {
                                person1.addFreund(person2);
                                person2.addFreund(person1);  // Freundschaft ist gegenseitig
                            }
                            break;
                        case "\"person_id\",\"product_id\"":
                            //Selbe Logik wie bei den Beziehungen, nur diesmal mit der addProdunkt Funktion von Produkte.
                            Person pers = personMap.get(Integer.parseInt(data[0]));
                            Produkte prod = produktMap.get(Integer.parseInt(data[1]));
                            if (pers != null && prod != null) {
                                pers.addProdukt(prod);
                            }
                            break;
                        case "\"product_id\",\"company_id\"":
                            Produkte produk = produktMap.get(Integer.parseInt(data[0]));
                            Firma firm = firmaMap.get(Integer.parseInt(data[1]));
                            if (produk != null && firm != null) {
                                produk.setZugehoerigeFirma(firm);
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (IOException e) {
            // Ausgabe von Fehlern, die während des Lesens der Datei passieren können
            e.printStackTrace();
        }
    }
    // Methode zur Suche von Personen nach einem Suchbegriff -> in diesem Fall der Name.
    // Sucht alle Personen und gibt eine Liste von Personen zurück,
    // sobald ein Teil vom Suchbegriff im Namen gefunden wird.
    public List<Person> suchePersonen(String suchbegriff) {
        List<Person> ergebnis = new ArrayList<>();
        for (Person person : personMap.values()) {
            if (person.getPersonName().toLowerCase().contains(suchbegriff.toLowerCase())) {
                ergebnis.add(person);
            }
        }
        return ergebnis;
    }
    // Methode zur Suche einer Person nach ID
    // Durchsucht alle Personen und gibt die Person zurück, deren ID der gesuchten ID entspricht.
    public Person suchePerson(int iD){
        for (Person person : personMap.values()){
            if (person.getPersonID() == iD){
                return person;
            }
        }
        return null;
    }
    // Methode zur Suche von Produkten nach einem Suchbegriff
    // Sucht alle Produkte und gibt eine Liste von Produkten zurück,
    // sobald ein Teil vom Suchbegriff im Namen gefunden wird.
    public List<Produkte> sucheProdukte(String suchbegriff) {
        List<Produkte> ergebnis = new ArrayList<>();

        for (Produkte produkt : produktMap.values()) {
            if (produkt.getProduktName().toLowerCase().contains(suchbegriff.toLowerCase())) {
                ergebnis.add(produkt);
            }
        }

        return ergebnis;
    }
    /* Methode zur Erstellung eines Produkt-Netzwerks für eine bestimmte Person
     Zuerst wird Person anhand ihrer ID aus der personMap gesucht.
     Wenn die Person existiert, wird durch die Liste ihrer Freunde iteriert.
     Für jeden Freund wird dann die Liste seiner gekauften Produkte durchlaufen.
     Dann wird überprüft, ob das Produkt bereits in produktNetzwerk vorhanden ist.
     Wenn nein, wird es zum produktNetzwerk hinzugefügt.
     Schließlich wird das Netzwerk sortiert und zurückgegeben.
     */
    public List<Produkte> getProduktNetzwerk(int personID) {
        Person person = personMap.get(personID);
        if (person == null) {
            return Collections.emptyList();
        }

        List<Produkte> personProdukte = person.getGekaufteProdukte();
        List<Produkte> produktNetzwerk = new ArrayList<>();

        for (Person freund : person.getFreunde()) {
            for (Produkte produkt : freund.getGekaufteProdukte()) {
                if (!personProdukte.contains(produkt) && !produktNetzwerk.contains(produkt)) {
                    produktNetzwerk.add(produkt);
                }
            }
        }
        // Sortieren der Produktliste nach Name
        produktNetzwerk.sort(Comparator.comparing(Produkte::getProduktName));

        return produktNetzwerk;
    }

    /* Methode zur Erstellung eines Firmen-Netzwerks
    Wie bei der getProduktNetzwerk-Methode wird zuerst die Person anhand ihrer ID gesucht.
    Wenn die Person existiert, wird durch die Liste ihrer Freunde iteriert.
    Für jeden Freund wird die Liste seiner gekauften Produkte durchlaufen.
    Die zugehörige Firma jedes Produkts wird ermittelt.
    Wenn die Firma existiert und die Person selbst noch kein Produkt von dieser Firma gekauft hat,
    wird die Firma zum firmenNetzwerk hinzugefügt. Schließlich wird es nach den Namen der Firmen sortiert.
    */
    public List<Firma> getFirmenNetzwerk(int personID) {
        Person person = suchePerson(personID);
        if (person == null) {
            return new ArrayList<>();
        }
        Set<Firma> firmenNetzwerk = new HashSet<>();
        for (Person freund : person.getFreunde()) {
            for (Produkte produkt : freund.getGekaufteProdukte()) {
                Firma firma = produkt.getZugehoerigeFirma();
                if (firma != null && !person.hatProduktVonFirma(firma)) {
                    firmenNetzwerk.add(firma);
                }
            }
        }
        List<Firma> firmenListe = new ArrayList<>(firmenNetzwerk);
        firmenListe.sort(Comparator.comparing(Firma::getFirmaName));
        return firmenListe;
    }
}
