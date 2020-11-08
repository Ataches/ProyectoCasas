package presentation.window;

import presentation.ControllerClass;

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

	JLabel labelSearch =new JLabel();

	JButton buttonModify = new JButton ("Modificar");
	JButton buttonContactBook = new JButton ("Agenda");
	JButton buttonSend = new JButton ();
	JButton newRegister = new JButton ("Nuevo registro");

	JTextField textFieldSearch = new JTextField(30);

	JComboBox<String> cityList = new JComboBox<String>();
	JComboBox<String> saleTypeList = new JComboBox<String>();
	JComboBox<String> propertyTypeList = new JComboBox<String>();
	JComboBox<String> propertyStateList = new JComboBox<String>();

	private final Container container;

	String sourceImage;

	public Home(ControllerClass controllerClass){
		super("Encuentra tu hogar");

		container = getContentPane();
		container.setLayout(null);

		paintElements();

		toolKit = Toolkit.getDefaultToolkit();
		try {
			sourceImage = "/Imagenes/botonBuscar.png";
			Image imageSearchButton = toolKit.getImage(getClass().getResource(sourceImage));
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
	public void paintElements(){

		container.add(cityList);
		cityList.setBounds(230,400,80,20);
		cityList.addItem("Ciudad?");
		cityList.addItem("Bogotá");
		cityList.addItem("Medellin");
		cityList.addItem("Cartagena");
		cityList.addItem("Barrinquilla");
		cityList.addItem("Cali");
		cityList.addItem("Tolima");
		cityList.addItem("Bucaramanga");

		container.add(saleTypeList);
		saleTypeList.setBounds(330, 400,150,20);
		saleTypeList.addItem("¿En venta o arriendo?");
		saleTypeList.addItem("Venta");
		saleTypeList.addItem("Arriendo");
		saleTypeList.addItem("Permuta");
		saleTypeList.addItem("Leasing");

		container.add(propertyTypeList);
		propertyTypeList.setBounds(500,400,115,20);
		propertyTypeList.addItem("¿Que buscas?");
		propertyTypeList.addItem("Casa");
		propertyTypeList.addItem("Apartamento");
		propertyTypeList.addItem("Local");

		container.add(propertyStateList);
		propertyStateList.setBounds(630,400, 125,20);
		propertyStateList.addItem("¿Nuevo o usado?");
		propertyStateList.addItem("Nuevo");
		propertyStateList.addItem("Usado");

		container.add(labelSearch);
		labelSearch.setText("¿Que estas buscando?: ");
		labelSearch.setBounds(330, 370, 150, 23);

		container.add(textFieldSearch);
		textFieldSearch.setText("escribe una breve descripción");
		textFieldSearch.setBounds(480, 370, 180, 23);

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
		sourceImage = "/Imagenes/bogotaHome.jpg";
		Image imageHome = toolKit.getImage(getClass().getResource(sourceImage));
		try {
			tracker.addImage(imageHome, 1);
			tracker.waitForID(1);
		} catch (Exception e1) {
			System.out.println("Error imagen home Bogota");
		}
		g.drawImage(imageHome, 73, 70, 850, 300,this);
	}

	public JButton getButtonSend() {
		return buttonSend;
	}

	public JButton getButtonNewRegister() {
		return newRegister;
	}

	public JButton getButtonContactBook() { return buttonContactBook; }

	public JButton getButtonModify() { return buttonModify; }

	public List<String> getSearchData() {
		List<String> listSearch = new ArrayList<>();
		listSearch.add(textFieldSearch.getText());
		listSearch.add((String) cityList.getSelectedItem());
		listSearch.add((String) saleTypeList.getSelectedItem());
		listSearch.add((String) propertyTypeList.getSelectedItem());
		listSearch.add((String) propertyStateList.getSelectedItem());
		return listSearch;
	}

}
