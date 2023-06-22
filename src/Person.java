public class Person  extends Entity {
    //private final int personID;
    private String personName;
    private String personGeschlecht;

    public Person(int personID, String Name, String geschlecht) {
        super(personID);
        this.personName = Name;
        this.personGeschlecht = geschlecht;
    }
    public String getName() {
        return personName;
    }

    public String getPersonGeschlecht() {
        return personGeschlecht;
    }
}