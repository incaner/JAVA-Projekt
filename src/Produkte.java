public class Produkte {
    private final int produktID;
    private String ProduktName;

    public Produkte(int produktID, String Name){
        this.produktID = produktID;
        this.ProduktName = Name;
    }

    public int getProduktID() {
        return produktID;
    }
    public String getProduktName() {
        return ProduktName;
    }

}
