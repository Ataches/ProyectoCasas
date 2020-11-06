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

@SuppressWarnings("serial")
public class modificar extends JFrame implements ActionListener{
	String descp="", ciudad="", tipo="", clase="", estado="",deuda="";
	int habs=-1, metros=-1, baños=-1, antiguedad=-1, mtdesde=-1, mthasta=-1, prdesde=-1, prhasta=-1;

	JButton guardar = new JButton ("Guardar");

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
	JLabel lbaños = new JLabel();
	JLabel lestado = new JLabel();

	JButton modificar = new JButton("Modificar");
	JButton siguiente = new JButton("Siguiente");

	MediaTracker tracker;
	Toolkit tk; 
	void Main() {
		modificar o = new modificar();
		o.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		o.setSize(1100,500);
		o.setVisible(true);
	}
	modificar(){
		Container c = getContentPane();
		c.setLayout(null);

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
		
		c.add(modificar);
		modificar.setBounds(770,70,120,30);
		c.add(siguiente);
		siguiente.setBounds(40,30,100,30);

		modificar.addActionListener (this);	
		siguiente.addActionListener (this);	
		tracker = new MediaTracker(this);

		mostrar(1);
	}
	int pint, posy=120;
	void mostrar(int i){
		try{
			File entra = new File("Base de datos.xls");
			Workbook original = Workbook.getWorkbook(entra);
			WritableWorkbook copia = Workbook.createWorkbook(new File("Busq.xls"), original);
			WritableSheet sheet = copia.getSheet(0);
			for (int j = 1; j<=14; j++){
				Cell dato = sheet.getCell(j,i);
				String sdato = dato.getContents();
				System.out.println(" ---dato "+sdato+"\n\n");
				int a = -30;
				switch (j)
				{
				case 1://Titulo	
					lnombre.setText(sdato);
					lnombre.setBounds(320+a, 110, 300, 23);
					break;
				case 2://Descripcion	
					ldescp.setText(sdato);
					ldescp.setBounds(320+a, 145, 500, 50);
					break;
				case 3://Ciudad	
					lciudad.setText(sdato);
					lciudad.setBounds(320+a, 125, 70, 23);
					break;
				case 4://Clase (casa)	
					lclase.setText(sdato);
					lclase.setBounds(420+a, 190, 200, 23);
					break;	
				case 6://Tipo (venta)	
					ltipo.setText(sdato+" de: ");
					ltipo.setBounds(320+a, 190, 120, 23);
					break;
				case 7://habs	
					lhabs.setText("Habitaciones: "+sdato);
					lhabs.setBounds(800+a, 130, 150, 23);
					break;
				case 8://precio	
					lprecio.setText("$"+sdato);
					lprecio.setBounds(800+a, 110, 100, 23);
					break;
				case 9://deuda	
					ldeuda.setText("Deuda: "+sdato);
					ldeuda.setBounds(800+a, 150, 150, 23);
					break;
				case 10://metros	
					lmetros.setText(sdato+"mt");
					lmetros.setBounds(800+a, 170, 150, 23);
					break;
				case 11://Barrio	
					lbarrio.setText(sdato);
					lbarrio.setBounds(410+a, 125, 70, 23);
					break;
				case 14://imagen
					if((sdato.equals(""))){pint=0;posy=0;}else{
						pint=Integer.parseInt(sdato);
						posy=120;
						repaint();
					}
					break;
				}
			}
		}
		catch (IOException ex)
		{
			System.out.println("error la base de datos esta abierta");
		} catch (BiffException e) {
			e.printStackTrace();
		}

	}
	Image imagen;
	public void paint(Graphics g){
		super.paint(g);
		tk = Toolkit.getDefaultToolkit();
		if(pint!=0){
			String ubicacion = String.format("/icasas/casa00"+pint+".jpg");
			imagen = tk.getImage(getClass().getResource(ubicacion));
			try {
				tracker.addImage(imagen, 1);
				tracker.waitForID(1);
			} catch (Exception e1) {
				System.out.println("Error imagen");
			}
			g.drawImage(imagen, 10,posy,100,100,this);	
		}

	}
	int i=1;
	public void actionPerformed(ActionEvent e) {
		if (siguiente == e.getSource()){
			i++;
			Graphics g = getGraphics();
			g.clearRect(0,0,1000,1000);
			mostrar(i);			
		}
		if ( modificar == e.getSource()){
			registrarNuevo reg = new registrarNuevo(i);
			reg.Main(i);
			dispose();
		}
	}
}