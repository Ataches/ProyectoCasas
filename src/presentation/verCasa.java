package presentation;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import jxl.Cell;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

@SuppressWarnings("serial")
public class verCasa extends JFrame implements ActionListener  {

	MediaTracker tracker;
	Toolkit tk; 

	JLabel lfiltro = new JLabel();
	JLabel lbusq = new JLabel();
	JLabel lbusq2 = new JLabel();
	JLabel ldesde = new JLabel();
	JLabel lhasta = new JLabel();
	JLabel lnombre = new JLabel();
	JLabel ldescp = new JLabel();
	JLabel lciudad = new JLabel();
	JLabel lbarrio = new JLabel();
	JLabel lclase = new JLabel();
	JLabel ltipo = new JLabel();
	JLabel lhabs = new JLabel();
	JLabel lmetros = new JLabel();
	JLabel ldeuda = new JLabel();
	JLabel lprecio = new JLabel();
	JLabel lnumberBathroom = new JLabel();
	JLabel lestado = new JLabel();
	JLabel lantiguedad = new JLabel();
	JLabel lnombreP = new JLabel();
	JLabel ltel = new JLabel();
	
	JButton guardar = new JButton("Guardar numero");

	int boton, numero;
	String descp, nombre, where;

	public void Main(){
		verCasa p=new verCasa(boton, descp);
		p.setSize(800, 600);
		p.setVisible(true);
	}

	public verCasa(int i, String descp){
		super("Ver oferta");

		this.descp = descp;
		boton = i;

		Container c = getContentPane();
		c.setLayout(null);

		c.add(guardar);
		guardar.setBounds(230, 460, 200, 23);

		c.add(lnombre);
		c.add(ldescp);	
		c.add(lciudad);
		c.add(lbarrio);
		c.add(lclase);
		c.add(ltipo);
		c.add(lhabs);
		c.add(lmetros);
		c.add(ldeuda);
		c.add(lprecio);
		c.add(lantiguedad);
		c.add(lnumberBathroom);
		c.add(lestado);
		c.add(lnombreP);
		c.add(ltel);

		guardar.addActionListener(this);
		tracker= new MediaTracker(this);
		mostrar();
	}

	int pintar;

	public void mostrar(){
		System.out.println("boton no. "+boton);
		System.out.println("en busca "+descp);
		try{
			File entra = new File("Base de datos.xls");
			Workbook original = Workbook.getWorkbook(entra);
			WritableWorkbook copia = Workbook.createWorkbook(new File("Busq.xls"), original);
			WritableSheet sheet = copia.getSheet(0);

			//			Sheet sheet = original.getSheet(0);

			Cell fil = sheet.getCell(8,0);
			String sfila = fil.getContents();
			int fila = Integer.parseInt(sfila);

			String[][] sugeridos= new String [fila][3];
			int[][] numSg = new int [fila][3];
			fila--;
			LevenshteinDistance leven = new LevenshteinDistance ();

			for (int i = 1; i <= fila; i++){
				Cell titulo = sheet.getCell(1,i);
				String sugerido = titulo.getContents();

				numSg[i][0] = leven.computeLevenshteinDistance(sugerido, descp);
				sugeridos[i][1] = sugerido;
				numSg[i][2] = i;
			}

			int aux;
			String saux;

			for(int i = 2; i < fila; i++)
			{

				for(int j = 1; j < fila-1; j++)
				{
					if (numSg[j][0] > numSg[j + 1][0])
					{
						aux = numSg[j][0];
						numSg[j][0] = numSg[j+1][0] ;
						numSg[j+1][0] = aux;

						saux = sugeridos[j][1];
						sugeridos[j][1] = sugeridos[j+1][1];
						sugeridos[j+1][1] = saux;	

						aux = numSg[j][2];
						numSg[j][2] = numSg[j+1][2] ;
						numSg[j+1][2] = aux;

					}
				}
			}
			//			for (int i=1 ; i<=fila;i++){
			//			System.out.println(" Leven "+numSg[i][0]);
			//			System.out.println(" sugerido "+sugeridos[i][1]);
			//			System.out.println(" numero lista "+numSg[i][2]);

			for (int j = 1; j<=16; j++){
				Cell dato = sheet.getCell(j,numSg[boton][2]);
				String sdato = dato.getContents();

				switch (j)
				{
				case 1://Titulo	
					lnombre.setText(sdato);
					lnombre.setBounds(440,115,300,24);
					break;
				case 2://Descripcion	
					ldescp.setText(sdato);
					ldescp.setBounds(440,160,550,40);
					break;
				case 3://Ciudad	
					lciudad.setText(sdato);
					lciudad.setBounds(440, 190, 70, 23);
					break;
				case 4://Clase (casa)	
					lclase.setText(sdato);
					lclase.setBounds(440, 210, 200, 23);
					break;	
				case 5://Estado (nuevo)	
					lestado.setText(sdato);
					lestado.setBounds(440, 230, 70, 23);
					break;	
				case 6://Tipo (venta)	
					ltipo.setText(sdato+" de: ");
					ltipo.setBounds(440, 250, 200, 23);
					break;
				case 7://habs	
					lhabs.setText("Habitaciones: "+sdato);
					lhabs.setBounds(440, 270, 150, 23);
					break;
				case 8://precio	
					lprecio.setText("$"+sdato);
					lprecio.setBounds(440, 140, 100, 23);
					break;
				case 9://deuda	
					ldeuda.setText("Deuda: "+sdato);
					ldeuda.setBounds(440, 290, 150, 23);
					System.out.println("***dato "+sdato);
					break;
				case 10://metros	
					lmetros.setText(sdato+"mt");
					lmetros.setBounds(440, 310, 150, 23);
					break;
				case 11://Barrio	
					lbarrio.setText(sdato);
					lbarrio.setBounds(490, 190, 70, 23);
					break;
				case 12://numberBathroom	
					lnumberBathroom.setText("numberBathroom: "+sdato);
					lnumberBathroom.setBounds(440, 330, 70, 23);
					break;	
				case 13://antiguedad
					lantiguedad.setText("Antiguedad: "+sdato);
					lantiguedad.setBounds(440, 350, 200, 23);
					break;
				case 14://Imagen
					pintar = Integer.parseInt(sdato);
					break;	
				case 15://Nombre
					lnombreP.setText("Nombre de contacto: "+sdato);
					lnombreP.setBounds(100, 420, 300, 23);
					break;	
				case 16://telefono
					ltel.setText("Numero de telefono: "+sdato);
					ltel.setBounds(400, 420, 200, 23);
					break;	
					

				}

	}
}
catch (IOException ex)
{
	System.out.println("error");
}
catch (BiffException e) {
	e.printStackTrace();
}	
}
public void paint(Graphics g){
	super.paint(g);
	tk = Toolkit.getDefaultToolkit();
	String ubicacion = String.format("/icasas/casa00"+pintar+".jpg");
	Image imagen = tk.getImage(getClass().getResource(ubicacion));
	try {
		tracker.addImage(imagen, 1);
		tracker.waitForID(1);
	} catch (Exception e1) {
		System.out.println("Error imagen");
	}
	g.drawImage(imagen, 100,100,320,298,this);		
}

public void actionPerformed(ActionEvent e) {
	if (guardar == e.getSource()){
		try{
			File entra = new File("Agenda.xls");
			Workbook original = Workbook.getWorkbook(entra);
			WritableWorkbook copia = Workbook.createWorkbook(new File("copiaAg.xls"), original);
			WritableSheet sheet = copia.getSheet(0);

			Cell fil = sheet.getCell(8,0);

			String sfila = fil.getContents();
			int fila = Integer.parseInt(sfila);

			sheet.addCell(new jxl.write.Label(1, fila, nombre));
			sheet.addCell(new jxl.write.Number(2, fila, numero));
			fila++;
			sheet.addCell(new jxl.write.Number(8, 0, (int) fila));

			copia.write();
			copia.close();
			original.close();
			File file = new File("copiaAg.xls");
			Workbook Copia = Workbook.getWorkbook(file);
			WritableWorkbook Original = Workbook.createWorkbook(new File("Agenda.xls"), Copia);

			Original.write();
			Original.close();
		}
		catch (IOException ex)
		{
			System.out.println("error");
		}
		catch (BiffException e1) {
			e1.printStackTrace();
		} catch (WriteException e1) {
			e1.printStackTrace();
		}	
	}
}
}
