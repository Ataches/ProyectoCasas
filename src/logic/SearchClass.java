package logic;

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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import jxl.Cell;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import presentation.LevenshteinDistance;
import presentation.verCasa;

@SuppressWarnings("serial")
public class SearchClass extends JFrame implements ActionListener{
	String descp="", ciudad="", tipo="", clase="", estado="",deuda="";
	int habs=-1, metros=-1, numberBathroom=-1, antiguedad=-1, mtdesde=-1, mthasta=-1, prdesde=-1, prhasta=-1;

	JButton guardar = new JButton ("Guardar");
	JButton[] ver = new JButton[11];
	JButton buscar = new JButton("Buscar");
	JButton filtrar = new JButton("Filtrar");

	JTextField tbusq = new JTextField(70);
	JTextField tdesde = new JTextField(20);
	JTextField thasta = new JTextField(20);
	JTextField tnumberBathroom = new JTextField(20);
	JTextField tantiguedad = new JTextField(20);
	JTextField thabs = new JTextField(20);
	JTextField tdeuda = new JTextField(20);
	JTextField tmdesde = new JTextField(20);
	JTextField tmhasta = new JTextField(20);

	JComboBox<String> listaCiudades = new JComboBox<String>();
	JComboBox<String> listaTipo = new JComboBox<String>();
	JComboBox<String> listaClase = new JComboBox<String>();
	JComboBox<String> listaEstado = new JComboBox<String>();

	JLabel lfiltro = new JLabel();
	JLabel lbusq = new JLabel();
	JLabel lbusq2 = new JLabel();
	JLabel ldesde = new JLabel();
	JLabel lhasta = new JLabel();
	JLabel lnombre[] = new JLabel[10];
	JLabel ldescp[] = new JLabel[10];
	JLabel lciudad[] = new JLabel[11];
	JLabel lbarrio[] = new JLabel[11];
	JLabel lclase[] = new JLabel[11];
	JLabel ltipo[] = new JLabel[11];
	JLabel lhabs[] = new JLabel[11];
	JLabel lmetros[] = new JLabel[11];
	JLabel ldeuda[] = new JLabel[11];
	JLabel lprecio[] = new JLabel[11];
	JLabel lnumberBathroom = new JLabel();
	JLabel lestado = new JLabel();

	MediaTracker tracker;
	Toolkit tk; 

	public void Main() {
		SearchClass o = new SearchClass(descp, ciudad, tipo, clase, estado);
		o.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		o.setSize(1100,900);
		o.setVisible(true);
	}

	public SearchClass(String descp, String ciudad, String tipo, String clase, String estado) {

		super("Resultado de la busqueda");

		this.descp = descp;
		this.ciudad = ciudad;
		this.tipo = tipo;
		this.clase = clase;
		this.estado = estado;

		int espaciado=155;

		Container c = getContentPane();
		c.setLayout(null);

		//-------------------COMBO BOX----------------

		lciudad[0] = new JLabel();
		c.add(lciudad[0]);
		lciudad[0].setText("Ciudad:");
		lciudad[0].setBounds(50,140,100,20);

		c.add(listaCiudades);
		listaCiudades.setBounds(50,160,80,20);
		listaCiudades.addItem("Bogotá");
		listaCiudades.addItem("Medellín");
		listaCiudades.addItem("Cartagena");
		listaCiudades.addItem("Barrinquilla");
		listaCiudades.addItem("Cali");
		listaCiudades.addItem("Tolima");
		listaCiudades.addItem("Bucaramanga");

		lciudad[0] = new JLabel();
		c.add(lciudad[0]);
		lciudad[0].setText("Que Buscas?");
		lciudad[0].setBounds(50,180,100,23);

		c.add(listaClase);
		listaClase.setBounds(50,200,115,20);
		listaClase.addItem("Casa");
		listaClase.addItem("Apartamento");
		listaClase.addItem("Local");	

		ltipo[0] = new JLabel();
		c.add(ltipo[0]);
		ltipo[0].setText("Tipo: ");
		ltipo[0].setBounds(50,285,100,23);

		c.add(listaTipo);
		listaTipo.setBounds(50, 305,150,20);
		listaTipo.addItem("Venta");
		listaTipo.addItem("Arriendo");
		listaTipo.addItem("Permuta");
		listaTipo.addItem("Leasing");	

		c.add(lestado);
		lestado.setText("Estado:");
		lestado.setBounds(50,325,100,23);

		c.add(listaEstado);	
		listaEstado.setBounds(50,345, 125,20);
		listaEstado.addItem("Nuevo");
		listaEstado.addItem("Usado");

		//-------------------COMBO BOX----------------

		c.add(lfiltro);
		lfiltro.setText("Filtrar busqueda");
		lfiltro.setBounds(50, 110, 150, 23);

		c.add(lbusq);
		lbusq.setText("Buscar nuevo:");
		lbusq.setBounds(350, 50, 150, 23);
		c.add(tbusq);
		tbusq.setBounds(350, 70, 400, 23);

		lprecio[0] = new JLabel();
		c.add(lprecio[0]);
		lprecio[0].setText("Precio");
		lprecio[0].setBounds(50, 220, 100, 23);
		c.add(tdesde);
		tdesde.setBounds(50, 240, 100, 23);
		c.add(thasta);
		thasta.setBounds(50, 265, 100, 23);

		lhabs[0] = new JLabel();
		c.add(lhabs[0]);
		lhabs[0].setText("habs");
		lhabs[0].setBounds(50, 210+espaciado, 100, 23);
		c.add(thabs);
		thabs.setBounds(50, 230+espaciado, 100, 23);

		ldeuda[0] = new JLabel();
		c.add(ldeuda[0]);
		ldeuda[0].setText("deuda (si/no)");
		ldeuda[0].setBounds(50, 250+espaciado, 100, 23);
		c.add(tdeuda);
		tdeuda.setBounds(50, 270+espaciado, 100, 23);

		c.add(lnumberBathroom);
		lnumberBathroom.setText("numberBathroom");
		lnumberBathroom.setBounds(50, 320+espaciado, 100, 23);
		c.add(tnumberBathroom);
		tnumberBathroom.setBounds(50, 340+espaciado, 100, 23);

		lmetros[0] = new JLabel();
		c.add(lmetros[0]);
		lmetros[0].setText("metros");
		lmetros[0].setBounds(50, 365+espaciado, 150, 23);
		c.add(tmdesde);
		tmdesde.setBounds(50, 390+espaciado, 100, 23);
		c.add(tmhasta);
		tmhasta.setBounds(50, 415+espaciado, 100, 23);		

		for (int i = 1; i<=5; i++){
			lnombre[i] = new JLabel();
			c.add(lnombre[i]);
			ldescp[i] = new JLabel();
			c.add(ldescp[i]);
			lciudad[i] = new JLabel();			
			c.add(lciudad[i]);
			lbarrio[i] = new JLabel();
			c.add(lbarrio[i]);
			lclase[i] = new JLabel();
			c.add(lclase[i]);
			ltipo[i] = new JLabel();
			c.add(ltipo[i]);
			lhabs[i] = new JLabel();
			c.add(lhabs[i]);
			lmetros[i] = new JLabel();
			c.add(lmetros[i]);
			ldeuda[i] = new JLabel();
			c.add(ldeuda[i]);
			lprecio[i] = new JLabel();
			c.add(lprecio[i]);
			ver[i] = new JButton("Ver");
			c.add(ver[i]);
			ver[i].setEnabled(false);
			ver[i].addActionListener(this);
		}
		c.add(buscar);
		buscar.setBounds(770,70,120,30);
		c.add(filtrar);
		filtrar.setBounds(40,600,100,30);

		buscar.addActionListener (this);	
		filtrar.addActionListener (this);	
		tracker = new MediaTracker(this);

		resultado(descp, ciudad, tipo, clase, estado, prdesde, prhasta, 
				habs, antiguedad, mtdesde, mthasta, numberBathroom, deuda);
	}
	int boton[] = new int[99];
	int[] numeros = new int [99];
	int cont=0, fil4, nciclo,indice = 0;
	int[] pint = new int[7], posy = new int[7];
	int posx=320;
	String[][] Gsugeridos= new String [99][3];
	int[][] GnumSg = new int [99][3];
	boolean filtro=false;

	public void 
	resultado(String descp, String Ciudad, String Tipo, 
			String Clase, String Estado, int psdesde, int pshasta,
			int habts, int antg, int mtsdesde, int mtshasta, int numberBathroom, String Deuda){

		try{
			File entra = new File("Base de datos.xls");
			Workbook original = Workbook.getWorkbook(entra);
			WritableWorkbook copia = Workbook.createWorkbook(new File("Busq.xls"), original);
			WritableSheet sheet = copia.getSheet(0);

			Cell fil = sheet.getCell(8,0);
			String sfila = fil.getContents();
			int fila = Integer.parseInt(sfila);



			String[][] sugeridos= new String [fila][3];
			int[][] numSg = new int [fila][3];
			fila--;
			fil4=fila;
			int[] nobuscar = new int[99];
			if(filtro = true){
				for (int j=1; j<=fila;j++){
					for(int i=3; i<=13;i++){
						Cell dato = sheet.getCell(i,j);
						String sdato = dato.getContents();

						System.out.println("dato: "+sdato+"");
						if(!sdato.equals("")){
							switch (i)
							{
							case 3://Ciudad	
								if ((!Ciudad.equals("Ciudad?"))){
									if (!ciudad.equals(Ciudad)){
										System.out.println("sdato: "+sdato+" ciclo: "+j+" ciudad");
										nobuscar[j]=1;
									}
								}
								break;
							case 4://Clase (casa)	
								if ((Clase.equals("Que buscas?"))){}else{
									if ((Clase.equals(clase))){}else{
										nobuscar[j]=1;

										System.out.println("sdato: "+sdato+" ciclo: "+j+" casa");
									}
								}
								break;	
							case 5://Estado (nuevo)	
								if ((Estado.equals("Nuevo o usado?"))){}else{
									if (Estado.equals(sdato)){}else{
										nobuscar[j]=1;

										System.out.println("sdato: "+sdato+" ciclo: "+j+" estado");
									}
								}
								break;	
							case 6://Tipo (venta)	
								if (!Tipo.equals("En venta o arriendo?")){
									if (!Tipo.equals(sdato)){
										nobuscar[j]=1;

										System.out.println("sdato: "+sdato+" ciclo: "+j+" tipo");
									}
								}
								break;
							case 7://habs	
								int ndato = Integer.parseInt(sdato);
								if (habs != -1){
									if (ndato != habts){
										nobuscar[j]=1;
										System.out.println("sdato: "+ndato+" ciclo: "+j+ " habs");
									}
								}
								break;
							case 8://precio	
								int precio = Integer.parseInt(sdato);
								if ((psdesde != -1) || (pshasta != -1)){
									if ((psdesde <= precio) || (precio >= pshasta)){
										nobuscar[j]=1;

										System.out.println("sdato: "+precio+" ciclo: "+j+" precio");
									}
								}
								break;
							case 9://deuda	
								if(!deuda.equals("")){
									if (!sdato.equals(deuda)){
										nobuscar[j]=1;
										System.out.println("sdato: "+sdato+" ciclo: "+j+" deuda");
									}
								}
								break;
							case 10://metros	
								int metro = Integer.parseInt(sdato);
								if ((mtdesde != -1) || (mthasta != -1)){
									if ((psdesde <= metro) || (metro >= pshasta)){
										nobuscar[j]=1;

										System.out.println("sdato: "+metro+" ciclo: "+j+" metros");
									}
								}
								break;
							case 12://numberBathroom	
								if ((numberBathroom != -1)){
									if (numberBathroom!=Integer.parseInt(sdato)){
										nobuscar[j]=1;

										System.out.println("sdato: "+sdato+" ciclo: "+j+" numberBathroom");
									}
								}
								break;	
							case 13://antiguedad
								if ((antiguedad != -1)){
									if (Integer.parseInt(sdato)!=antg){
										nobuscar[j]=1;
										System.out.println("sdato: "+sdato+" ciclo: "+j+" antiguedad");
									}
								}
								break;
							}
						}
					}
				}
			}
			LevenshteinDistance leven = new LevenshteinDistance ();

			for (int i = 1; i <= fila; i++){
				Cell titulo = sheet.getCell(1,i);
				String sugerido = titulo.getContents();
				if(nobuscar[i] != 1){
					numSg[i][0] = leven.computeLevenshteinDistance(sugerido, descp);
					sugeridos[i][1] = sugerido;
					numSg[i][2] = i;
				}else{
					numSg[i][0] = 999999;
					sugeridos[i][1] = "no buscar imagen oooooooooooooooooooooooooooooo";
					numSg[i][2] = 9999999;
				}
			}

			int aux;
			String saux;

			for(int i = 2; i < fila; i++)
			{

				for(int j = 1; j < fila; j++)
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

			for (int i=1 ; i<=fila;i++){
				System.out.println(" Leven "+numSg[i][0]);
				System.out.println(" sugerido "+sugeridos[i][1]);
				System.out.println(" numero lista "+numSg[i][2]);
			}

			int cont=0, cont2=0, falso;
			for(int i = 1; i<=5; i++){
				cont++;
				indice++;
				if (cont<=5){
					if(sugeridos[i][1].equals("no buscar imagen oooooooooooooooooooooooooooooo")){
						falso= 0;
					}else{
						lnombre[i].setText(sugeridos[i][1]);
						lnombre[i].setBounds(330, 100+(i*(100)), 150, 23);
						ver[i].setEnabled(true);
						ver[i].setBounds(950,190+((i-1)*(120)),60,20);
						falso= 1;
					}
					if(cont2==0){
						boton(i);
					}
					for (int j = 1; j<=14; j++){
						Cell dato = sheet.getCell(j,numSg[i][2]);
						String sdato = dato.getContents();
						int a = 150;
						if(falso==1){
							switch (j)
							{
							case 1://Titulo	
								lnombre[i].setText(sdato);
								lnombre[i].setBounds(320+a, 110+((i-1)*(120)), 300, 23);
								System.out.println(" ---dato "+sdato+"\n\n");
								break;
							case 2://Descripcion	
								ldescp[i].setText(sdato);
								ldescp[i].setBounds(320+a, 145+((i-1)*(120)), 500, 50);
								break;
							case 3://Ciudad	
								lciudad[i].setText(sdato);
								lciudad[i].setBounds(320+a, 125+((i-1)*(120)), 70, 23);
								break;
							case 4://Clase (casa)	
								lclase[i].setText(sdato);
								lclase[i].setBounds(420+a, 190+((i-1)*(120)), 200, 23);
								break;	
							case 6://Tipo (venta)	
								ltipo[i].setText(sdato+" de: ");
								ltipo[i].setBounds(320+a, 190+((i-1)*(120)), 120, 23);
								break;
							case 7://habs	
								lhabs[i].setText("Habitaciones: "+sdato);
								lhabs[i].setBounds(800+a, 130+((i-1)*(120)), 150, 23);
								break;
							case 8://precio	
								lprecio[i].setText("$"+sdato);
								lprecio[i].setBounds(800+a, 110+((i-1)*(120)), 100, 23);
								break;
							case 9://deuda	
								ldeuda[i].setText("Deuda: "+sdato);
								ldeuda[i].setBounds(800+a, 150+((i-1)*(120)), 150, 23);
								break;
							case 10://metros	
								lmetros[i].setText(sdato+"mt");
								lmetros[i].setBounds(800+a, 170+((i-1)*(120)), 150, 23);
								break;
							case 11://Barrio	
								lbarrio[i].setText(sdato);
								lbarrio[i].setBounds(410+a, 125+((i-1)*(120)), 70, 23);
								break;
							case 14://imagen
								if((cont>5)||(sdato.equals(""))){pint[i]=0;posy[i]=0;}else{
									pint[i]=Integer.parseInt(sdato);
									posy[i]=120+((i-1)*(120));
								}
								break;
							}
						}
					}
				}
			}

			for (int i = 0; i<=fil4;i++){

				GnumSg[i][0] = numSg[i][0];
				Gsugeridos[i][1] = sugeridos[i][1];
				GnumSg[i][2] = numSg[i][2];
			}

		}
		catch (IOException ex)
		{
			System.out.println("error");
		}
		catch (BiffException e) {
			e.printStackTrace();
		}
		repaint();
	}

	Image[] imagen = new Image[99];
	public void paint(Graphics g){
		super.paint(g);
		tk = Toolkit.getDefaultToolkit();
		for (int i=1; i<=5;i++ ){
			if(pint[i]!=0){
				String ubicacion = String.format("/icasas/casa00"+pint[i]+".jpg");
				imagen[i] = tk.getImage(getClass().getResource(ubicacion));
				try {
					tracker.addImage(imagen[i], 1);
					tracker.waitForID(1);
				} catch (Exception e1) {
					System.out.println("Error imagen");
				}
				g.drawImage(imagen[i], posx,posy[i],100,100,this);	
			}
		}

	}

	void boton(int ciclo){
		nciclo++;
	}
	public void actionPerformed(ActionEvent e) {
		for(int i=1;i<=5;i++){
			if (ver[i] == e.getSource()){
				verCasa vr = new verCasa(i, descp);
				vr.Main();
			}
		}
		if(buscar == e.getSource()){
			descp = tbusq.getText();
			resultado(descp, ciudad, tipo, clase, estado, prdesde, prhasta, 
					habs, antiguedad, mtdesde, mthasta, numberBathroom, deuda);
		}
		if(filtrar == e.getSource()){

			deuda= tdeuda.getText();

			String spdesd= tdesde.getText();
			if((spdesd).equals("")){
			}else{
				prdesde = Integer.parseInt(spdesd);
			}
			String sphast= thasta.getText();
			if((sphast.equals(""))){}else{
				prhasta = Integer.parseInt(sphast);
			}

			ciudad = (String)listaCiudades.getSelectedItem();
			tipo =(String)listaTipo.getSelectedItem();
			clase = (String)listaClase.getSelectedItem();
			estado =(String)listaEstado.getSelectedItem();

			String shabs= thabs.getText();
			if((shabs.equals(""))){}else{
				habs = Integer.parseInt(shabs);
			}

			String santiguedad= tantiguedad.getText();
			if((santiguedad.equals(""))){}else{
				antiguedad = Integer.parseInt(santiguedad);
			}

			String shastm= tmdesde.getText();
			if((shastm.equals(""))){}else{
				mtdesde = Integer.parseInt(shastm);
			}
			String sdesdm= tmhasta.getText();
			if((sdesdm.equals(""))){}else{
				mthasta = Integer.parseInt(sdesdm);}

			String snumberBathroom= tnumberBathroom.getText();
			if((snumberBathroom.equals(""))){}else{
				numberBathroom = Integer.parseInt(snumberBathroom);}
			Graphics g = getGraphics();
			g.clearRect(0,0,1000,1000);
			filtro = true;
			resultado(descp, ciudad, tipo, clase, estado, prdesde, prhasta, 
					habs, antiguedad, mtdesde, mthasta, numberBathroom, deuda);
		}
	}

}
