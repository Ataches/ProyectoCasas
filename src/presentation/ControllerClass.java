package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerClass implements ActionListener {

    private ViewClass viewClass;

    ControllerClass(ViewClass viewClass) {
        this.viewClass = viewClass;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (viewClass.getButtonSend() == actionEvent.getSource()) {
            viewClass.goSearch();
        }
        if (viewClass.getButtonNewRegister() == actionEvent.getSource()) {
            registrarNuevo reg = new registrarNuevo(0);
            reg.Main(0);
            reg.dispose();
        }
        if (viewClass.getButtonContactBook() == actionEvent.getSource()) {
            ContactBook ag = new ContactBook();
            ag.Main();
        }
        if (viewClass.getButtonModify() == actionEvent.getSource()) {
            Modify md = new Modify();
            md.Main();
        }
    }
}
