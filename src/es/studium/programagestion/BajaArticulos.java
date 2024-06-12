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

public class BajaArticulos implements WindowListener, ActionListener {
    Frame ventanaBaja = new Frame("Baja Artículo");
    Label lblBaja = new Label("Elegir Artículo a Borrar: ");
    Choice choArticulos = new Choice();
    Button btnAceptar = new Button("Aceptar");
    Button btnCancelar = new Button("Cancelar");
    Dialog dlgSeguro = new Dialog(ventanaBaja, "¿Seguro?", true);
    Label lblMensaje = new Label("¿Seguro que quieres borrar este artículo?");
    Button btnSi = new Button("Sí");
    Button btnNo = new Button("No");
    Label lblResultado = new Label("");
    Button btnOk = new Button("OK");
    Datos datos = new Datos();

    public BajaArticulos() {
        // Añadimos los elementos a la ventana
        ventanaBaja.setLayout(new FlowLayout());
        ventanaBaja.setSize(250, 160);
        ventanaBaja.setResizable(false);
        ventanaBaja.setVisible(true);
        ventanaBaja.addWindowListener(this);
        ventanaBaja.setLocationRelativeTo(null);

        // Añadimos
        ventanaBaja.add(lblBaja);
        datos.conectar();
        datos.rellenarChoiceArticuloEliminar(choArticulos);
        ventanaBaja.add(choArticulos);
        ventanaBaja.add(btnAceptar);

        // Añadimos los botones
        btnAceptar.addActionListener(this);
        btnCancelar.addActionListener(this);
        btnSi.addActionListener(this);
        btnNo.addActionListener(this);
        btnOk.addActionListener(this);
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
            ventanaBaja.setVisible(false);
        } else if (e.getSource() == btnSi) {
            // Eliminar el artículo seleccionado
            String idArticulo = choArticulos.getSelectedItem().split("-")[0];
            if (datos.eliminarArticulo(Integer.parseInt(idArticulo))) {
                lblResultado.setText("Artículo eliminado con éxito.");
            } else {
                lblResultado.setText("Error al eliminar el artículo.");
            }
            dlgSeguro.setVisible(false);
            Dialog dlgResultado = new Dialog(ventanaBaja, "Resultado", true);
            dlgResultado.setLayout(new FlowLayout());
            dlgResultado.setSize(350, 150);
            dlgResultado.add(lblResultado);
            dlgResultado.add(btnOk);
            dlgResultado.setLocationRelativeTo(null);
            dlgResultado.setVisible(true);
        } else if (e.getSource() == btnNo) {
            dlgSeguro.setVisible(false);
        } else if (e.getSource() == btnOk) {
            System.exit(0);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {
    	System.exit(0);
    	
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
