public class Person {
    private final int personID;
    private String personName;
    private String personGeschlecht;

    public Person(int personID, String Name, String geschlecht) {
        this.personID = personID;
        this.personName = Name;
        this.personGeschlecht = geschlecht;
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
}