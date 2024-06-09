
// Baja Usuario
package es.studium.programagestion;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class BajaUsuario implements WindowListener, ActionListener {
	Frame ventanaBajaUsuario = new Frame("Usuario");
	Label lblnomusu = new Label("Nombre Usuario");
	TextField txtnomusu = new TextField(15);
	Label lbltipousu = new Label("Tipo Usuario");
	TextField txttiposu = new TextField(15);
	Label lblpassword = new Label("Password");
	TextField txtpassword = new TextField(15);
	Button btnaceptar = new Button("Aceptar");
	Button btncancelar = new Button("Cancelar");
	Dialog dlgSeguro=new Dialog(ventanaBajaUsuario,"¿Seguro@?",true);
	Label lblMensaje=new Label("¿Seguro que quiere borrar?");
	Button btnSi=new Button("Si");
	Button btnNo=new Button("No");
	Datos datos=new Datos();

	public BajaUsuario ()
	{	//Añadimos los elementos a la ventana
		ventanaBajaUsuario.setLayout(new FlowLayout());
		ventanaBajaUsuario.setSize(250,160);
		ventanaBajaUsuario.setResizable(false);
		ventanaBajaUsuario.setVisible(true);
		ventanaBajaUsuario.addWindowListener(this);
		ventanaBajaUsuario.setLocationRelativeTo(null);

		//Añadimos los elementos a la ventana
		ventanaBajaUsuario.add(lblnomusu);
		ventanaBajaUsuario.add(txtnomusu);
		ventanaBajaUsuario.add(lbltipousu);
		ventanaBajaUsuario.add(txttiposu);
		ventanaBajaUsuario.add(lblpassword);
		ventanaBajaUsuario.add(txtpassword);
		ventanaBajaUsuario.add(btnaceptar);
		ventanaBajaUsuario.add(btncancelar);
		//Añadimos los botones
		btnaceptar.addActionListener(this);
		btncancelar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnaceptar) {
            // Mostrar diálogo de confirmación
            dlgSeguro.setLayout(new FlowLayout());
            dlgSeguro.setSize(200, 100);
            dlgSeguro.add(lblMensaje);
            dlgSeguro.add(btnSi);
            dlgSeguro.add(btnNo);
            dlgSeguro.setVisible(true);
        } else if (e.getSource() == btncancelar) {
            ventanaBajaUsuario.setVisible(false);
        } else if (e.getSource() == btnSi) {
            // Eliminar el usuario
            String nombreUsuario = txtnomusu.getText();
            if (datos.eliminarUsuario(nombreUsuario, "usuario_actual")) {
                System.out.println("Usuario eliminado con éxito.");
            } else {
                System.err.println("Error al eliminar el usuario.");
            }
            dlgSeguro.setVisible(false);
            ventanaBajaUsuario.setVisible(false);
        } else if (e.getSource() == btnNo) {
            dlgSeguro.setVisible(false);
        }
    }
		
	

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
