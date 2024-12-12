package Item;

import Item.Medicines.Medicines;
import Team.Human.ImaginaryHuman.ImaginaryHuman;

public interface IRepurchasing {
    default void purchase(ImaginaryHuman human, Medicines heal) {
        if (heal.getDurability() == 0 && human.getMoneyAmount() >= heal.cost) {
            heal.setDurability(100);
            human.raiseMoney(heal.cost);
        }
    }
}
