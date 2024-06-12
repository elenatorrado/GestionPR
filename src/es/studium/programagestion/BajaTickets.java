package es.studium.programagestion;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class BajaTickets implements WindowListener, ActionListener {
    Frame ventanaBajaTickets = new Frame("Baja Tickets");
    Label lblBajaTickets = new Label("Elegir Ticket a Borrar: ");
    Choice chcTickets = new Choice();
    Button btnAceptar = new Button("Aceptar");
    Button btnCancelar = new Button("Cancelar");
    Dialog dlgSeguro = new Dialog(ventanaBajaTickets, "¿Seguro?", true);
    Label lblMensaje = new Label("¿Seguro que quiere borrar?");
    Button btnSi = new Button("Si");
    Button btnNo = new Button("No");
    Dialog dlgResultado = new Dialog(ventanaBajaTickets, "Resultado", true);
    Label lblResultado = new Label();
    Datos datos = new Datos();

    public BajaTickets() {
        // Añadimos los elementos a la ventana
        ventanaBajaTickets.setLayout(new FlowLayout());
        ventanaBajaTickets.setSize(250, 140);
        ventanaBajaTickets.setResizable(false);
        ventanaBajaTickets.setVisible(true);
        ventanaBajaTickets.addWindowListener(this);
        ventanaBajaTickets.setLocationRelativeTo(null);

        // Añadimos
        ventanaBajaTickets.add(lblBajaTickets);
        datos.conectar();
        datos.rellenarChoiceTickets(chcTickets); // Utilizamos el método de la clase Datos
        ventanaBajaTickets.add(chcTickets);
        ventanaBajaTickets.add(btnAceptar);
        ventanaBajaTickets.add(btnCancelar);

        // Añadimos los botones
        btnAceptar.addActionListener(this);
        btnCancelar.addActionListener(this);
        btnSi.addActionListener(this);
        btnNo.addActionListener(this);
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAceptar) {
            // Mostrar diálogo de confirmación
            dlgSeguro.setLayout(new FlowLayout());
            dlgSeguro.setSize(280, 100);
            dlgSeguro.add(lblMensaje);
            dlgSeguro.add(btnSi);
            dlgSeguro.add(btnNo);
            dlgSeguro.setVisible(true);
        } else if (e.getSource() == btnCancelar) {
            ventanaBajaTickets.setVisible(false);
        } else if (e.getSource() == btnSi) {
            // Eliminar el ticket seleccionado
            String idTicket = chcTickets.getSelectedItem().split("-")[0];
            if (datos.eliminarTicket(Integer.parseInt(idTicket), "usuario_actual")) {
                lblResultado.setText("Ticket eliminado con éxito.");
            } else {
                lblResultado.setText("Error al eliminar el ticket.");
            }
            dlgSeguro.setVisible(false);
            dlgResultado.setLayout(new FlowLayout());
            dlgResultado.setSize(250, 150);
            dlgResultado.add(lblResultado);
            dlgResultado.setVisible(true);
        } else if (e.getSource() == btnNo) {
            dlgSeguro.setVisible(false);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {
        ventanaBajaTickets.setVisible(false);
    }

    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}
}
