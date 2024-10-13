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

    UserManager usuarios;
    MenuInicio menuInicio;

    private JPanel PanelPrincipal;
    private JButton JUGARXIANGQI;
    private JButton MiCuenta;
    private JButton Reportes;
    private JButton LogOut;
    
    private GuardarPlayers guardarplayers;

    private static final int MAX_JUGADORES = 100;
    private static int numJugadores = 0;
    // Es null cuando ningun jugador ha iniciado sesion
    private static User JugadorLogueado = null;   // Mantiene una referencia al jugador que ha iniciado sesion en el sistema
    private static User[] Jugadores = new User[MAX_JUGADORES];

    public MenuPrincipal() {
        
        
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

                // condiciion aqui, donde se verifica la cantidad de jugadores para iniciar una nueva partida
                if (numJugadores <= 1) {

                    int respuesta = JOptionPane.showConfirmDialog(MenuPrincipal.this, "No hay suficientes jugadores para iniciar la partida. ¿Desea crear otro jugador?", "Falta jugadores", JOptionPane.YES_OPTION);

                    if (respuesta == JOptionPane.YES_OPTION) {

                        CrearPlayer nuevoJugador = new CrearPlayer(guardarplayers);

                    } else {
                        JOptionPane.showMessageDialog(MenuPrincipal.this, "No se puede iniciar la partida sin otro jugador.");
                    }

                }

                // aqui creo un array de nombres de jugadores que no incluyen al jugador logueado
                String[] nombresDeJugadores = new String[numJugadores - 1];
                int index = 0;
                // for que recorre los nombres de los usuarios
                for (int nameJuga = 0; nameJuga < numJugadores; nameJuga++) {

                    if (!Jugadores[nameJuga].getNombre().equals(JugadorLogueado.getNombre())) {
                        nombresDeJugadores[index++] = Jugadores[nameJuga].getNombre();
                    }

                }

                // aqui un cuadrito para seleccionar a un oponente 
                String OponenteSeleccionar = (String) JOptionPane.showInputDialog(MenuPrincipal.this, "Selecciona un oponente", "Seleccionar un oponente", JOptionPane.QUESTION_MESSAGE, null, nombresDeJugadores, nombresDeJugadores[0]);

                // aqui se verifica si el jugador selecciono un oponente en la lista de jugadores 
                if (OponenteSeleccionar == null) {
                    // RECORDATORIOOOO
                    return;
                }

                // aqui busca el oponente seleccionado en la lista de Jugadores 
                User oponente = null;
                for (int buscar = 0; buscar < numJugadores; buscar++) {
                    if (Jugadores[buscar].getNombre().equals(OponenteSeleccionar)) {
                        oponente = Jugadores[buscar];
                        break;
                    }
                }

                // aqui se verifica si el oponetne es valido 
                if (oponente != null) {
                    //IniciarPartida(frame, JugadorLogueado, oponente); HACER ESTA FUNCION
                } else {
                    JOptionPane.showMessageDialog(MenuPrincipal.this, "Oponente no Valido");
                }

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

   

}
