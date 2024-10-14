/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_ajedrezchino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author royum
 */
public class MenuPrincipal extends JFrame {
//----------INSTANCIAS----------------------------------------------------------------------------------------------------------------------------
    UserManager usuarios;
    MenuInicio menuInicio;
//------------------------------------------------------------------------------------------------------------------------------------------------

    
//-----BOTONES PRINCIPALES------------------------------------------------------------------------------------------------------------------------
    private JPanel PanelPrincipal;
    private JButton JUGARXIANGQI;
    private JButton MiCuenta;
    private JButton Reportes;
    private JButton LogOut;
//------------------------------------------------------------------------------------------------------------------------------------------------
    
    
//--------------GUARDAR PLAYERS-------------------------------------------------------------------------------------------------------------------
    private GuardarPlayers guardarplayers;
    private static final int MAX_JUGADORES = 100;
    private static int numJugadores = 0;
    private  User JugadorLogueado;   // Mantiene una referencia al jugador que ha iniciado sesion en el sistema
    private UserManager usermanager;
//------------------------------------------------------------------------------------------------------------------------------------------------
    
    
//------------FRAME PRINCIPAL CONST---------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------------------------
    public MenuPrincipal(User usuario) {
        
        this.JugadorLogueado = usuario;
        

        setTitle("Menu Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);

        PanelPrincipal = new JPanel();
        PanelPrincipal.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // aqio espacio entre los botones 
        PanelPrincipal.setBackground(Color.blue);

        JUGARXIANGQI = new JButton("JUGAR XIANGQI ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        JUGARXIANGQI.setBackground(Color.cyan);
        PanelPrincipal.add(JUGARXIANGQI, gbc);

        MiCuenta = new JButton("MI CUENTA ");
        gbc.gridy = 1;
        MiCuenta.setBackground(Color.red);
        PanelPrincipal.add(MiCuenta, gbc);

        Reportes = new JButton("REPORTES");
        gbc.gridy = 2;
        Reportes.setBackground(Color.PINK);
        PanelPrincipal.add(Reportes, gbc);

        LogOut = new JButton("LOG OUT ");
        gbc.gridy = 3;
        LogOut.setBackground(Color.ORANGE);
        PanelPrincipal.add(LogOut, gbc);

        add(PanelPrincipal);
        setVisible(true);

        JUGARXIANGQI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MostrarNuevaPartida();

            }
        });

        MiCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MostrarMiCuenta();

            }
        });

        Reportes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        LogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MenuInicio menu = new MenuInicio();
                dispose();

            }
        });

    }
//---------------FIN DE FRAME PRINCIPAL-----------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------------------------
    public void MostrarNuevaPartida() {

        this.getContentPane().removeAll();
        this.setSize(800, 600);

        JPanel PanelNuevaPartida = new JPanel();
        PanelNuevaPartida.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        JButton BotonNuevaPartida = new JButton("NUEVA PARTIDA");
        BotonNuevaPartida.setBackground(Color.yellow);

        BotonNuevaPartida.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
//
//                if (numJugadores > 1) {
//                    String[] nombresDeJugadores = new String[numJugadores - 1];
//                    int index = 0;
//
//                    // for que recorre los nombres de los usuarios
//                    for (int nameJuga = 0; nameJuga < numJugadores; nameJuga++) {
//                        if (!Jugadores[nameJuga].getNombre().equals(JugadorLogueado.getNombre())) {
//                            nombresDeJugadores[index++] = Jugadores[nameJuga].getNombre();
//                        }
//                    }
//
//                    // Aquí se selecciona el oponente
//                    String OponenteSeleccionar = (String) JOptionPane.showInputDialog(
//                            MenuPrincipal.this,
//                            "Selecciona un oponente",
//                            "Seleccionar un oponente",
//                            JOptionPane.QUESTION_MESSAGE,
//                            null,
//                            nombresDeJugadores,
//                            nombresDeJugadores[0]
//                    );
//
//                    if (OponenteSeleccionar == null) {
//                        return;
//                    }
//
//                    // Se busca el oponente seleccionado
//                    User oponente = null;
//                    for (int buscar = 0; buscar < numJugadores; buscar++) {
//                        if (Jugadores[buscar].getNombre().equals(OponenteSeleccionar)) {
//                            oponente = Jugadores[buscar];
//                            break;
//                        }
//                    }
//
//                    if (oponente != null) {
//                        // IniciarPartida(frame, JugadorLogueado, oponente); // HACER ESTA FUNCION
//                    } else {
//                        JOptionPane.showMessageDialog(MenuPrincipal.this, "Oponente no válido");
//                    }
//
//                } else {
//                    JOptionPane.showMessageDialog(MenuPrincipal.this, "No hay suficientes jugadores para iniciar una partida.");
//                    int respuesta = JOptionPane.showConfirmDialog(MenuPrincipal.this, "Desea crear otro jugador?", "Faltan jugadores", JOptionPane.YES_OPTION);
//
//                    if (respuesta == JOptionPane.YES_OPTION) {
//                        CrearPlayer crear = new CrearPlayer(guardarplayers);
//                        dispose();
//                    } else {
//                        JOptionPane.showMessageDialog(MenuPrincipal.this, "No es posible iniciar la partida sin otro jugador", "Error", JOptionPane.INFORMATION_MESSAGE);
//                    }
//
//                }

            }

        });

        JButton BotonVolver = new JButton("VOLVER");
        BotonVolver.setBackground(Color.cyan);

        BotonVolver.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                // aqui accion para volver al menuPrinpal, sin cambiar de frame
                MenuPrincipal.this.getContentPane().removeAll(); // elimina el contenido actual (PanelNuevaPartida)
                MenuPrincipal.this.getContentPane().add(PanelPrincipal); // con esto vuelve a agregar el PanelPrincipal
                MenuPrincipal.this.revalidate(); // con este refresca el frame para aplicar los cambios
                MenuPrincipal.this.repaint();
                //Estos metodos aseguran que los cambios en la interfaz gráfica sean reflejados inmediatamente,
                //refrescando la ventana.

            }

        });
        // esto me ayuda agregar los botones 
        PanelNuevaPartida.add(BotonNuevaPartida, gbc);
        gbc.gridy = 1;
        PanelNuevaPartida.add(BotonVolver, gbc);
        PanelNuevaPartida.setBackground(Color.BLUE);

        // sin esto se mama el frame
        this.getContentPane().add(PanelNuevaPartida);
        this.revalidate();
        this.repaint();

    }

    public void MostrarMiCuenta() {

        this.getContentPane().removeAll();
        this.setSize(800, 600);

        JPanel PanelMiCuenta = new JPanel();
        PanelMiCuenta.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        JButton BotonInformacionJugador = new JButton("Ver Mi Información ");
        BotonInformacionJugador.setBackground(Color.PINK);
        BotonInformacionJugador.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                MostrarInformacionJugador();

            }

        });
        PanelMiCuenta.add(BotonInformacionJugador, gbc);
        gbc.gridy++;

        JButton BotonCambiarContrasena = new JButton("Cambiar Password ");
        BotonCambiarContrasena.setBackground(Color.GREEN);
        BotonCambiarContrasena.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                MostrarCambiarContrasena();

            }

        });
        PanelMiCuenta.add(BotonCambiarContrasena, gbc);
        gbc.gridy++;

        JButton CerrarCuenta = new JButton("Cerrar mi Cuenta ");
        CerrarCuenta.setBackground(Color.magenta);
        CerrarCuenta.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                MostrarCerrarCuenta();

            }

        });
        PanelMiCuenta.add(CerrarCuenta, gbc);
        gbc.gridy++;

        JButton Volver = new JButton("Volver");
        Volver.setBackground(Color.yellow);
        Volver.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                MenuPrincipal.this.getContentPane().removeAll();
                MenuPrincipal.this.getContentPane().add(PanelPrincipal); // con esto vuelve a agregar el PanelPrincipal
                MenuPrincipal.this.revalidate(); // con este refresca el frame para aplicar los cambios
                MenuPrincipal.this.repaint();
                //Estos metodos aseguran que los cambios en la interfaz gráfica sean reflejados inmediatamente,
                //refrescando la ventana.

            }

        });
        PanelMiCuenta.add(Volver, gbc);
        gbc.gridy++;
        PanelMiCuenta.setBackground(Color.BLUE);

        // sin esto se mama el frame xd.
        this.getContentPane().add(PanelMiCuenta);
        this.revalidate();
        this.repaint();

    }
    
//---------INFORMACION JUAGADOR-------------------------------------------------------------------------------------------------------------------------
    public void MostrarInformacionJugador(){
        
        
        
        String Mensaje = "Nombre de usuario: "+JugadorLogueado.getNombre() + "\n"
                + "Puntuacion: " + JugadorLogueado.getPuntos() + "\n"
                + "Fecha creacion cuenta: " + JugadorLogueado.getFormattedFechaIngreso() + "\n"
                + "Estado" + (JugadorLogueado.isActivo() ? "Activo" : "Inactivo");
        
        JOptionPane.showMessageDialog(MenuPrincipal.this, Mensaje," Informacion del jugador ",JOptionPane.INFORMATION_MESSAGE);
        
    }
    
//--------CAMABIAR CONTRASEÑA---------------------------------------------------------------------------------------------------------------------------
    public void MostrarCambiarContrasena() {

        JPanel PanelContrasena = new JPanel(new GridLayout(3, 2, 10, 10));

        JLabel EtiquetaContrasena = new JLabel("Contraseña Actual: ");
        JPasswordField TextoContrasenaActual = new JPasswordField();

        JLabel EtiquetaNuevaContraseña = new JLabel("Nueva Contraseña:");
        JPasswordField TextoNuevaContraseña = new JPasswordField();

        JLabel EtiquetaConfirmarContraseña = new JLabel("Confirmar Nueva Contraseña:");
        JPasswordField TextoConfirmarContraseña = new JPasswordField();
        
        PanelContrasena.add(EtiquetaContrasena);
        PanelContrasena.add(TextoContrasenaActual);
        PanelContrasena.add(EtiquetaNuevaContraseña);
        PanelContrasena.add(TextoNuevaContraseña);
        PanelContrasena.add(EtiquetaConfirmarContraseña);
        PanelContrasena.add(TextoConfirmarContraseña);

        // aqui un cuadro para cambiar de contra
        int resultado = JOptionPane.showConfirmDialog(MenuPrincipal.this, PanelContrasena, "Cambiar Contraseña", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (resultado == JOptionPane.OK_OPTION) {
            
            String ContrasenaActual = new String(TextoContrasenaActual.getPassword());
            String NuevaContrasena = new String (TextoNuevaContraseña.getPassword());  
            String ConfirmarContrasena = new String(TextoConfirmarContraseña.getPassword());

            if(!ContrasenaActual.equals(JugadorLogueado.getContrasena())){
                
                JOptionPane.showMessageDialog(MenuPrincipal.this, "La contraseña actual es incorrecta","Error",JOptionPane.ERROR_MESSAGE);
                return;
                
            }
            
            if(!NuevaContrasena.equals(ConfirmarContrasena)){
                
                JOptionPane.showMessageDialog(MenuPrincipal.this, "Las contraseñas no coinciden","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // se cambia la contrasena
            JugadorLogueado.setContrasena(NuevaContrasena);
            JOptionPane.showMessageDialog(MenuPrincipal.this, "La contraseña se a cambiado con "+NuevaContrasena,"Exito",JOptionPane.INFORMATION_MESSAGE);
            
        }

    }
    
    public void MostrarCerrarCuenta(){
        
        int resultado = JOptionPane.showConfirmDialog(MenuPrincipal.this, "¿Estas seguro de que quieres cerrar tu cuenta?","Confirmar cierre de cuenta",JOptionPane.YES_OPTION);
        if(resultado == JOptionPane.YES_OPTION){
            // aqui buscamos el indice del jugador en el array de jugadores 
            User[] Jugadores = usermanager.getUsuarios();
            int indiceJugador = -1;
            for (int i = 0; i < numJugadores; i++) {
                if(Jugadores[i].getNombre().equals(JugadorLogueado.getNombre())){
                    
                    indiceJugador=i;
                    break;
                    
                }
                
            }
            // aqui se verifica si el jugador fu encotradro
            if(indiceJugador != -1){
                
                // aqui se mueve los jugadores siguientes para cubrir el espacio del jugador eliminado
                for (int mover = indiceJugador; mover < numJugadores-1; mover++) {
                    Jugadores[mover] = Jugadores[mover+1];// mover los jugadores
                }
                
                // aqui se elimina la ultima referencia
                Jugadores[numJugadores-1]=null;
                numJugadores--;// se redue el numero de jugadores
                JugadorLogueado=null; // aqui se limpia la referencia al jugador actual
                
                JOptionPane.showMessageDialog(MenuPrincipal.this, "La cuenta a sido eliminada con exito","Exito",JOptionPane.INFORMATION_MESSAGE);
                MenuInicio m = new MenuInicio();
                m.setVisible(true);
                dispose();
                
            } else {
                JOptionPane.showMessageDialog(MenuPrincipal.this, "Error: jugador no encontrador","Error",JOptionPane.ERROR_MESSAGE);
            }
            
        } else{
            JOptionPane.showMessageDialog(MenuPrincipal.this, "Opcion cancelada","Cancelado",JOptionPane.ERROR_MESSAGE);
        }
        
    }

}

