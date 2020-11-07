package presentation;

import jxl.Cell;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ContactBook extends JFrame {

    JLabel arreglo[] = new JLabel[40];

    void Main() {
        ContactBook r = new ContactBook();
        r.setSize(500, 900);
        r.setVisible(true);
    }

    ContactBook() {
        super("Resultado de la busqueda");

        Container c = getContentPane();
        c.setLayout(null);

        for (int i = 0; i <= 39; i++) {
            arreglo[i] = new JLabel();
            c.add(arreglo[i]);
        }
        mostrar();
    }

    void mostrar() {
        try {
            // Se abre original y se copia la info a la copia; se modifica la copia.
            File entra = new File("Agenda.xls");
            Workbook original = Workbook.getWorkbook(entra);
            WritableWorkbook copia = Workbook.createWorkbook(new File("copiaAg.xls"), original);
            WritableSheet sheet = copia.getSheet(0);
            int cont = 1, i = 0, aux = 0;
            while (cont != -1) {
                i++;
                System.out.println("i: " + i);
                for (int j = 0; j <= 1; j++) {
                    aux++;
                    Cell dato = sheet.getCell(j, i);
                    String sdato = dato.getContents();
                    arreglo[aux].setText(sdato);
                    System.out.println("dato: " + sdato + " j: " + j);
                    if (j == 1) {
                        arreglo[aux].setBounds(10 + (190), 20 + ((i - 1) * (20)), 200, 23);
                    } else {
                        arreglo[aux].setBounds(10, 20 + ((i - 1) * (20)), 200, 23);
                    }
                    if (sdato.equals("")) {
                        cont = -1;
                        break;
                    }

                }
            }
        } catch (IOException ex) {
            System.out.println("error la base de datos esta abierta");
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }
}
