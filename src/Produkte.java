import java.util.*;
public class Produkte {
    private final int produktID;
    private String ProduktName;
    private List<Firma> zugehoerigeFirma;

    public Produkte(int produktID, String Name){
        this.produktID = produktID;
        this.ProduktName = Name;
        this.zugehoerigeFirma = new ArrayList<>();
    }

    public int getProduktID() {
        return produktID;
    }
    public String getProduktName() {
        return ProduktName;
    }

    public void addFirma(Firma firma) {
        this.zugehoerigeFirma.add(firma);
    }
}