package interfazGrafica;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import ManejadorExcepciones.ExcepcionGenerica;
import Usuario.Usuario;

@SuppressWarnings("serial")
/*
 * Esta es la clase principal de ventana y tiene el logueo de usuario
 */
public class Ventana extends JFrame{
	//ATRIBUTOS
	JFrame ventana;
	JLabel usuario;
	JTextField cajaUsuario;
	JButton aceptar;
	JButton cerrar;
	JPanel contenedor;
	Sonido reproductor;
	File fondoLogueo= new File("src\\Imagenes\\FondoLogueo.jpg");
	File cancionLogueo= new File("src\\Sonidos\\CancionLogueo.mp3");
	
	//CONSTRUCTOR
	public Ventana() throws ExcepcionGenerica{
		ventana= new JFrame("Logueo");
		ventana.setSize(1600,800);
		ventana.setLocationRelativeTo(null);
	    ventana.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    ventana.setLayout(null);
	    ventana.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	    ventana.setUndecorated(true);
	    ventana.setVisible(true);
	    ventana.setContentPane(new JLabel(new ImageIcon(fondoLogueo.getAbsolutePath())));
		usuario = new JLabel("Usuario: ");
		usuario.setForeground(Color.white);
		cajaUsuario = new JTextField(20);
		aceptar = new JButton("Aceptar");
		cerrar= new JButton("Salir");
		cerrar.setBackground(Color.red);
		cerrar.setForeground(Color.BLACK);
		cerrar.addActionListener(new BotonCerrar());
		aceptar.setBackground(Color.cyan);
		aceptar.setForeground(Color.BLACK);
		ventana.add(usuario);
		ventana.add(cajaUsuario);
		ventana.add(aceptar);
		ventana.add(cerrar);
		usuario.setBounds(575,302,50,10);
		cajaUsuario.setBounds(625, 300, 150, 20);
		aceptar.setBounds(650, 330,100,20);
		cerrar.setBounds(20, 700,100 , 20);
		ventana.setSize(1559,799);
		ventana.setSize(1600,800);
		try {
			reproductor=new Sonido(cancionLogueo.getAbsolutePath());
			reproductor.Reproducir();
		}
		catch(ExcepcionGenerica error) {
			error.printStackTrace();
			throw new ExcepcionGenerica(error.MensajeError());
		}
	}
	
	public class BotonCerrar implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent cerrar) {
			System.exit(0);
		}
	}
}
