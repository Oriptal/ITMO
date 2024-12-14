package Team.Human.ImaginaryHuman;

import Item.Clothes.Armor.BulletproofLegs;
import Item.Clothes.Armor.BulletproofVest;
import Item.Clothes.Armor.Helmet;
import Item.Weapon.HandWeapons.Knife;
import Item.Weapon.Rifle.Assault.Assault;
import Item.Weapon.Rifle.Assault.AssaultType;
import Item.Weapon.Rifle.Pistol.Pistol;
import Item.Weapon.Rifle.Pistol.PistolType;
import Item.Weapon.Rifle.Shotgun.Shotgun;
import Item.Weapon.Rifle.Shotgun.ShotgunType;
import java.util.Random;

public class Policy extends ImaginaryHuman {
    public Policy(String name) {
        super(name, ImaginaryHumanType.POLICY);
    }

    @Override
    protected void addRifle() {
        Random rand = new Random();
        int weaponType = rand.nextInt(3);
        this.inventory.add(switch (weaponType) {
            case 0->(new Assault(AssaultType.M4A1_S));
            case 1->(new Pistol(PistolType.MAKAROV));
            case 2->(new Shotgun(ShotgunType.Nova));
            default -> throw new IllegalStateException("Unexpected value: " + weaponType);
        });
    }

    @Override
    protected void addHandWeapon() {
        Random rand = new Random();
        this.inventory.add(new Hands());
        int hasKnife = rand.nextInt(2);
        if (hasKnife == 1) {
            this.inventory.add(new Knife());
        }
    }

    @Override
    protected void addClothes() {
        this.clothes.put("Legs", new BulletproofLegs());
        this.clothes.put("Chest", new BulletproofVest());
        this.clothes.put("Head", new Helmet());
    }
}
