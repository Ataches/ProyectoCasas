package presentation;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Home extends JFrame{

	MediaTracker tracker;
	Toolkit toolKit;

	JLabel lClase=new JLabel();
	JLabel lCiudad=new JLabel();
	JLabel lBusq=new JLabel();
	JLabel lnFoto=new JLabel();
	JLabel info=new JLabel();
	JLabel nombre=new JLabel();

	JButton buttonModify = new JButton ("Modificar");
	JButton buttonContactBook = new JButton ("Agenda");
	JButton buttonSend = new JButton ();
	JButton newRegister = new JButton ("Nuevo registro");

	JTextField nBusq=new JTextField(30);

	JComboBox<String> listaCiudades = new JComboBox<String>();
	JComboBox<String> listaTipo = new JComboBox<String>();
	JComboBox<String> listaClase = new JComboBox<String>();
	JComboBox<String> listaEstado = new JComboBox<String>();

	private Container container;

	String location;
	private Image imageSearchButton;

	public Home(ControllerClass controllerClass){
		super("Encuentra tu hogar");

		container = getContentPane();
		container.setLayout(null);

		startElements();

		toolKit = Toolkit.getDefaultToolkit();
		try {
			location = String.format("/Imagenes/botonBuscar.png");
			imageSearchButton = toolKit.getImage(getClass().getResource(location));
			buttonSend.setIcon(new ImageIcon(imageSearchButton));
		} catch (Exception e) {
			System.out.println("Error imagen boton buscar");
		}

		buttonModify.addActionListener (controllerClass);
		buttonContactBook.addActionListener (controllerClass);
		buttonSend.addActionListener (controllerClass);
		newRegister.addActionListener (controllerClass);
		tracker = new MediaTracker(this);

	}
	public void startElements(){

		container.add(listaCiudades);
		listaCiudades.setBounds(230,400,80,20);
		listaCiudades.addItem("Ciudad?");
		listaCiudades.addItem("Bogotá");
		listaCiudades.addItem("Medellin");
		listaCiudades.addItem("Cartagena");
		listaCiudades.addItem("Barrinquilla");
		listaCiudades.addItem("Cali");
		listaCiudades.addItem("Tolima");
		listaCiudades.addItem("Bucaramanga");

		container.add(listaTipo);
		listaTipo.setBounds(330, 400,150,20);
		listaTipo.addItem("¿En venta o arriendo?");
		listaTipo.addItem("Venta");
		listaTipo.addItem("Arriendo");
		listaTipo.addItem("Permuta");
		listaTipo.addItem("Leasing");

		container.add(listaClase);
		listaClase.setBounds(500,400,115,20);
		listaClase.addItem("¿Que buscas?");
		listaClase.addItem("Casa");
		listaClase.addItem("Apartamento");
		listaClase.addItem("Local");

		container.add(listaEstado);
		listaEstado.setBounds(630,400, 125,20);
		listaEstado.addItem("¿Nuevo o usado?");
		listaEstado.addItem("Nuevo");
		listaEstado.addItem("Usado");

		container.add(lBusq);
		lBusq.setText("¿Que estas buscando?: ");
		lBusq.setBounds(330, 370, 150, 23);

		container.add(nBusq);
		nBusq.setText("escribe una breve descripción");
		nBusq.setBounds(480, 370, 180, 23);

		container.add(buttonSend);
		buttonSend.setBounds(534, 430, 180, 40);
		container.add(newRegister);
		newRegister.setBounds(271, 430, 180, 40);
		container.add(buttonContactBook);
		buttonContactBook.setBounds(331, 480, 90, 20);
		container.add(buttonModify);
		buttonModify.setBounds(534, 480, 90, 20);
	}
	public void paint(Graphics g){
		super.paint(g);
		location = String.format("/Imagenes/bogotaHome.jpg");
		Image BHimg = toolKit.getImage(getClass().getResource(location));
		try {
			tracker.addImage(BHimg, 1);
			tracker.waitForID(1);
		} catch (Exception e1) {
			System.out.println("Error imagen home Bogota");
		}
		g.drawImage(BHimg, 73, 70, 850, 300,this);
	}

	public JButton getButtonSend() {
		return buttonSend;
	}

	public JButton getButtonNewRegister() {
		return newRegister;
	}

	public List<String> getSearchData() {
		List<String> listSearch = new ArrayList<>();
		listSearch.add(nBusq.getText());
		listSearch.add((String)listaCiudades.getSelectedItem());
		listSearch.add((String)listaTipo.getSelectedItem());
		listSearch.add((String)listaClase.getSelectedItem());
		listSearch.add((String)listaEstado.getSelectedItem());
		return listSearch;
	}

	public JButton getButtonContactBook() {
		return buttonContactBook;
	}

	public JButton getButtonModify() {
		return buttonModify;
	}
}
