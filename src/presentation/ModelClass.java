package presentation;

public class ModelClass {

    private static ViewClass viewClass;

    public static void startHome() {
        viewClass = new ViewClass();
        viewClass.startHome();
    }
}
