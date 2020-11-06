package presentation;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class home extends JFrame implements ActionListener{

	MediaTracker tracker;
	Toolkit tk; 

	JLabel lClase=new JLabel();
	JLabel lCiudad=new JLabel();
	JLabel lBusq=new JLabel();
	JLabel lnFoto=new JLabel();
	JLabel info=new JLabel();
	JLabel nombre=new JLabel();

	JButton modificar = new JButton ("Modificar");
	JButton agenda = new JButton ("Agenda");
	JButton enviar = new JButton ();
	JButton nRegistro = new JButton ("Nuevo registro");

	JTextField nBusq=new JTextField(30);

	JComboBox<String> listaCiudades = new JComboBox<String>();
	JComboBox<String> listaTipo = new JComboBox<String>();
	JComboBox<String> listaClase = new JComboBox<String>();
	JComboBox<String> listaEstado = new JComboBox<String>();

	public static void main(String[] args) {
		home o = new home();
		o.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		o.setSize(1000,600);
		o.setVisible(true);

	}
	String ubicacion;
	Image BBsimg;

	home(){

		super("Encuentra tu hogar");

		Container c = getContentPane();
		c.setLayout(null);

		c.add(listaCiudades);
		listaCiudades.setBounds(230,400,80,20);
		listaCiudades.addItem("¿Ciudad?");
		listaCiudades.addItem("Bogotá");
		listaCiudades.addItem("Medellin");
		listaCiudades.addItem("Cartagena");
		listaCiudades.addItem("Barrinquilla");
		listaCiudades.addItem("Cali");
		listaCiudades.addItem("Tolima");
		listaCiudades.addItem("Bucaramanga");

		c.add(listaTipo);
		listaTipo.setBounds(330, 400,150,20);
		listaTipo.addItem("¿En venta o arriendo?");
		listaTipo.addItem("Venta");
		listaTipo.addItem("Arriendo");
		listaTipo.addItem("Permuta");
		listaTipo.addItem("Leasing");	

		c.add(listaClase);
		listaClase.setBounds(500,400,115,20);
		listaClase.addItem("¿Que buscas?");
		listaClase.addItem("Casa");
		listaClase.addItem("Apartamento");
		listaClase.addItem("Local");	

		c.add(listaEstado);	
		listaEstado.setBounds(630,400, 125,20);
		listaEstado.addItem("¿Nuevo o usado?");
		listaEstado.addItem("Nuevo");
		listaEstado.addItem("Usado");

		c.add(lBusq);
		lBusq.setText("¿Que estas buscando?: ");
		lBusq.setBounds(330, 370, 150, 23);

		c.add(nBusq);
		nBusq.setText("escribe una breve descripción");
		nBusq.setBounds(480, 370, 180, 23);

		c.add(enviar);
		enviar.setBounds(534, 430, 180, 40);
		c.add(nRegistro);
		nRegistro.setBounds(271, 430, 180, 40);
		c.add(agenda);
		agenda.setBounds(331, 480, 90, 20);
		c.add(modificar);
		modificar.setBounds(534, 480, 90, 20);

		tk = Toolkit.getDefaultToolkit();
		try {
			ubicacion = String.format("/Imagenes/botonBuscar.png");
			BBsimg = tk.getImage(getClass().getResource(ubicacion));
			enviar.setIcon(new ImageIcon(BBsimg));
		} catch (Exception e) {
			System.out.println("Error imagen boton buscar");
		}
		
		modificar.addActionListener (this);	
		agenda.addActionListener (this);	
		enviar.addActionListener (this);		
		nRegistro.addActionListener (this);		
		tracker = new MediaTracker(this);

	}

	public void paint(Graphics g){
		super.paint(g);
		ubicacion = String.format("/Imagenes/bogotaHome.jpg");
		Image BHimg = tk.getImage(getClass().getResource(ubicacion));
		try {
			tracker.addImage(BHimg, 1);
			tracker.waitForID(1);
		} catch (Exception e1) {
			System.out.println("Error imagen home Bogota");
		}
		g.drawImage(BHimg, 73, 70, 850, 300,this);
	}

	int mem=5;
	public void actionPerformed (ActionEvent e){
		String descp = nBusq.getText(); 
		String ciudad=(String)listaCiudades.getSelectedItem();
		String tipo=(String)listaTipo.getSelectedItem();
		String clase=(String)listaClase.getSelectedItem();
		String estado=(String)listaEstado.getSelectedItem();
		if (enviar == e.getSource()){
			busqueda bsq = new busqueda(descp, ciudad, tipo, clase, estado);
			bsq.Main();
			dispose();
		}
		if (nRegistro == e.getSource()){
			registrarNuevo reg = new registrarNuevo(0);
			reg.Main(0);
			dispose();
		}
		if (agenda == e.getSource()){
			Agenda ag = new Agenda();
			ag.Main();
		}
		if (modificar == e.getSource()){
			modificar md = new modificar();
			md.Main();
		}
		
	}
}
