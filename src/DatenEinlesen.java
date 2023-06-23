import java.io.*;
import java.util.*;

public class DatenEinlesen {
    String dateipfad;
    Map<Integer, Person> personMap = new HashMap<>();
    Map<Integer, Produkte> produktMap = new HashMap<>();
    Map<Integer, Firma> firmaMap = new HashMap<>();
    List<Beziehungen> beziehungenList = new ArrayList<>();

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
                    String[] data = line.replace("\"", "").split(",");

                    switch(entityType) {
                        case "\"person_id\", \"person_name\", \"person_gender\"":
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
                            /*Beziehungen beziehung = new Beziehungen(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
                            beziehungenList.add(beziehung);
                             */
                            Person person1 = personMap.get(Integer.parseInt(data[0]));
                            Person person2 = personMap.get(Integer.parseInt(data[1]));
                            if (person1 != null && person2 != null) {
                                person1.addFreund(person2);
                                person2.addFreund(person1);  // Freundschaft ist gegenseitig
                            }
                            break;
                        case "\"person_id\",\"product_id\"":
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
                                produk.addFirma(firm);
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<Person> suchePersonen(String suchbegriff) {
        List<Person> ergebnis = new ArrayList<>();

        for (Person person : personMap.values()) {
            if (person.getPersonName().toLowerCase().contains(suchbegriff.toLowerCase())) {
                ergebnis.add(person);
            }
        }
        return ergebnis;
    }

    public List<Produkte> sucheProdukte(String suchbegriff) {
        List<Produkte> ergebnis = new ArrayList<>();

        for (Produkte produkt : produktMap.values()) {
            if (produkt.getProduktName().toLowerCase().contains(suchbegriff.toLowerCase())) {
                ergebnis.add(produkt);
            }
        }

        return ergebnis;
    }
}
