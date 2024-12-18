package Item.Clothes.Armor;

import Item.Clothes.Clothes;
import Team.Human.ImaginaryHuman.ImaginaryHuman;

public class BulletproofLegs extends Clothes {
    public BulletproofLegs() {
        super(4.0, 0.75, -3, 0.10, 15);
    }

    @Override
    public void use(ImaginaryHuman... user) {
        user[0].setClothes("Legs", this);
    }

    @Override
    public String toString() {
        return "Защитные штаны";
    }
}
