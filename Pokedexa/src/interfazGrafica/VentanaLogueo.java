package interfazGrafica;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import ManejadorExcepciones.ExcepcionGenerica;
import Usuario.Usuario;

@SuppressWarnings("serial")
public class VentanaLogueo extends JFrame{
	JFrame ventana;
	JLabel usuario;
	JTextField cajaUsuario;
	JButton aceptar;
	JPanel contenedor;
	Sonido reproductor;
	Usuario usu;

	public VentanaLogueo() throws ExcepcionGenerica{
		ventana= new JFrame("Logueo");
		ventana.setSize(1600,800);
		ventana.setLocationRelativeTo(null);
	    ventana.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    ventana.setVisible(true);
	    ventana.setLayout(null);
	    ventana.setContentPane(new JLabel(new ImageIcon("C:\\Users\\Zackrer\\Desktop\\Pokedexa\\src\\Imagenes\\FondoLogueo.jpg")));
		usuario = new JLabel("Usuario: ");
		usuario.setForeground(Color.white);
		cajaUsuario = new JTextField(20);
		aceptar = new JButton("Aceptar");
		aceptar.setBackground(Color.cyan);
		ventana.add(usuario);
		ventana.add(cajaUsuario);
		ventana.add(aceptar);
		usuario.setBounds(700,302,50,10);
		cajaUsuario.setBounds(750, 300, 150, 20);
		aceptar.setBounds(775, 330,100,20);
		ventana.setSize(1559,799);
		ventana.setSize(1600,800);
		try {
			reproductor=new Sonido("C:\\Users\\Zackrer\\Desktop\\Pokedexa\\src\\Sonidos\\CancionLogueo.mp3");
			reproductor.Reproducir();
		}
		catch(ExcepcionGenerica error) {
			error.printStackTrace();
			throw new ExcepcionGenerica(error.MensajeError());
		}
	}
}
