package Item;

public interface Describable {
    default String getDescription() {
        return this.toString();
    }
}
