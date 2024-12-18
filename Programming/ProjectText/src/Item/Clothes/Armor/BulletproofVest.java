package Item.Clothes.Armor;

import Item.Clothes.Clothes;
import Team.Human.ImaginaryHuman.ImaginaryHuman;

public class BulletproofVest extends Clothes {
    public BulletproofVest() {
        super(5.0, 0.8, -2, 0.15, 25);
    }

    @Override
    public void use(ImaginaryHuman... user) {
        user[0].setClothes("Chest", this);
    }

    @Override
    public String toString() {
        return "Бронежилет";
    }
}
