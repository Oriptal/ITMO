package Team;

import Team.Human.*;
import Team.Human.ImaginaryHuman.Bandit;
import Team.Human.ImaginaryHuman.ImaginaryHuman;
import Team.Human.ImaginaryHuman.ImaginaryHumanType;
import Team.Human.ImaginaryHuman.Policy;
import java.util.*;

public class Team {
    final private static String[] namesDB = {"Wolfgang", "Willow", "Wilson",
            "Webber", "Wurt", "Wigfrid", "Winona", "Wormwood", "Wortex", "Charlie"};
    ArrayList<ImaginaryHuman> team = new ArrayList<>();
    String teamName;

    public Team(String teamName, ImaginaryHuman... team) {
        this.teamName = teamName;
        Collections.addAll(this.team, team);
    }

    public Team(String teamName, ImaginaryHumanType humanType) {
        Random rand = new Random();
        this.teamName = teamName;
        int n = rand.nextInt(10) + 1;
        ArrayList<String> nameList = new ArrayList<>(Arrays.asList(namesDB));
        for (int i = 0; i < n; i++) {
            int nameIndex = rand.nextInt(nameList.size());
            if (humanType == ImaginaryHumanType.POLICY) {
                this.team.add(new Policy(nameList.get(nameIndex)));
            } else {
                this.team.add(new Bandit(nameList.get(nameIndex)));
            }
            nameList.remove(nameIndex);
        }
    }

    public boolean isAlive() {
        boolean res = false;
        for (ImaginaryHuman human : this.team) {
            res = res || (human.isAlive());
        }
        return res;
    }

    public void decreaseEffects() {
        for (ImaginaryHuman human : this.team) {
            for (Effect e: Effect.values()) {
                if (human.getEffectTime(e) > 0) {
                    human.addEffect(e, -1);
                }
            }
            human.repairInventory();
        }
    }

    public void moveAgainst(Team opp) {
        Random rand = new Random();
        Iterator<ImaginaryHuman> iterator = this.team.iterator();
        while (iterator.hasNext()) {
            ImaginaryHuman human = iterator.next();
            if (human.isAlive()) {
                if (!human.hasEffect(Effect.SHOKED)) {
                    int itemType = Math.max(rand.nextInt(2), rand.nextInt(2)); // 1 - Weapon, 0 - Heal
                    if (itemType == 1) {
                        int indexOpp = rand.nextInt(opp.team.size());
                        human.chooseRandom(itemType).use(human, opp.team.get(indexOpp));
                        if (!opp.team.get(indexOpp).isAlive()) {
                            opp.team.remove(indexOpp);
                        }
                    } else {
                        human.chooseRandom(itemType).use(human);
                    }
                }
            } else {
                // Пометить ImaginaryHuman для удаления после завершения итерации
                iterator.remove();
            }
        }
    }
}
