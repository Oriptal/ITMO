package Item;

import Team.Human.ImaginaryHuman.ImaginaryHuman;

public interface IRepurchasing {
    default void purchase(ImaginaryHuman human, Item item) {
        if (item.getDurability() == 0 && human.getMoneyAmount() >= item.cost) {
            item.setDurability(100);
            human.spendMoney(item.cost);
            System.out.println("купив " + item);
        } else if (item.getDurability() == 0) {
            System.out.println("Попытался восстановить " + item + ", но не хватило денег.");
        }
    }
}
