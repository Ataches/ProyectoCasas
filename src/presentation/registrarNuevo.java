package presentation;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.io.*;
import jxl.*;
import jxl.read.biff.BiffException;
import jxl.write.*;

@SuppressWarnings("serial")
public class registrarNuevo extends JFrame implements ActionListener{
	String nombre, descp, ciudad, tipo, clase, tel, estado, deuda, barrio,nombreP;
	int habs, precio, metros, numberBathroom, antiguedad;
	int fila;

	JButton guardar = new JButton ("Guardar");
	JButton nuevo = new JButton ("Nuevo");
	JButton subir = new JButton ("Subir imagen");

	JLabel lregistro = new JLabel();
	JLabel lprecio = new JLabel();
	JLabel lhabs = new JLabel();
	JLabel ldescp = new JLabel();
	JLabel lnumberBathroom = new JLabel();
	JLabel ltipo = new JLabel();
	JLabel lciudad = new JLabel();
	JLabel lnombre = new JLabel();
	JLabel lclase = new JLabel();
	JLabel lestado = new JLabel();
	JLabel ldeuda = new JLabel();
	JLabel lbarrio = new JLabel();
	JLabel lmetros = new JLabel();
	JLabel lantiguedad = new JLabel();
	JLabel lnombreP = new JLabel();
	JLabel ltel = new JLabel();

	JTextField tprecio = new JTextField(30);
	JTextField tciudad = new JTextField(30);
	JTextField tnombre = new JTextField(30);
	JTextField tclase = new JTextField(30);
	JTextArea tdescp = new JTextArea(2, 30);
	JTextField testado = new JTextField(30);
	JTextField tnumberBathroom = new JTextField(30);
	JTextField thabs = new JTextField(30);
	JTextField ttipo = new JTextField(30);
	JTextField tdeuda = new JTextField(30);
	JTextField tbarrio = new JTextField(30);
	JTextField tmetros = new JTextField(30);
	JTextField tantiguedad = new JTextField(30);
	JTextField tnombreP = new JTextField(30);
	JTextField ttel = new JTextField(30);

	JComboBox<String> listaCiudades = new JComboBox<String>();
	JComboBox<String> listaTipo = new JComboBox<String>();
	JComboBox<String> listaClase = new JComboBox<String>();
	JComboBox<String> listaEstado = new JComboBox<String>();

	MediaTracker tracker;
	Toolkit tk; 

	void Main(int fila) {
		registrarNuevo r = new registrarNuevo(fila);
		r.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		r.setSize(950,750);
		r.setVisible(true);
	}

	registrarNuevo(int fila) {
		super("Resultado de la busqueda");
		this.fila = fila;

		Container c = getContentPane();

		c.add(lregistro);
		lregistro.setBounds(40, 150, 150, 23);
		c.setLayout(null);
		if (fila == 0){
			lregistro.setText("Registrar nuevo:");
		}else{

			lregistro.setText("Modificar registro: ");
		}
		c.add(lprecio);
		lprecio.setText("precio: ");
		lprecio.setBounds(530, 175, 100, 23);
		c.add(tprecio);
		tprecio.setBounds(600, 175, 100, 23);

		c.add(ldeuda);
		ldeuda.setText("deuda: ");
		ldeuda.setBounds(530, 205, 100, 23);
		c.add(tdeuda);
		tdeuda.setBounds(600, 205, 100, 23);

		c.add(lbarrio);
		lbarrio.setText("barrio: ");
		lbarrio.setBounds(530, 355, 100, 23);
		c.add(tbarrio);
		tbarrio.setBounds(600, 355, 50, 23);

		c.add(lmetros);
		lmetros.setText("metros: ");
		lmetros.setBounds(530, 380, 100, 23);
		c.add(tmetros);
		tmetros.setBounds(600, 380, 30, 23);

		c.add(lantiguedad);
		lantiguedad.setText("antiguedad: ");
		lantiguedad.setBounds(530, 405, 100, 23);
		c.add(tantiguedad);
		tantiguedad.setBounds(600, 405, 20, 23);

		c.add(lnumberBathroom);
		lnumberBathroom.setText("numberBathroom: ");
		lnumberBathroom.setBounds(530, 430, 100, 23);
		c.add(tnumberBathroom);
		tnumberBathroom.setBounds(600, 430, 20, 23);


		c.add(lnombre);
		lnombre.setText("Nombre: ");
		lnombre.setBounds(40, 175, 100, 23);
		c.add(tnombre);
		tnombre.setBounds(120, 175, 400, 23);

		c.add(ldescp);
		ldescp.setText("Descripción: ");
		ldescp.setBounds(40, 205, 80, 23);
		c.add(tdescp);
		tdescp.setBounds(120, 205, 400, 112);

		c.add(lnombreP);
		lnombreP.setText("Nombre contacto: ");
		lnombreP.setBounds(530, 455, 150, 23);
		c.add(tnombreP);
		tnombreP.setBounds(640, 455, 200, 23);

		c.add(ltel);
		ltel.setText("Numero: ");
		ltel.setBounds(40, 455, 80, 23);
		c.add(ttel);
		ttel.setBounds(120, 455, 200, 23);

		c.add(lciudad);
		lciudad.setText("Ciudad: ");
		lciudad.setBounds(40, 325, 50, 23);
		c.add(listaCiudades);
		listaCiudades.setBounds(120,325,80,20);
		listaCiudades.addItem("¿Ciudad?");
		listaCiudades.addItem("Bogotá");
		listaCiudades.addItem("Medellin");
		listaCiudades.addItem("Cartagena");
		listaCiudades.addItem("Barrinquilla");
		listaCiudades.addItem("Cali");
		listaCiudades.addItem("Tolima");
		listaCiudades.addItem("Bucaramanga");

		c.add(lclase);
		lclase.setText("Clase");
		lclase.setBounds(40, 355, 150, 23);
		c.add(listaClase);
		listaClase.setBounds(120, 355,115,20);
		listaClase.addItem("¿Que buscas?");
		listaClase.addItem("Casa");
		listaClase.addItem("Apartamento");
		listaClase.addItem("Local");	

		c.add(lestado);
		lestado.setText("Estado: ");
		lestado.setBounds(40,380, 150, 23);
		c.add(listaEstado);	
		listaEstado.setBounds(120, 380, 125,20);
		listaEstado.addItem("¿Nuevo o usado?");
		listaEstado.addItem("Nuevo");
		listaEstado.addItem("Usado");

		c.add(ltipo);
		ltipo.setText("Tipo: ");
		ltipo.setBounds(40, 405, 150, 23);
		c.add(listaTipo);
		listaTipo.setBounds(120, 405,150,20);
		listaTipo.addItem("¿En venta o arriendo?");
		listaTipo.addItem("Venta");
		listaTipo.addItem("Arriendo");
		listaTipo.addItem("Permuta");
		listaTipo.addItem("Leasing");	

		c.add(lhabs);
		lhabs.setText("Habitaciones:");
		lhabs.setBounds(40, 430, 150, 23);
		c.add(thabs);
		thabs.setBounds(120, 430, 30, 23);

		c.add(guardar);
		guardar.setBounds(230, 500, 100, 23);
		c.add(nuevo);
		nuevo.setBounds(350, 500, 100, 23);
		c.add(subir);
		subir.setBounds(470, 500, 150, 23);

		subir.addActionListener(this);
		guardar.addActionListener(this);
		nuevo.addActionListener(this);
		tracker = new MediaTracker(this);
	}
	int nFila;
	public void guardarExcel(int fila){

		this.fila = fila;

		nombre = tnombre.getText(); 
		String sprecio= tprecio.getText();
		precio = Integer.parseInt(sprecio);
		descp = tdescp.getText(); 
		ciudad = (String)listaCiudades.getSelectedItem();
		tipo =(String)listaTipo.getSelectedItem();
		clase = (String)listaClase.getSelectedItem();
		estado =(String)listaEstado.getSelectedItem();
		String shabs= thabs.getText();
		habs = Integer.parseInt(shabs);
		deuda = tdeuda.getText(); 
		barrio = tbarrio.getText(); 
		String smetros= tmetros.getText();
		metros = Integer.parseInt(smetros);
		String santiguedad= tantiguedad.getText();
		antiguedad = Integer.parseInt(santiguedad);
		String snumberBathroom= tnumberBathroom.getText();
		numberBathroom = Integer.parseInt(snumberBathroom);
		nombreP = tnombreP.getText();
		tel= ttel.getText();

		try{
			// Se abre original y se copia la info a la copia; se modifica la copia.
			File entra = new File("Base de datos.xls");
			Workbook original = Workbook.getWorkbook(entra);
			WritableWorkbook copia = Workbook.createWorkbook(new File("Temp.xls"), original);
			WritableSheet sheet = copia.getSheet(0);

			if (fila == 0){
				Cell fil = sheet.getCell(8,0);

				String sfila = fil.getContents();
				fila = Integer.parseInt(sfila);
				nFila = fila;
			}else{
				nFila = this.fila;
			}
			sheet.addCell(new jxl.write.Number(0, nFila, nFila));
			sheet.addCell(new jxl.write.Label(1, nFila, nombre));
			sheet.addCell(new jxl.write.Label(2, nFila, descp));
			sheet.addCell(new jxl.write.Label(3, nFila, ciudad));
			sheet.addCell(new jxl.write.Label(4, nFila, clase));
			sheet.addCell(new jxl.write.Label(5, nFila, estado));
			sheet.addCell(new jxl.write.Label(6, nFila, tipo));
			sheet.addCell(new jxl.write.Number(7, nFila, habs));
			sheet.addCell(new jxl.write.Number(8, nFila, precio));
			sheet.addCell(new jxl.write.Label(9, nFila, deuda));
			sheet.addCell(new jxl.write.Number(10, nFila, metros));
			sheet.addCell(new jxl.write.Label(11, nFila, barrio));
			sheet.addCell(new jxl.write.Number(12, nFila, numberBathroom));
			sheet.addCell(new jxl.write.Number(13, nFila, antiguedad));
			sheet.addCell(new jxl.write.Label(15, nFila, nombreP));
			sheet.addCell(new jxl.write.Label(16, nFila, tel));
			nFila+=1;
			sheet.addCell(new jxl.write.Number(8, 0, (int) nFila));

			copia.write();
			copia.close();
			original.close();
			//Se guardan cambios en copia y se guardan los cambios en original
			File file = new File("Temp.xls");
			Workbook Copia = Workbook.getWorkbook(file);
			WritableWorkbook Original = Workbook.createWorkbook(new File("Base de datos.xls"), Copia);

			Original.write();
			Original.close();

			System.out.println("Se guardo con exito");
		}
		catch (IOException ex)
		{
			System.out.println("error la base de datos esta abierta");
		}
		catch (WriteException ex)
		{
			System.out.println("error 202");
		} catch (BiffException e) {
			e.printStackTrace();
		}

	}
	public void paint(Graphics g){
		super.paint(g);
	}
	@SuppressWarnings("static-access")
	public void actionPerformed(ActionEvent e) {
		if (guardar == e.getSource()){
			guardarExcel(fila);
		}
		if (subir == e.getSource()){
			Principal subir = new Principal();
			subir.Main();
		}
	}


}
