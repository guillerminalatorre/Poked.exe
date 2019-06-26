package interfazGrafica;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import ManejadorExcepciones.ExcepcionGenerica;
import Usuario.Usuario;
import interfazGrafica.VentanaSignIn.BotonPoke1;
import interfazGrafica.VentanaSignIn.BotonPoke2;
import interfazGrafica.VentanaSignIn.BotonPoke3;

/*
 * Ventana de menu principal con todas las opciones del juego
 */
@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame{
	private JFrame ventana;
	private JButton hospital;
	private JButton batalla;
	private JButton pokedex;
	private JButton capturados;
	private JButton cerrar;
	private File IconoHospital= new File("src\\Imagenes\\Charmander.png");
	private File IconoBatalla= new File("src\\Imagenes\\Charmander.png");
	private File IconoPokedex= new File("src\\Imagenes\\Charmander.png");
	private File IconoCapturados= new File("src\\Imagenes\\Charmander.png");
	private File fondoPrincipal= new File("src\\Imagenes\\FondoPrincipal.jpg");
	private File cancionPrincipal= new File("src\\Sonidos\\CancionPrincipal.mp3");
	private Sonido reproductor3;
	
	public class BotonVolver2 implements ActionListener{
		@Override
		public void actionPerformed (ActionEvent cerrar) {

				try {
					Ventana volver=new Ventana();
					ventana.dispose();
				} catch (ExcepcionGenerica e) {
					e.printStackTrace();
				}
				ventana.dispose();

		}
	}
	public class BotonHospital implements ActionListener{
		@Override
		public void actionPerformed (ActionEvent cerrar) {

		}
	}
		
	public class BotonBatalla implements ActionListener{
		@Override
		public void actionPerformed (ActionEvent cerrar) {
					
		}
				
	}
			
	public class BotonPokedex implements ActionListener{
		@Override
		public void actionPerformed (ActionEvent cerrar) {

		}
	}
				
	public class BotonCapturados implements ActionListener{
		@Override
		public void actionPerformed (ActionEvent cerrar) {

		}
	}
	
	public VentanaPrincipal () throws ExcepcionGenerica{
		ventana= new JFrame("Principal");
		ventana.setLocationRelativeTo(null);
	    ventana.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    ventana.setLayout(null);
	    ventana.setUndecorated(true);
	    ventana.setVisible(true);
	    ventana.setContentPane(new JLabel(new ImageIcon(fondoPrincipal.getAbsolutePath())));
	    ventana.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	    hospital=new JButton();
	    cerrar=new JButton("Volver");
	    capturados=new JButton();
	    pokedex=new JButton();
	    batalla=new JButton();
		cerrar.setBackground(Color.red);
		cerrar.setForeground(Color.BLACK);
	    hospital.setIcon(new ImageIcon(IconoHospital.getAbsolutePath()));
	    batalla.setIcon(new ImageIcon(IconoBatalla.getAbsolutePath()));
	    pokedex.setIcon(new ImageIcon(IconoPokedex.getAbsolutePath()));
	    capturados.setIcon(new ImageIcon(IconoCapturados.getAbsolutePath()));
	    hospital.addActionListener(new BotonHospital());
	    batalla.addActionListener(new BotonBatalla());
	    pokedex.addActionListener(new BotonPokedex());
	    capturados.addActionListener(new BotonCapturados());
	    cerrar.addActionListener(new BotonVolver2());
		ventana.add(cerrar);
		ventana.add(hospital);
		ventana.add(batalla);
		ventana.add(pokedex);
		ventana.add(capturados);
		hospital.setBounds(200,130, 230, 230);
		batalla.setBounds(675,330, 230, 230);
		pokedex.setBounds(1225,330, 230, 230);
		capturados.setBounds(1225,330, 230, 230);
		cerrar.setBounds(20, 850,100 , 20);
	    try {
			reproductor3=new Sonido(cancionPrincipal.getAbsolutePath());
			reproductor3.Reproducir();
		}
		catch(ExcepcionGenerica error) {
			error.printStackTrace();
			throw new ExcepcionGenerica(error.MensajeError());
		}
	}
}
