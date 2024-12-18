package Team.Human.ImaginaryHuman;

public enum ImaginaryHumanType {
    POLICY("полицейских"),
    BANDIT("блатных воров");

    private final String typeName;

    ImaginaryHumanType(String name) {
        this.typeName = name;
    }

    public String toString() {
        return this.typeName;
    }
}
