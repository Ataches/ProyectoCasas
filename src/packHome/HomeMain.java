package packHome;

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
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class HomeMain extends JFrame implements ActionListener {


	JComboBox<String> listaCiudades = new JComboBox();
	JComboBox<String> listaTipo = new JComboBox();
	JComboBox<String> listaClase = new JComboBox();
	JComboBox<String> listaEstado = new JComboBox();

	JTextField nombre = new JTextField(45);

	MediaTracker tracker;
	Toolkit tk; 

	JTextField DTposX = new JTextField(2);
	JButton enviar = new JButton ();

	public static void main(String[] args) {
		HomeMain HM = new HomeMain();
		HM.setSize(1280,768);
		HM.setVisible(true);	
		HM.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	String ubicacion;
	Image BBsimg;

	HomeMain(){
		super("Ajedrez");

		repaint();
		Container c = getContentPane();
		c.setLayout(null);

		listaCiudades.setBounds(400,470,80,20);
		listaCiudades.addItem("Bogotá");
		listaCiudades.addItem("Medellin");
		listaCiudades.addItem("Cartagena");
		listaCiudades.addItem("Barrinquilla");
		listaCiudades.addItem("Cali");
		listaCiudades.addItem("Tolima");
		listaCiudades.addItem("Bucaramanga");
		c.add(listaCiudades);

		listaTipo.setBounds(500, 470,80,20);
		listaTipo.addItem("Venta");
		listaTipo.addItem("Arriedo");
		listaTipo.addItem("Permuta");
		listaTipo.addItem("Leasing");	
		c.add(listaTipo);

		listaClase.setBounds(600,470,80,20);
		listaClase.addItem("Casa");
		listaClase.addItem("Apartamento");
		listaClase.addItem("Local");	
		c.add(listaClase);

		listaEstado.setBounds(700,470,80,20);
		listaEstado.addItem("Nuevo");
		listaEstado.addItem("Usado");	
		c.add(listaEstado);	

		nombre.setText("¿Que estas buscando?");
		nombre.setBounds(180,470,190,23);
		c.add(nombre);


		tk = Toolkit.getDefaultToolkit();

		try {
			ubicacion = String.format("/Imagenes/botonBuscar.png");
			BBsimg = tk.getImage(getClass().getResource(ubicacion));
			enviar.setIcon(new ImageIcon(BBsimg));
		} catch (Exception e) {
			System.out.println("Error imagen boton buscar");
		}
		enviar.setBounds(800, 460, 180, 40);
		c.add(enviar);
		
		tracker = new MediaTracker(this);
		enviar.addActionListener (this);
		nombre.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
				   nombre.setText("");
			   }
			});
	}
	public void paint(Graphics g){
		tk = Toolkit.getDefaultToolkit();
		ubicacion = String.format("/Imagenes/bogotaHome.jpg");
		Image BHimg = tk.getImage(getClass().getResource(ubicacion));
		try {
			tracker.addImage(BHimg, 1);
			tracker.waitForID(1);
		} catch (Exception e1) {
			System.out.println("Error imagen home Bogota");
		}
		g.drawImage(BHimg, 70, 70, 1110, 384,this);
	}
	public void actionPerformed(ActionEvent e) {
		Graphics g = getGraphics();
		if (e.getSource() == enviar){

			String ciudad = (String) listaCiudades.getSelectedItem();
			String tipo = (String) listaCiudades.getSelectedItem();
			String clase = (String) listaCiudades.getSelectedItem();
			String estado = (String) listaCiudades.getSelectedItem();

			ResultadosBusq rb = new ResultadosBusq(ciudad, tipo, clase, estado);

		}

	}

}
