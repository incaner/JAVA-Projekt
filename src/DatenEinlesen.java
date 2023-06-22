import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class DatenEinlesen {
    private String dateipfad;

    public DatenEinlesen(String dateipfad) {
        this.dateipfad = dateipfad;
    }
    public List<Produkte> produkteAusDateiEinlesen() {
        List<Produkte> produkteList = new ArrayList<>();
        boolean inProdukteAbschnitt = false;


        try (BufferedReader reader = new BufferedReader(new FileReader(dateipfad))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("New_Entity:")) {
                    String entity = line.substring("New_Entity:".length()).trim();
                    if (entity.equals("\"product_id\",\"product_name\"")) {
                        inProdukteAbschnitt = true;
                    } else {
                        inProdukteAbschnitt = false;
                    }
                } else if (inProdukteAbschnitt) {
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        int id = Integer.parseInt(parts[0].trim().replaceAll("\"", ""));
                        String name = parts[1].trim().replaceAll("\"", "");
                        Produkte produkt = new Produkte(id, name);
                        produkteList.add(produkt);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return produkteList;
    }
    public List<Person> personenAusDateiEinlesen() {
        List<Person> personList = new ArrayList<>();
        boolean inPersonenAbschnitt = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(dateipfad))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("New_Entity:")) {
                    String entity = line.substring("New_Entity:".length()).trim();
                    if (entity.equals("\"person_id\", \"person_name\", \"person_gender\"")) {
                        inPersonenAbschnitt = true;
                    } else {
                        inPersonenAbschnitt = false;
                    }
                } else if (inPersonenAbschnitt) {
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        int id = Integer.parseInt(parts[0].trim().replaceAll("\"", ""));
                        String name = parts[1].trim().replaceAll("\"", "");
                        String geschlecht = parts[2].trim().replaceAll("\"", "");
                        Person person = new Person(id,name,geschlecht);
                        personList.add(person);

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return personList;
    }
    public List<Entity> datenAusDateiEinlesen(String entityType) {
        List<Entity> entityList = new ArrayList<>();
        /*
        List<Person> personList = new ArrayList<>();
        List<Produkte> produkteList = new ArrayList<>();
        List<Firma> firmaList = new ArrayList<>();
        List<Beziehungen> beziehungenList = new ArrayList<>();
         */
        boolean inProdukteAbschnitt = false;
        boolean inPersonenAbschnitt = false;
        boolean inFirmaAbschnitt = false;
        boolean inBeziehungenAbschnitt = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(dateipfad))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("New_Entity:")) {
                    String entity = line.substring("New_Entity:".length()).trim();
                    if (entity.equals("\"product_id\",\"product_name\"")) {
                        inProdukteAbschnitt = true;
                    } else if (entity.equals("\"person_id\",\"person_name\",\"person_gender\"")) {
                        inPersonenAbschnitt = true;
                    } else if (entity.equals("\"company_id\",\"company_name\"")){
                        inFirmaAbschnitt = true;
                    } else if (entity.equals("\"person1_id\",\"person2_id\"")) {
                        inBeziehungenAbschnitt = true;
                    } else {
                        inProdukteAbschnitt = false;
                        inPersonenAbschnitt = false;
                        inFirmaAbschnitt = false;
                        inBeziehungenAbschnitt = false;
                    }
                } else if (inProdukteAbschnitt && entityType.equals("produkt")) {
                    // Verarbeite Produkte
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        int id = Integer.parseInt(parts[0].trim().replaceAll("\"", ""));
                        String name = parts[1].trim().replaceAll("\"", "");
                        Produkte produkt = new Produkte(id, name);
                        entityList.add(produkt);
                    }
                } else if (inPersonenAbschnitt && entityType.equals("person")) {
                    // Verarbeite Personen
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        int id = Integer.parseInt(parts[0].trim().replaceAll("\"", ""));
                        String name = parts[1].trim().replaceAll("\"", "");
                        String gender = parts[2].trim().replaceAll("\"", "");
                        System.out.println(name);
                        Person person = new Person(id, name, gender);
                        entityList.add(person);
                    }
                }else if (inFirmaAbschnitt && entityType.equals("firma")) {
                    // Verarbeite Firma
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        int id = Integer.parseInt(parts[0].trim().replaceAll("\"", ""));
                        String name = parts[1].trim().replaceAll("\"", "");
                        Firma firma = new Firma(id,name);
                        entityList.add(firma);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
          return entityList;
    }

}
