package Item.Medicines;

public class Bandages extends Medicines {
    public Bandages() {
        super(25, 5, 0.3, 0.5, 20);
    }

    @Override
    public String toString() {
        return "Бинты";
    }
}
