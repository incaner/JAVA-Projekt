import java.util.*;
public class Produkte {
    private final int produktID;
    private String produktName;
    private Firma zugehoerigeFirma;
    public Produkte(int produktID, String Name){
        this.produktID = produktID;
        this.produktName = Name;
    }

    public int getProduktID() {
        return produktID;
    }
    public String getProduktName() {
        return produktName;
    }

    public void setZugehoerigeFirma(Firma zugehoerigeFirma) {
        this.zugehoerigeFirma = zugehoerigeFirma;
    }

    public Firma getZugehoerigeFirma() {
        return zugehoerigeFirma;
    }

    @Override
    public String toString() {
        return " Produkt [id=" + produktID + ", name= " + produktName + " Firma=" + zugehoerigeFirma + "]";
    }

}