
//AltaEmpleado

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

public class AltaEmpleado implements WindowListener, ActionListener {

	//Creamos los elementos del Alta Empleado
	Frame ventana = new Frame ("Alta Empleado");
	Label lblnombre= new Label("Nombre");
	TextField txtnombre= new TextField(15);
	Label lblapellido= new Label("Apellido");
	TextField txtapellido= new TextField(15);
	Label lblpuesto= new Label("Puesto");
	TextField txtpuesto= new TextField(15);
	Button btnaceptar= new Button("Aceptar");
	Button btncancelar= new Button("Cancelar");
	Datos datAlta=new Datos();
	Dialog dlgAlta=new Dialog(ventana,"Alta",true);
	Label lblAlta=new Label("Alta realizada correctamente");
	//Ditribuimos los elementos en la ventana de Login
	public AltaEmpleado()
	{	//Añadimos los elementos a la ventana
		ventana.setLayout(new FlowLayout());
		ventana.setSize(250,160);
		ventana.setResizable(false);
		ventana.setVisible(true);
		ventana.addWindowListener(this);
		ventana.setLocationRelativeTo(null);

		//Añadimos los elementos a la ventana
		ventana.add(lblnombre);
		ventana.add(txtnombre);
		ventana.add(lblapellido);
		ventana.add(txtapellido);
		ventana.add(lblpuesto);
		ventana.add(txtpuesto);
		ventana.add(btnaceptar);
		ventana.add(btncancelar);
		//Añadimos los botones
		btnaceptar.addActionListener(this);
		btncancelar.addActionListener(this);
	}
	//Creamos el main
	public static void main(String[] args) 
	{
		new AltaEmpleado();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Con esto si pulsamos cancelar se borra el texto escrito
		if(e.getSource().equals(btncancelar))
		{
			txtnombre.setText("");
			txtapellido.setText("");
			txtpuesto.setText("");
			txtnombre.requestFocus();
		}
		//Si clicamos en el botón aceptar 
		if(e.getSource().equals(btnaceptar)) {
			//Concetamos la BD con el Objeto previamente creado
			datAlta.conectar();
			//Comprobamos que la sentencia fue ejecutada correctamente y que los datos son correctos
			String nombre=txtnombre.getText();
			String apellido=txtapellido.getText();
			String puesto=txtpuesto.getText();
			boolean altaCorrecta=datAlta.empleadoAlta(nombre, apellido, puesto);

			//Añadimos el dialogo a la ventana del Alta
			dlgAlta.setLayout(new FlowLayout());
			dlgAlta.setSize(250,160);
			dlgAlta.setResizable(false);
			dlgAlta.addWindowListener(this);

			//Si el Alta es correcta
			if(altaCorrecta==true) 
			{
				lblAlta.setText("Alta Realizada Correctamente");
				dlgAlta.add(lblAlta);
			}
			//Si no se ha realizado correctamente el Alta
			else
			{
				lblAlta.setText("No se ha realizado el alta correctamente");
			}

			dlgAlta.setVisible(true);
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		ventana.setVisible(false);

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
