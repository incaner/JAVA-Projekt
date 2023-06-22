public class Firma extends Entity {
    private String firmaName;

    public Firma(int firmaID, String firmaName){
        super(firmaID);
        this.firmaName = firmaName;
    }

    public String getName() {
        return firmaName;
    }
}
