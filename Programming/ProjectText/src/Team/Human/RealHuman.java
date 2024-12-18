package Team.Human;

public class RealHuman extends Human {
    public RealHuman(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Игрок " + super.getName() + " ";
    }
}
