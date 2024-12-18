package Team.Human;

public enum Effect {
    SHOKED("шокирован"),
    INTERSTED("заинтересован"),
    OVERWEIGHT("перегружен"),
    SLOPPINES("расстерян");

    final private String effectDescription;

    Effect(String effectDescription) {
        this.effectDescription = effectDescription;
    }

    public String toString() {
        return effectDescription;
    }
}
