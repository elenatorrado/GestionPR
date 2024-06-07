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

public class ModificacionesEmpleados implements WindowListener, ActionListener {
    Frame ventanaModEmp = new Frame("Modificaciones Empleados");
    Label lblNombreEmpleado = new Label("Nombre del Empleado");
    Choice chcNombreEmpleado = new Choice();
    Label lblNuevoNombre = new Label("Nuevo Nombre");
    TextField txtNuevoNombre = new TextField(30);
    Label lblNuevoApellido = new Label("Nuevo Apellido");
    TextField txtNuevoApellido = new TextField(30);
    Label lblNuevoPuesto = new Label("Nuevo Puesto");
    TextField txtNuevoPuesto = new TextField(40);
    Button btnAceptar = new Button("Aceptar");
    Button btnCancelar = new Button("Cancelar");
    Dialog dlgConfirmacion = new Dialog(ventanaModEmp, "¿Seguro?", true);
    Label lblMensaje = new Label("¿Seguro que desea modificar este empleado?");
    Button btnSi = new Button("Sí");
    Button btnNo = new Button("No");
    Datos datos = new Datos();

    public ModificacionesEmpleados() {
        // Configurar la ventana principal
        ventanaModEmp.setLayout(new FlowLayout());
        ventanaModEmp.setSize(450, 200);
        ventanaModEmp.setResizable(false);
        ventanaModEmp.setVisible(true);
        ventanaModEmp.addWindowListener(this);
        ventanaModEmp.setLocationRelativeTo(null);

        // Añadir elementos a la ventana principal
        ventanaModEmp.add(lblNombreEmpleado);
        ventanaModEmp.add(chcNombreEmpleado);
        ventanaModEmp.add(lblNuevoNombre);
        ventanaModEmp.add(txtNuevoNombre);
        ventanaModEmp.add(lblNuevoApellido);
        ventanaModEmp.add(txtNuevoApellido);
        ventanaModEmp.add(lblNuevoPuesto);
        ventanaModEmp.add(txtNuevoPuesto);
        ventanaModEmp.add(btnAceptar);
        ventanaModEmp.add(btnCancelar);

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

        // Llenar la lista de empleados
        datos.conectar();
        for (String empleado : datos.rellenarChoiceEmpleadosMod()) {
            chcNombreEmpleado.add(empleado);
        }
        datos.desconectar();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAceptar) {
            dlgConfirmacion.setVisible(true);
        } else if (e.getSource() == btnCancelar) {
            ventanaModEmp.setVisible(false);
        } else if (e.getSource() == btnSi) {
            String empleadoSeleccionado = chcNombreEmpleado.getSelectedItem();
            String nuevoNombre = txtNuevoNombre.getText();
            String nuevoApellido = txtNuevoApellido.getText();
            String nuevoPuesto = txtNuevoPuesto.getText();
            if (!empleadoSeleccionado.equals("Elegir un Empleado...") && !nuevoNombre.isEmpty() && !nuevoApellido.isEmpty() && !nuevoPuesto.isEmpty()) {
                datos.conectar();
                datos.modificarEmpleado(empleadoSeleccionado, nuevoNombre, nuevoApellido, nuevoPuesto);
                datos.desconectar();
                dlgConfirmacion.setVisible(false);
                ventanaModEmp.setVisible(false);
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
        ventanaModEmp.setVisible(false);
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
