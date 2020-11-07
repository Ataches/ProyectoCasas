package presentation;

import jxl.Cell;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;


@SuppressWarnings("serial")
public class Principal extends JFrame implements ActionListener {

	MediaTracker tracker;
	Toolkit tk; 

	BufferedImage imagen;
	ImageIcon img=null;
	JLabel limg;

	JButton buscar = new JButton ("Buscar en el equipo");
	JButton guardar = new JButton ("Guardar");

	public static void Main() {
		Principal p=new Principal();
		p.setBounds(0, 0, 400, 400);
		p.setLocationRelativeTo(null);
		p.setVisible(true);
	}

	Principal(){
		super("Resultado de la busqueda");

		Container c = getContentPane();
		c.setLayout(null);

		c.add(buscar);
		buscar.setBounds(50, 330, 150, 23);
		c.add(guardar);
		guardar.setBounds(220, 330, 100, 23);

		buscar.addActionListener(this);
		guardar.addActionListener(this);

		tracker = new MediaTracker(this);
	}

	public void actionPerformed (ActionEvent e){
		Graphics g = getGraphics();
		if (buscar == e.getSource() ){
			JFileChooser fc=new JFileChooser();
			int r=fc.showOpenDialog(null);
			if(r==JFileChooser.APPROVE_OPTION){
				try {
					imagen=ImageIO.read(fc.getSelectedFile().toURL());

					try {
						tracker.addImage(imagen, 1);
						tracker.waitForID(1);
					} catch (Exception e1) {
						System.out.println("Error imagen");
					}
					g.drawImage(imagen, 40,45,320,298, this);
				} catch (MalformedURLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				} catch (IOException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		}
		if (guardar == e.getSource()){
			int ax = JOptionPane.showConfirmDialog(null, "Guardar imagen?");
			if(ax == JOptionPane.YES_OPTION){

				try{
					File entra = new File("Base de datos.xls");
					Workbook original = Workbook.getWorkbook(entra);
					WritableWorkbook copia = Workbook.createWorkbook(new File("Temp.xls"), original);
					WritableSheet sheet = copia.getSheet(0);

					Cell fil = sheet.getCell(8,0);
					String sfila = fil.getContents();
					int fila = Integer.parseInt(sfila);

					String direccion = ("D:\\Imagenes casas\\casa00"+fila+".png");
					try{

						File f = new File(direccion);
						ImageIO.write ((RenderedImage) imagen,"png",f);
					}catch(Exception e2){
						System.out.println("Error de la imgaen");
					}
					sheet.addCell(new jxl.write.Number(14, fila, fila));

					copia.write();
					copia.close();
					original.close();
					//Se guardan cambios en copia y se guardan los cambios en original
					File file = new File("Temp.xls");
					Workbook Copia = Workbook.getWorkbook(file);
					WritableWorkbook Original = Workbook.createWorkbook(new File("Base de datos.xls"), Copia);

					Original.write();
					Original.close();
				}
				catch (IOException ex)
				{
					System.out.println("error la base de datos esta abierta");
				}
				catch (WriteException ex)
				{
					System.out.println("error 202");
				} catch (BiffException ex) {
					ex.printStackTrace();
				}
				dispose();
				JOptionPane.showMessageDialog(null, "Se guardo la imagen exitosamente");
			}
			else if(ax == JOptionPane.NO_OPTION){

			}

		}
	}
}