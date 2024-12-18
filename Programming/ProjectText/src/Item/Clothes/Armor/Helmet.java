package Item.Clothes.Armor;

import Item.Clothes.Clothes;
import Team.Human.ImaginaryHuman.ImaginaryHuman;

public class Helmet extends Clothes {
    public Helmet() {
        super(2.0, 0.4, -5, 0.20, 20);
    }

    @Override
    public void use(ImaginaryHuman... user) {
        user[0].setClothes("Head", this);
    }

    @Override
    public String toString() {
        return "Шлем";
    }
}
