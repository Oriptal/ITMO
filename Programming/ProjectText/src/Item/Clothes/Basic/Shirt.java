package Item.Clothes.Basic;

import Item.Clothes.Clothes;
import Team.Human.ImaginaryHuman.ImaginaryHuman;

public class Shirt extends Clothes {
    public Shirt() {
        super(0.3, 0.2, -20, 0.3);
    }

    @Override
    public void use(ImaginaryHuman... user) {
        user[0].setClothes("Chest", this);
    }

    @Override
    protected String describe() {
        return "Shirt";
    }
}
