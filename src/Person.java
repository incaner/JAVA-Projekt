import java.util.*;
public class Person {
    private final int personID;
    private String personName;
    private String personGeschlecht;
    //Jede Person hat eine Liste. Dort werden alle Produkte und Personen abgespeichert,
    //mit denen die Person eine Beziehung hat. So umgehe ich meinen ersten Versuch
    //von einer extra Beziehungsklasse.
    private List<Produkte> gekaufteProdukte;
    private List<Person> freunde;

    public Person(int personID, String Name, String geschlecht) {
        this.personID = personID;
        this.personName = Name;
        this.personGeschlecht = geschlecht;
        this.gekaufteProdukte = new ArrayList<>();
        this.freunde = new ArrayList<>();
    }

    public int getPersonID() {
        return personID;
    }

    public String getPersonName() {
        return personName;
    }

    public String getPersonGeschlecht() {
        return personGeschlecht;
    }

    public void addProdukt(Produkte produkt) {
        this.gekaufteProdukte.add(produkt);
    }
    public void addFreund(Person freund) {
        this.freunde.add(freund);
    }

    public List<Produkte> getGekaufteProdukte() {
        return gekaufteProdukte;
    }

    public List<Person> getFreunde() {
        return freunde;
    }
    //diese Methode erleichtert und verschönert die Überprüfung von Firmensuche.
    //Es wird überprüft, ob die Person bei ein Produkt einer Firma hat.
    public boolean hatProduktVonFirma(Firma firma) {
        for (Produkte produkt : this.gekaufteProdukte) {
            if (produkt.getZugehoerigeFirma().equals(firma)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Person [id=" + personID + ", name=" + personName + ", gender=" + personGeschlecht + ", gekaufte Produkte=" + gekaufteProdukte + "]";
    }

}