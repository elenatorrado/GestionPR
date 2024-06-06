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

public class ModificacionesUsuario implements WindowListener, ActionListener {
    Frame ventanaModUsu = new Frame("Modificaciones Usuario");
    Label lblNombreUsuario = new Label("Nombre");
    Choice chcNombreUsuario = new Choice();
    Label lblNuevoValor = new Label("Nuevo Valor");
    TextField txtNuevoValor = new TextField(20);
    Button btnAceptar = new Button("Aceptar");
    Button btnCancelar = new Button("Cancelar");
    Dialog dlgSeguro = new Dialog(ventanaModUsu, "¿Seguro?", true);
    Label lblMensaje = new Label("¿Seguro que quiere modificar el usuario?");
    Button btnSi = new Button("Si");
    Button btnNo = new Button("No");
    Datos datos = new Datos();

    public ModificacionesUsuario() {
        // Configurar la ventana principal
        ventanaModUsu.setLayout(new FlowLayout());
        ventanaModUsu.setSize(300, 200);
        ventanaModUsu.setResizable(false);
        ventanaModUsu.setVisible(true);
        ventanaModUsu.addWindowListener(this);
        ventanaModUsu.setLocationRelativeTo(null);

        // Añadir elementos a la ventana principal
        ventanaModUsu.add(lblNombreUsuario);
        ventanaModUsu.add(chcNombreUsuario);
        ventanaModUsu.add(lblNuevoValor);
        ventanaModUsu.add(txtNuevoValor);
        ventanaModUsu.add(btnAceptar);
        ventanaModUsu.add(btnCancelar);

        // Añadir listeners a los botones
        btnAceptar.addActionListener(this);
        btnCancelar.addActionListener(this);

        // Configurar el diálogo de confirmación
        dlgSeguro.setLayout(new FlowLayout());
        dlgSeguro.setSize(300, 120);
        dlgSeguro.add(lblMensaje);
        dlgSeguro.add(btnSi);
        dlgSeguro.add(btnNo);
        dlgSeguro.setLocationRelativeTo(null);

        // Añadir listeners a los botones del diálogo
        btnSi.addActionListener(this);
        btnNo.addActionListener(this);

        // Llenar la lista de usuarios
        datos.conectar();
        for (String usuario : datos.RellenarchcUsuarioMod()) {
            chcNombreUsuario.add(usuario);
        }
        datos.desconectar();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAceptar) {
            dlgSeguro.setVisible(true);
        } else if (e.getSource() == btnCancelar) {
            ventanaModUsu.setVisible(false);
        } else if (e.getSource() == btnSi) {
            String usuarioSeleccionado = chcNombreUsuario.getSelectedItem();
            String nuevoValor = txtNuevoValor.getText();
            if (!usuarioSeleccionado.equals("Elegir un Usuario...") && !nuevoValor.isEmpty()) {
                datos.conectar();
                datos.modificarUsuario(usuarioSeleccionado, nuevoValor);
                datos.desconectar();
                dlgSeguro.setVisible(false);
                ventanaModUsu.setVisible(false);
            }
        } else if (e.getSource() == btnNo) {
            dlgSeguro.setVisible(false);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        ventanaModUsu.setVisible(false);
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
