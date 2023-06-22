public abstract class Entity {
    private final int id;

    public Entity(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }
    public abstract String getName();

}
