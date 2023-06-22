public class Produkte extends Entity{
    //private final int produktID;
    private String ProduktName;

    public Produkte(int produktID, String Name){
        super(produktID);
        //this.produktID = produktID;
        this.ProduktName = Name;
    }

    public String getName() {
        return ProduktName;
    }

}
