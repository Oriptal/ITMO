package Item.Medicines;

import Team.Human.Effect;
import Team.Human.ImaginaryHuman.ImaginaryHuman;

public class Syringe extends Medicines {
    public Syringe() {
        super(0, 15, 0.3, 0.3, 50);
    }

    @Override
    public void use(ImaginaryHuman... humans) {
        var user = humans[0];
        if (this.getDurability() == 0 && user.getMoneyAmount() >= this.cost) {
            this.setDurability(100);
            user.spendMoney(this.cost);
        }
        if (this.getDurability() > 0) {
            user.addEffect(Effect.INTERSTED, 7);
        }
    }

    @Override
    public String toString() {
        return "Шприц";
    }
}
