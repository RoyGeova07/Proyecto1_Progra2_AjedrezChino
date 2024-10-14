/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_ajedrezchino;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.*;

/**
 *
 * @author royum
 */
public class CrearPlayer extends JFrame {

    public static MenuPrincipal menu;
    private JTextField TextoNombre;
    private JPasswordField Textocontrasena;
    private JTextField fechaIngresoField; // Campo para mostrar la fecha generada
    private String UsuarioActual;
    private Calendar fechaActual;  // Almacena la fecha actual generada
    private UserManager userManager;  // Instancia de UserManager
    User usuario;

    private GuardarPlayers guardarplayers;

    public CrearPlayer(GuardarPlayers guardarplayers) {
        this.guardarplayers = guardarplayers;
        

        MenuPrincipal menu;

        setTitle("Crear Jugador");
        setSize(600, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);  // Cerrar la aplicacion al cerrar la ventana

        // Crear el panel
        JPanel PanelMenu = new JPanel();
        PanelMenu.setLayout(null); // Layout absoluto
        PanelMenu.setBackground(Color.YELLOW);

        // Label y campo para nombre de usuario
        JLabel Nombre = new JLabel("Ingrese un nombre de usuario");
        Nombre.setBounds(20, 50, 200, 30);
        PanelMenu.add(Nombre);

        TextoNombre = new JTextField(20);
        TextoNombre.setBounds(260, 50, 200, 30);
        PanelMenu.add(TextoNombre);

        // Label y campo para contraseña
        JLabel Contrasena = new JLabel("Ingrese una contraseña (5 caracteres)");
        Contrasena.setBounds(20, 120, 250, 30);
        PanelMenu.add(Contrasena);

        Textocontrasena = new JPasswordField(20);
        Textocontrasena.setBounds(260, 120, 200, 30);
        PanelMenu.add(Textocontrasena);

        // Campo para mostrar la fecha generada
        JLabel labelFecha = new JLabel("Fecha de registro:");
        labelFecha.setBounds(20, 160, 200, 30);
        PanelMenu.add(labelFecha);

        fechaIngresoField = new JTextField(20);
        fechaIngresoField.setBounds(260, 160, 200, 30);
        fechaIngresoField.setEditable(false);  // No se puede editar directamente
        PanelMenu.add(fechaIngresoField);

        JButton GenerarFecha = new JButton("Generar Fecha");
        GenerarFecha.setBackground(Color.cyan);
        GenerarFecha.setBounds(130, 240, 150, 40);
        GenerarFecha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fechaActual = Calendar.getInstance();
                fechaIngresoField.setText(fechaActual.get(Calendar.DAY_OF_MONTH) + "/"
                        + (fechaActual.get(Calendar.MONTH) + 1) + "/"
                        + fechaActual.get(Calendar.YEAR) + " "
                        + fechaActual.get(Calendar.HOUR_OF_DAY) + ":"
                        + fechaActual.get(Calendar.MINUTE) + ":"
                        + fechaActual.get(Calendar.SECOND));
            }
        });
        PanelMenu.add(GenerarFecha);

        // Botón para crear el jugador
        JButton Crear = new JButton("Crear Player");
        Crear.setBackground(Color.magenta);
        Crear.setBounds(320, 240, 120, 40);
        Crear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ACCION();
            }
        });
        PanelMenu.add(Crear);

        // Botón para regresar al menú anterior
        JButton Regresar = new JButton("Regresar");
        Regresar.setBackground(Color.orange);
        Regresar.setBounds(320, 300, 120, 40);
        Regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuInicio inicio = new MenuInicio();
                dispose();
            }
        });
        PanelMenu.add(Regresar);

        // Añadir el panel a la ventana y hacer visible la ventana
        add(PanelMenu);
        setVisible(true);

        userManager = new UserManager();
    }

    private void ACCION() {
        String nombre = TextoNombre.getText().trim();
        String contrasena = Textocontrasena.getText().trim();

        // Verificar si los campos están completos
        if (nombre.isEmpty() || contrasena.isEmpty() || fechaActual == null) {
            JOptionPane.showMessageDialog(null, "Todos los campos deben ser completados y la fecha debe ser generada.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar si la contraseña tiene 5 caracteres
        if (contrasena.length() != 5) {
            JOptionPane.showMessageDialog(null, "La contraseña debe tener exactamente 5 caracteres.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Verificar si el usuario ya existe
        if (guardarplayers.ExisteUsuario(nombre)) {
            JOptionPane.showMessageDialog(null, "El usuario ya existe. Por favor, elija otro nombre.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Evitar que el usuario sea creado de nuevo
        }

        // Si el usuario no existe, proceder a crearlo
        String fechaIngreso = fechaIngresoField.getText();
        System.out.println("Creando usuario: " + nombre); // Depuración

        if (guardarplayers.AgregarUsuario(nombre, contrasena, fechaActual)) {
            JOptionPane.showMessageDialog(null, "Usuario " + nombre + " creado exitosamente. Bienvenido", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            // Abrir el menú principal
            // obtener usuario ya creado
            User usuarioCreado = guardarplayers.IniciarSesion(nombre, contrasena);
        MenuPrincipal entrar = new MenuPrincipal(usuarioCreado);
        entrar.setVisible(true);
        dispose();
        } else {
            // Mostrar un mensaje de error en caso de que no se pueda agregar el usuario
            JOptionPane.showMessageDialog(null, "Error al crear el usuario. Inténtalo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Limpiar los campos de entrada después de la creación
        TextoNombre.setText("");
        Textocontrasena.setText("");
        fechaIngresoField.setText("");
        fechaActual = null;

        
    }
}
