//LOGIN
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

	public class Login implements WindowListener, ActionListener{

		//Creamos los elementos del Login
		Frame ventana = new Frame ("Login");
		Label lblnombre= new Label("Nombre Usuario");
		TextField txtnombre= new TextField(15);
		Label lblcontrasenia= new Label("Password");
		TextField txtpassword= new TextField(15);
		Button btnaceptar= new Button("Aceptar");
		Button btncancelar= new Button("Cancelar");
		Dialog dlgerror= new Dialog(ventana,"Error",false);
		Label lblerror= new Label("Error al iniciar sesión, intentelo de nuevo");
		

		//Ditribuimos los elementos en la ventana de Login
		public Login()
		{
			ventana.setLayout(new FlowLayout());
			txtpassword.setEchoChar('*');
			ventana.setSize(200,200);
			ventana.setResizable(false);
			ventana.setVisible(true);
			ventana.addWindowListener(this);

			//Añadimos los elementos a la ventana
			ventana.add(lblnombre);
			ventana.add(txtnombre);
			ventana.add(lblcontrasenia);
			ventana.add(txtpassword);
			ventana.add(btnaceptar);
			ventana.add(btncancelar);
			ventana.setLocationRelativeTo(null);
			//Añadimos los botones
				btnaceptar.addActionListener(this);
				btncancelar.addActionListener(this);
		}

		//Creamos el main
		public static void main(String[] args) 
		{
			new Login();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(btncancelar))
	        {
				txtnombre.setText("");
				txtpassword.setText("");
	            txtnombre.requestFocus();
	        }
	        else if(e.getSource().equals(btnaceptar))
	        {
	            // Conectar a la BD
	            Datos datos = new Datos();
	            if(datos.conectar()==true)
	            {
	                // Si OK, comprobarlascredenciales
	                String usuario = txtnombre.getText();
	                String clave = txtpassword.getText();

	                if(datos.comprobarCredenciales(usuario, clave)== true)
	                {
	                    // Si OK, ir al Menú Principal
	                    new PantallaPrincipal();
	                    ventana.setVisible(false);
	                }
	                else
	                {
	                    // Si no OK, mostrarDiálogo
	                    dlgerror.setLayout(new FlowLayout());
	                    dlgerror.setSize(420,120);
	                    dlgerror.setResizable(false);
	                    dlgerror.setLocationRelativeTo(null);
	                    dlgerror.add(lblerror);
	                    dlgerror.addWindowListener(this);
	                    dlgerror.setVisible(true);
	                    
	                }
	            }
	            else
	            {
	                System.out.println("Conexión rechazada");
	            }
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

