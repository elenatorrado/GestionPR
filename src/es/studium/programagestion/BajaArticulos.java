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
    private Frame ventanaBajaArticulo;
    private Label lblnombreart;
    private Button btnaceptar;
    private Button btncancelar;
    private Datos datos;
    private Choice chcnombreart;
    
    // Confirmation Dialog
    private Dialog dlgSeguro;
    private Label lblMensaje;
    private Button btnSi;
    private Button btnNo;
    
    // Success Dialog
    private Dialog dlgConfirm;
    private Label lblConfirm;

    public BajaArticulos() {
        datos = new Datos();
        ventanaBajaArticulo = new Frame("Baja Articulo");
        lblnombreart = new Label("Nombre");
        btnaceptar = new Button("Aceptar");
        btncancelar = new Button("Cancelar");
        chcnombreart = new Choice();

        ventanaBajaArticulo.setLayout(new FlowLayout());
        ventanaBajaArticulo.setSize(300, 150);
        ventanaBajaArticulo.setResizable(false);
        ventanaBajaArticulo.setVisible(true);
        ventanaBajaArticulo.addWindowListener(this);
        ventanaBajaArticulo.setLocationRelativeTo(null);
        ventanaBajaArticulo.add(lblnombreart);
        ventanaBajaArticulo.add(chcnombreart);
        ventanaBajaArticulo.add(btnaceptar);
        ventanaBajaArticulo.add(btncancelar);

        btnaceptar.addActionListener(this);
        btncancelar.addActionListener(this);
        
        String[] BajaCorrecta = datos.rellenarChoiceArticuloEliminar();
        for(String elemento: BajaCorrecta) {
            chcnombreart.add(elemento);
        }

        // Initialize Confirmation Dialog
        dlgSeguro = new Dialog(ventanaBajaArticulo, "¿Seguro?", true);
        lblMensaje = new Label("¿Estás seguro de eliminar este artículo?");
        btnSi = new Button("Sí");
        btnNo = new Button("No");
        dlgSeguro.setLayout(new FlowLayout());
        dlgSeguro.setSize(250, 110);
        dlgSeguro.setResizable(false);
        dlgSeguro.setLocationRelativeTo(null);
        dlgSeguro.add(lblMensaje);
        dlgSeguro.add(btnSi);
        dlgSeguro.add(btnNo);
        btnSi.addActionListener(this);
        btnNo.addActionListener(this);
        dlgSeguro.addWindowListener(this);

        // Initialize Success Dialog
        dlgConfirm = new Dialog(ventanaBajaArticulo, "Baja Correcta", true);
        lblConfirm = new Label("El artículo ha sido eliminado correctamente.");
        dlgConfirm.setLayout(new FlowLayout());
        dlgConfirm.setSize(210, 100);
        dlgConfirm.setResizable(false);
        dlgConfirm.setLocationRelativeTo(null);
        dlgConfirm.add(lblConfirm);
        dlgConfirm.addWindowListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnaceptar)) {
            if (chcnombreart.getSelectedIndex() != 0) {
                dlgSeguro.setVisible(true);
            }
        } else if (e.getSource().equals(btnSi)) {
            String seleccionArticulo = chcnombreart.getSelectedItem();
            if (datos.eliminarArticulo(seleccionArticulo)) { // Assuming eliminarArticulo is a method in Datos
                dlgSeguro.setVisible(false);
                dlgConfirm.setVisible(true);
            } else {
                System.out.println("Error al eliminar el artículo.");
            }
        } else if (e.getSource().equals(btnNo)) {
            dlgSeguro.setVisible(false);
        } else if (e.getSource().equals(btncancelar)) {
            ventanaBajaArticulo.setVisible(false);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {
        if (dlgConfirm.isActive()) {
            dlgConfirm.setVisible(false);
        } else if (dlgSeguro.isActive()) {
            dlgSeguro.setVisible(false);
        } else {
            ventanaBajaArticulo.setVisible(false);
        }
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
