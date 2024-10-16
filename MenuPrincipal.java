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
    private static User[] jugadores = new User[MAX_JUGADORES];
    private User JugadorLogueado;   // Mantiene una referencia al jugador que ha iniciado sesion en el sistema
    private UserManager usermanager;
//------------------------------------------------------------------------------------------------------------------------------------------------

//------------FRAME PRINCIPAL CONST---------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------------------------
    public MenuPrincipal(User usuario, UserManager usermanager) {

        this.JugadorLogueado = usuario;
        this.usermanager = usermanager;

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
                
                MostrarReportes();

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

                MostrarJugadoresDisponibles();

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
    
// ----AQUI SE MUESTRAN LOS JUGADORES DISPONIBLES-----------------------------------------------------------------------------------------------
    private void MostrarJugadoresDisponibles(){
        
        User[] jugadores = usermanager.getUsuarios();
        int numJugadores=0;
        
        // aqui se cuenta a todos los jugadores, excluyendo al jugador logeuado
        for (User jugador : jugadores) {
            
            if(jugador != null && !jugador.equals(JugadorLogueado)){
                
                numJugadores++;
                
            }
            
        }
        
        // aqui se verifica si hay jugadores disponibles
        if(numJugadores <1){
            
            JOptionPane.showMessageDialog(null, "No hay suficientes jugadores para iniciar la partida. Por favor cree otro jugador","Informacion",JOptionPane.INFORMATION_MESSAGE);
            return;
            
        }
        
        
        // si hay jugadores disponibles, se creara la ventana
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        
        JLabel label = new JLabel("Selecciona un jugador para iniciar la partida");
        panel.add(label);
        
        JComboBox<String> comboJugadores = new JComboBox<>();
        comboJugadores.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // aqui se agregan a los jugadores 
        for (User jugadore : jugadores) {
            
            if(jugadore != null && !jugadore.equals(JugadorLogueado)){
                
                comboJugadores.addItem(jugadore.getNombre());
                
            }
            
        }
        panel.add(comboJugadores);
        
        // boton de iniciar 
        JButton iniciar = new JButton("Iniciar partida");
        iniciar.setBackground(Color.red);
        iniciar.setAlignmentX(Component.RIGHT_ALIGNMENT);
        iniciar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String OponenteSeleccionado = (String) comboJugadores.getSelectedItem();
                
                if(OponenteSeleccionado != null){
                    
                    // se inicia la partida con el JugadorLogueado y con el oponente seleccionado
                    IniciarPartida(JugadorLogueado,OponenteSeleccionado);
                    
                }
                
                

            }

        });
        panel.add(iniciar);
        
        JOptionPane.showMessageDialog(null,panel, "Jugadores disponibles",JOptionPane.PLAIN_MESSAGE);
        
    }
    
    private void IniciarPartida(User jugadorLogueado, String oponenteSeleccionado){
        
        JOptionPane.showMessageDialog(MenuPrincipal.this, "Iniciando partida entre "+jugadorLogueado.getNombre()+ "VS" +oponenteSeleccionado, "PARTIDA 1VS1",JOptionPane.INFORMATION_MESSAGE);
        
    }

//--------AQUI SE MUESTRA KA CUENTA DEL JUGADOR EN SESION------------------------------------------------------------------------------------------------------
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
    public void MostrarInformacionJugador() {

        String Mensaje = "Nombre de usuario: " + JugadorLogueado.getNombre() + "\n"
                + "Puntuacion: " + JugadorLogueado.getPuntos() + "\n"
                + "Fecha creacion cuenta: " + JugadorLogueado.getFormattedFechaIngreso() + "\n"
                + "Estado" + (JugadorLogueado.isActivo() ? "Activo" : "Inactivo");

        JOptionPane.showMessageDialog(MenuPrincipal.this, Mensaje, " Informacion del jugador ", JOptionPane.INFORMATION_MESSAGE);

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
            String NuevaContrasena = new String(TextoNuevaContraseña.getPassword());
            String ConfirmarContrasena = new String(TextoConfirmarContraseña.getPassword());

            if (!ContrasenaActual.equals(JugadorLogueado.getContrasena())) {

                JOptionPane.showMessageDialog(MenuPrincipal.this, "La contraseña actual es incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
                return;

            }

            if (!NuevaContrasena.equals(ConfirmarContrasena)) {

                JOptionPane.showMessageDialog(MenuPrincipal.this, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // se cambia la contrasena
            JugadorLogueado.setContrasena(NuevaContrasena);
            JOptionPane.showMessageDialog(MenuPrincipal.this, "La contraseña se a cambiado con " + NuevaContrasena, "Exito", JOptionPane.INFORMATION_MESSAGE);

        }

    }
//----------------AQUI SE MUESTRA PARA CERRAR LA CUENTA-----------------------------------------------------------------------------------------------------------
    public void MostrarCerrarCuenta() {

        String ContrasenaIngresada = JOptionPane.showInputDialog(null, "Ingrese su contraseña, para confirmar la eliminacion de la cuenta: ", "EscribirContraseña");

        // aquise verifica si la contrasena es correcta
        if (ContrasenaIngresada != null && ContrasenaIngresada.equals(JugadorLogueado.getContrasena())) {

            //eliminar el usaurio
            if (usermanager.EliminarUsuario(JugadorLogueado, 0)) {

                JOptionPane.showMessageDialog(MenuPrincipal.this, "Cuenta Eliminada exitosamente", "Exito", JOptionPane.INFORMATION_MESSAGE);

                dispose();
                MenuInicio m = new MenuInicio();
                m.setVisible(true);

            } else {

                JOptionPane.showMessageDialog(MenuPrincipal.this, "Error al eliminar la cuenta", "Error", JOptionPane.ERROR_MESSAGE);

            }

        } else {

            JOptionPane.showMessageDialog(MenuPrincipal.this, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);

        }

    }
    
//------------------------------AQUI SE MUESTRAN LOS REPORTES------------------------------------------------------------------------------------------------------------
    public void MostrarReportes(){
        
        this.getContentPane().removeAll();
        this.setSize(800, 600);
        
        JPanel panelReportes = new JPanel();
        panelReportes.setLayout(new GridBagLayout());
        GridBagConstraints  gbc = new GridBagConstraints();
        gbc.insets= new Insets(10,10,10,10);
        gbc.gridx=0;
        gbc.gridy=0;
        
        JButton BotonRanking = new JButton("RANKING JUGADORES");
        BotonRanking.setBackground(Color.GREEN);
        BotonRanking.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e){
                
                
                
            }
            
        });
        panelReportes.add(BotonRanking,gbc);
        gbc.gridy++;
        
        JButton BotonUltimosJuegos = new JButton("ULTIMAS PARTIDAS");
        BotonUltimosJuegos.setBackground(Color.orange);
        BotonUltimosJuegos.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e){
                
                // NUEVO PANEL PARA NUEVAS PARTIDAS 
                
            }
            
        });
        panelReportes.add(BotonUltimosJuegos,gbc);
        gbc.gridy++;
        
        JButton Volver = new JButton("VOLVER");
        Volver.setBackground(Color.MAGENTA);
        Volver.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e){
                
                MenuPrincipal.this.getContentPane().removeAll();
                MenuPrincipal.this.getContentPane().add(PanelPrincipal);
                MenuPrincipal.this.revalidate();
                MenuPrincipal.this.repaint();
                
                
            }
            
        });
        panelReportes.setBackground(Color.BLUE);
        panelReportes.add(Volver,gbc);
        gbc.gridy++;
        
        this.getContentPane().add(panelReportes);
        this.revalidate();
        this.repaint();
        
        
        
    }

}
