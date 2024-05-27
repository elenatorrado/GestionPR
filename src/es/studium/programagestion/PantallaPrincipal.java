	package es.studium.programagestion;

	import java.awt.FlowLayout;
	import java.awt.Frame;
	import java.awt.MenuBar;
	import java.awt.MenuItem;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.event.WindowEvent;
	import java.awt.event.WindowListener;
	import java.awt.Menu;

	public class PantallaPrincipal implements WindowListener, ActionListener {
		Frame pantallaPrincipal = new Frame ("Pantalla Principal");
		MenuBar Menu= new MenuBar();
		Menu mEmpleado= new Menu("Empleados");
		Menu mTickets= new Menu("Tickets");
		Menu mArticulos= new Menu("Artículos");
		Menu mCompras= new Menu("Compras");

		//Añadimos los Items de cada tabla

		//Tabla Empleado

		MenuItem empleadoAlta= new MenuItem("Alta");
		MenuItem empleadoBaja= new MenuItem("Baja");
		MenuItem empleadoModificacion= new MenuItem("Modificación");
		MenuItem empleadoConsulta= new MenuItem("Consulta");

		//Tabla Tickets
		MenuItem ticketsAlta= new MenuItem("Alta");
		MenuItem ticketsBaja= new MenuItem("Baja");
		MenuItem ticketsModificacion= new MenuItem("Modificación");
		MenuItem ticketsConsulta= new MenuItem("Consulta");

		//Tabla Articulos
		MenuItem articulosAlta= new MenuItem("Alta");
		MenuItem articulosBaja= new MenuItem("Baja");
		MenuItem articulosModificacion= new MenuItem("Modificación");
		MenuItem articulosConsulta= new MenuItem("Consulta");

		// Tabla Compras
		MenuItem comprasAlta= new MenuItem("Alta");
		MenuItem comprasBaja= new MenuItem("Baja");
		MenuItem comprasModificacion= new MenuItem("Modificación");
		MenuItem comprasConsulta= new MenuItem("Consulta");
	//Distrubuimos los elementos en la ventana
		 public PantallaPrincipal()
		    	{

			//Añadimos los parametros de la ventana
			pantallaPrincipal.setLayout(new FlowLayout());
			pantallaPrincipal.setSize(400,400);
			pantallaPrincipal.setResizable(false);
			pantallaPrincipal.setVisible(true);
			pantallaPrincipal.addWindowListener(this);
			pantallaPrincipal.setMenuBar(Menu);
			pantallaPrincipal.setLocationRelativeTo(null);
			//Añadimos los elementos
			Menu.add(mEmpleado);
			Menu.add(mArticulos);
			Menu.add(mTickets);
			Menu.add(mCompras);
			

			mEmpleado.add(empleadoAlta);
			mEmpleado.add(empleadoBaja);
			mEmpleado.add(empleadoModificacion);
			mEmpleado.add(empleadoConsulta);

			mArticulos.add(articulosAlta);
			mArticulos.add(articulosBaja);
			mArticulos.add(articulosModificacion);
			mArticulos.add(articulosConsulta);

			mTickets.add(ticketsAlta);
			mTickets.add(ticketsBaja);
			mTickets.add(ticketsModificacion);
			mTickets.add(ticketsConsulta);

			mCompras.add(comprasAlta);
			mCompras.add(comprasBaja);
			mCompras.add(comprasModificacion);
			mCompras.add(comprasConsulta);
			
			//Añadimos el ActionListener
			empleadoConsulta.addActionListener(this);
			empleadoAlta.addActionListener(this);
			empleadoBaja.addActionListener(this);


		}


		//Creamos el main
		public static void main(String[] args) 
		{
			new PantallaPrincipal();
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(empleadoConsulta))
	        {
				new ConsultaEmpleado();
	        }
			 if (e.getSource().equals(empleadoAlta))
			{
				new AltaEmpleado();
			}
			 if (e.getSource().equals(empleadoBaja))
				{
					new BajaEmpleado();
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