package Team.Human.ImaginaryHuman;

import Item.Clothes.Basic.Jeans;
import Item.Clothes.Basic.Shirt;
import Item.Weapon.Rifle.Assault.Assault;
import Item.Weapon.Rifle.Assault.AssaultType;
import Item.Weapon.Rifle.Pistol.Pistol;
import Item.Weapon.Rifle.Pistol.PistolType;
import Item.Weapon.Rifle.Shotgun.Shotgun;
import Item.Weapon.Rifle.Shotgun.ShotgunType;
import java.util.Random;

public class Bandit extends ImaginaryHuman {
    public Bandit(String name) {
        super(name, ImaginaryHumanType.BANDIT);
    }

    @Override
    protected void addRifle() {
        Random rand = new Random();
        int weaponType = rand.nextInt(3);
        this.inventory.add(switch (weaponType) {
            case 0->(new Assault(AssaultType.AK_74M));
            case 1->(new Pistol(PistolType.LUGER));
            case 2->(new Shotgun(ShotgunType.M1014));
            default -> throw new IllegalStateException("Unexpected value: " + weaponType);
        });
    }

    @Override
    protected void addHandWeapon() {
        this.inventory.add(new Hands());
    }

    @Override
    protected void addClothes() {
        this.clothes.put("Legs", new Jeans());
        this.clothes.put("Chest", new Shirt());
    }
}
