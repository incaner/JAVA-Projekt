import java.util.*;
public class Firma {
    private final int firmaID;
    private String firmaName;

    public Firma(int iD, String Name){
        this.firmaID = iD;
        this.firmaName = Name;
    }
    public int getFirmaID() {
        return firmaID;
    }
    public String getFirmaName() {
        return firmaName;
    }
}
