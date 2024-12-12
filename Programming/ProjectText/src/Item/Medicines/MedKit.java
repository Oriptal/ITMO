package Item.Medicines;

public class MedKit extends Medicines{
    public MedKit() {
        super(85, 35, 1.5, 1.2, 100);
    }

    @Override
    protected String describe() {
        return "Medkit";
    }
}
