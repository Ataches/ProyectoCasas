package presentation;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ContactBook extends JFrame {

    private final Container container;

    void Main() {
        ContactBook r = new ContactBook();
        r.setSize(400, 400);
        r.setVisible(true);
    }

    ContactBook() {
        super("Resultado de la busqueda");

        container = getContentPane();
        container.setLayout(null);

        showContactBookData();
    }

    void showContactBookData() {
        try {
            File originalFile = new File("ContactBook.xls");
            Workbook original = Workbook.getWorkbook(originalFile);
            Sheet sheet = original.getSheet(0);
            for (int rowIndex = 0; rowIndex < sheet.getRows(); rowIndex++) {
                StringBuilder stringCell = new StringBuilder();
                for (int columnIndex = 0; columnIndex < sheet.getColumns(); columnIndex++) {
                    Cell sheetCell = sheet.getCell(columnIndex, rowIndex);
                    stringCell.append(sheetCell.getContents());
                    if (columnIndex + 1 < sheet.getColumns())
                        stringCell.append(" / ");
                }
                JLabel labelCell = new JLabel();
                labelCell.setBounds(10, 20 + (rowIndex * 20), 200, 23);
                labelCell.setText(stringCell.toString());
                container.add(labelCell);
            }
        } catch (BiffException | IOException exception) {
            System.out.println("Error searching data");
        }
    }
}
