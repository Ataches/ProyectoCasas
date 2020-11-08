package presentation;

import logic.SearchClass;
import presentation.window.Home;

import javax.swing.*;
import java.util.List;

public class ViewClass {

    private final ControllerClass controllerClass = new ControllerClass(this);

    private static Home home;

    public void startHome(){
        home = new Home(controllerClass);
        home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        home.setSize(1000,600);
        home.setVisible(true);
    }

    public void goSearch() {
        List<String> listSearch = home.getSearchData();
        SearchClass searchClass = new SearchClass(listSearch.get(0), listSearch.get(1), listSearch.get(2), listSearch.get(3), listSearch.get(4));
        searchClass.Main();
        searchClass.dispose();
    }

    public JButton getButtonSend() {
        return home.getButtonSend();
    }

    public JButton getButtonNewRegister() {
        return home.getButtonNewRegister();
    }

    public JButton getButtonContactBook() {
        return home.getButtonContactBook();
    }

    public JButton getButtonModify() {
        return home.getButtonModify();
    }
}
