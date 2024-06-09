package es.studium.programagestion;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class AltaTickets implements WindowListener, ActionListener {
    // Creamos los elementos del Alta Tickets
    Frame ventanaAltaTickets = new Frame("Alta Tickets");
    Label lbldescripcion = new Label("Descripción");
    TextField txtdescripcion = new TextField(15);
    Label lblfecha = new Label("Fecha");
    TextField txtfecha = new TextField(15);
    Label lblimporte = new Label("Importe");
    TextField txtimporte = new TextField(15);
    Label lblempleado = new Label("Empleado");
    Choice chcempleado = new Choice();
    Button btnaceptar = new Button("Aceptar");
    Button btncancelar = new Button("Cancelar");
    Datos datAltaTickets = new Datos();
    Dialog dlgAltaTickets = new Dialog(ventanaAltaTickets, "Alta", true);
    Label lblAltaTickets = new Label("Alta realizada correctamente");

    // Distribuimos los elementos en la ventana de Alta
    public AltaTickets() {
        // Conectar a la base de datos
        datAltaTickets.conectar();

        // Añadimos los elementos a la ventana
        ventanaAltaTickets.setLayout(new FlowLayout());
        ventanaAltaTickets.setSize(250, 220);
        ventanaAltaTickets.setResizable(false);
        ventanaAltaTickets.setVisible(true);
        ventanaAltaTickets.addWindowListener(this);
        ventanaAltaTickets.setLocationRelativeTo(null);

        // Añadimos los elementos a la ventana
        ventanaAltaTickets.add(lbldescripcion);
        ventanaAltaTickets.add(txtdescripcion);
        ventanaAltaTickets.add(lblfecha);
        ventanaAltaTickets.add(txtfecha);
        ventanaAltaTickets.add(lblimporte);
        ventanaAltaTickets.add(txtimporte);
        ventanaAltaTickets.add(lblempleado);
        ventanaAltaTickets.add(chcempleado);
        ventanaAltaTickets.add(btnaceptar);
        ventanaAltaTickets.add(btncancelar);

        // Rellenamos el Choice con los empleados
        for (String empleado : datAltaTickets.rellenarChoiceEmpleados()) {
            chcempleado.add(empleado);
        }

        // Añadimos los botones
        btnaceptar.addActionListener(this);
        btncancelar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Con esto si pulsamos cancelar se borra el texto escrito
        if (e.getSource().equals(btncancelar)) {
            txtdescripcion.setText("");
            txtfecha.setText("");
            txtimporte.setText("");
            chcempleado.select(0);
            txtdescripcion.requestFocus();
        }
        // Si clicamos en el botón aceptar
        if (e.getSource().equals(btnaceptar)) {
            // Comprobamos que la conexión está establecida
            if (datAltaTickets.connection == null) {
                System.out.println("Error: No hay conexión a la base de datos");
                return;
            }

            // Comprobamos que la sentencia fue ejecutada correctamente y que los datos son correctos
            String descripcion = txtdescripcion.getText();
            String fecha = txtfecha.getText();
            String importe = txtimporte.getText();
            String empleado = chcempleado.getSelectedItem().split("-")[0]; // Obtener solo el id del empleado

            boolean altaCorrecta = datAltaTickets.ticketsAlta(descripcion, fecha, importe, empleado);

            // Añadimos el diálogo a la ventana del Alta
            dlgAltaTickets.setLayout(new FlowLayout());
            dlgAltaTickets.setSize(250, 160);
            dlgAltaTickets.setResizable(false);
            dlgAltaTickets.addWindowListener(this);

            // Si el Alta es correcta
            if (altaCorrecta) {
                lblAltaTickets.setText("Alta realizada correctamente");
            } else {
                lblAltaTickets.setText("No se ha realizado el alta correctamente");
            }

            dlgAltaTickets.add(lblAltaTickets);
            dlgAltaTickets.setVisible(true);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        // No implementado
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {
        // No implementado
    }

    @Override
    public void windowIconified(WindowEvent e) {
        // No implementado
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        // No implementado
    }

    @Override
    public void windowActivated(WindowEvent e) {
        // No implementado
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        // No implementado
    }
}
