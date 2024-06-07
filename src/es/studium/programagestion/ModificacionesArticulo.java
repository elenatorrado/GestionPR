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

public class ModificacionesArticulo implements WindowListener, ActionListener {
    Frame ventanaModArt = new Frame("Modificaciones Articulo");
    Label lblNombreArticulo = new Label("Nombre del Artículo");
    Choice chcNombreArticulo = new Choice();
    Label lblNuevoNombre = new Label("Nuevo Nombre");
    TextField txtNuevoNombre = new TextField(60);
    Label lblNuevaDescripcion = new Label("Nueva Descripción");
    TextField txtNuevaDescripcion = new TextField(60);
    Label lblNuevoPrecio = new Label("Nuevo Precio");
    TextField txtNuevoPrecio = new TextField(70);
    Label lblNuevoStock = new Label("Nuevo Stock");
    TextField txtNuevoStock = new TextField(70);
    Button btnAceptar = new Button("Aceptar");
    Button btnCancelar = new Button("Cancelar");
    Dialog dlgConfirmacion = new Dialog(ventanaModArt, "¿Seguro?", true);
    Label lblMensaje = new Label("¿Seguro que desea modificar este artículo?");
    Button btnSi = new Button("Si");
    Button btnNo = new Button("No");
    Datos datos = new Datos();

    public ModificacionesArticulo() {
        // Configurar la ventana principal
        ventanaModArt.setLayout(new FlowLayout());
        ventanaModArt.setSize(650, 300);
        ventanaModArt.setResizable(false);
        ventanaModArt.setVisible(true);
        ventanaModArt.addWindowListener(this);
        ventanaModArt.setLocationRelativeTo(null);

        // Añadir elementos a la ventana principal
        ventanaModArt.add(lblNombreArticulo);
        ventanaModArt.add(chcNombreArticulo);
        ventanaModArt.add(lblNuevoNombre);
        ventanaModArt.add(txtNuevoNombre);
        ventanaModArt.add(lblNuevaDescripcion);
        ventanaModArt.add(txtNuevaDescripcion);
        ventanaModArt.add(lblNuevoPrecio);
        ventanaModArt.add(txtNuevoPrecio);
        ventanaModArt.add(lblNuevoStock);
        ventanaModArt.add(txtNuevoStock);
        ventanaModArt.add(btnAceptar);
        ventanaModArt.add(btnCancelar);

        // Añadir listeners a los botones
        btnAceptar.addActionListener(this);
        btnCancelar.addActionListener(this);

        // Configurar el cuadro de diálogo de confirmación
        dlgConfirmacion.setLayout(new FlowLayout());
        dlgConfirmacion.setSize(300, 150);
        dlgConfirmacion.add(lblMensaje);
        dlgConfirmacion.add(btnSi);
        dlgConfirmacion.add(btnNo);
        dlgConfirmacion.setLocationRelativeTo(null);

        // Añadir listeners a los botones del cuadro de diálogo
        btnSi.addActionListener(this);
        btnNo.addActionListener(this);

        // Llenar la lista de artículos
        datos.conectar();
        for (String articulo : datos.rellenarChoiceArticulosMod()) {
            chcNombreArticulo.add(articulo);
        }
        datos.desconectar();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAceptar) {
            dlgConfirmacion.setVisible(true);
        } else if (e.getSource() == btnCancelar) {
            ventanaModArt.setVisible(false);
        } else if (e.getSource() == btnSi) {
            String nombreArticuloSeleccionado = chcNombreArticulo.getSelectedItem();
            String nuevoNombre = txtNuevoNombre.getText();
            String nuevaDescripcion = txtNuevaDescripcion.getText();
            float nuevoPrecio = Float.parseFloat(txtNuevoPrecio.getText());
            int nuevoStock = Integer.parseInt(txtNuevoStock.getText());
            if (!nombreArticuloSeleccionado.equals("Elegir un Artículo...") && !nuevoNombre.isEmpty() && !nuevaDescripcion.isEmpty()) {
                datos.conectar();
                datos.modificarArticulo(nombreArticuloSeleccionado, nuevoNombre, nuevaDescripcion, nuevoPrecio, nuevoStock);
                datos.desconectar();
                dlgConfirmacion.setVisible(false);
                ventanaModArt.setVisible(false);
            }
        } else if (e.getSource() == btnNo) {
            dlgConfirmacion.setVisible(false);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        ventanaModArt.setVisible(false);
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
    

