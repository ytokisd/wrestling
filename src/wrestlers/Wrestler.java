package wrestlers;

public abstract class Wrestler {
    private String name;

    public Wrestler( String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
