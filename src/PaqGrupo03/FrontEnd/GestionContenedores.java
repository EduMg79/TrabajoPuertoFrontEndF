package PaqGrupo03.FrontEnd;

import PaqC03.Contenedor;
import PaqC03.Hub;
import PaqC03.Puerto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class GestionContenedores extends JFrame {

    public GestionContenedores(){
        setTitle("Contenedores ");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setContentPane(Contenedores);
        Contenedores.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        try {
           FileInputStream fis = new FileInputStream("puerto.dat");
          ObjectInputStream  entrada = new ObjectInputStream(fis);
            p = (Puerto) entrada.readObject(); //es necesario el casting
            fis.close(); // no es obligatorio pero es recomendable
            entrada.close(); // no es obligatorio pero es recomendable
        } catch (Exception e) {
            //Si el fichero no existe y aparece un error se crea el Puerto con el constructor por defecto
            p = new Puerto();

        }

        Hub h=new Hub();
        TxtEmpresa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }


        });
        //BOTONES DE PRIORIDAD
        a1RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (a1RadioButton.isSelected()){
                    JOptionPane.showMessageDialog(null,"Has seleccionado prioridad 1");
                    a2RadioButton.setEnabled(false);
                    a3RadioButton.setEnabled(false);
                }
                else {
                    a2RadioButton.setEnabled(true);
                    a3RadioButton.setEnabled(true);
                }
            }
        });
        a2RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (a2RadioButton.isSelected()){
                    JOptionPane.showMessageDialog(null,"Has seleccionado prioridad 2");
                    a1RadioButton.setEnabled(false);
                    a3RadioButton.setEnabled(false);
                }
                else {
                    a1RadioButton.setEnabled(true);
                    a3RadioButton.setEnabled(true);
                }

            }
        });
        a3RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (a3RadioButton.isSelected()){
                    JOptionPane.showMessageDialog(null,"Has seleccionado prioridad 3");
                    a1RadioButton.setEnabled(false);
                    a2RadioButton.setEnabled(false);
                }
                else {
                    a2RadioButton.setEnabled(true);
                    a1RadioButton.setEnabled(true);
                }
            }
        });
        //CHECK BOX DE INSPECCIONADO EN ADUANAS

        inspeccionadoEnAduanasCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (inspeccionadoEnAduanasCheckBox.isSelected()){
                    JOptionPane.showMessageDialog(null,"el contenedor esta inspeccionado en aduanas");

                }

            }
        });
        //APILAR CONTENEDOR
        apilarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int prioridad;
                if (a1RadioButton.isSelected()){
                    prioridad=1;
                } else if (a2RadioButton.isSelected()) {
                    prioridad=2;

                }
                else prioridad=3;
                int nhub;
                if (Hub1.isSelected()){
                    nhub=0;
                } else if (hub2.isSelected()) {
                    nhub=1;

                } else  {
                    nhub=2;
                }



                int id = Integer.parseInt(TxtNidentificacion.getText());
                int peso = Integer.parseInt(txtNpeso.getText()); //Controles de entero
                String s = (String)pais.getSelectedItem();
                Contenedor c1 = new Contenedor( id,peso,s, prioridad, TxtDescr.getText(), TxtEmpresa.getText(), TxtReceptora.getText());
                if (inspeccionadoEnAduanasCheckBox.isSelected()){
                    c1.setInspeccionado(true);
                }
                else c1.setInspeccionado(false);

                p.apilar(c1,nhub);
                if (p.ocupadocolumna(nhub)){
                    JOptionPane.showMessageDialog(null,"No hay espacio");
                }
                else if (p.ocupadocolumna2(nhub)){
                    JOptionPane.showMessageDialog(null,"No hay espacio");

                }
                else {
                    JOptionPane.showMessageDialog(null,"Has apilado un contenedor");
                }



                TxtEstado.setText(p.toString(nhub));
                FileOutputStream fos = null;
                ObjectOutputStream salida = null;
                try {
                    fos = new FileOutputStream("puerto.dat");
                    salida = new ObjectOutputStream(fos);
                    salida.writeObject(p);
                    fos.close();
                    salida.close();
                } catch (Exception ex) {
                    // si aparece un error se muestra en pantalla el tipo de error
                    ex.printStackTrace();
                }



            }
        });
        //DESAPILAR CONTENEDOR

        desapilarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int nhub;

                    if (Hub1.isSelected()){
                        nhub=0;
                    } else if (hub2.isSelected()) {
                        nhub=1;

                    } else nhub=2;

                    p.desapilar(nhub,Integer.parseInt(nºDeColumnaTextField.getText()));
                    JOptionPane.showMessageDialog(null,"Has desapilado un contenedor");

                FileOutputStream fos = null;
                ObjectOutputStream salida = null;
                try {
                    fos = new FileOutputStream("puerto.dat");
                    salida = new ObjectOutputStream(fos);
                    salida.writeObject(p);
                    fos.close();
                    salida.close();
                } catch (Exception ex) {
                    // si aparece un error se muestra en pantalla el tipo de error
                    ex.printStackTrace();
                }
            }


        });
        //Control de enteros
        TxtNidentificacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int dato = Integer.parseInt(TxtNidentificacion.getText());
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null,"El dato no es un entero, vuelve a teclearlo");
                }
            }
        });

        txtNpeso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double dato = Double.parseDouble(txtNpeso.getText());
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null,"El dato no es un entero o doble, vuelve a teclearlo");
                }
            }
        });
        //MOSTRAR DATOS CONTENEDOR
        mostrarDatosContenedorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int nhub;
                if(Hub1.isSelected()){
                    nhub=0;
                }
                else if(hub2.isSelected()){
                    nhub=1;
                }
                else if (Hub3.isSelected()){
                    nhub=2;
                }
                else{
                    nhub=3;
                }
                int id=Integer.parseInt(IDContenedorTextField.getText()); //Control de entero

                Contenedores2 contenedores2= new Contenedores2(p.muestraDatosid(nhub,id)); //paso por parametro de el contenedor de la id pasada por teclado
            }
        });
        //BOTONES DE HUB

        Hub1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Hub1.isSelected()){
                    hub2.setEnabled(false);
                    Hub3.setEnabled(false);
                    TxtEstado.setText(p.toString(0)); //Cada vez que se pulse el boton del hub se muestra el estado del hub del puerto
                    JOptionPane.showMessageDialog(null,"Has seleccionado el Hub 1");
                }
                else{
                    hub2.setEnabled(true);
                    Hub3.setEnabled(true);
                }

            }
        });
        hub2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hub2.isSelected()){
                    Hub1.setEnabled(false);
                    Hub3.setEnabled(false);
                    TxtEstado.setText(p.toString(1));
                    JOptionPane.showMessageDialog(null,"Has seleccionado el Hub 2");
                }
                else{
                    Hub1.setEnabled(true);
                    Hub3.setEnabled(true);
                }

            }


        });
        Hub3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Hub3.isSelected()){
                    hub2.setEnabled(false);
                    Hub1.setEnabled(false);
                    TxtEstado.setText(p.toString(2));
                    JOptionPane.showMessageDialog(null,"Has seleccionado el Hub 3");
                }
                else{
                    hub2.setEnabled(true);
                    Hub1.setEnabled(true);
                }

            }


        });


        cuantosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int nhub;
                if (Hub1.isSelected()){
                    nhub=0;
                } else if (hub2.isSelected()) {
                    nhub=1;
                }
                else nhub=2;

                String s = (String)pais2.getSelectedItem();
                JOptionPane.showMessageDialog(null,"Hay "+(p.ContadorPaises(s,nhub))+" contenedores de "+s);
            }

        });

    }


    public static void main(String[] args) {
        new GestionContenedores();
    }

    private JPanel Contenedores;
    private JTextField TxtNidentificacion;
    private JTextField txtNpeso;
    private JTextArea TxtDescr;
    private JLabel Nidentificacion;
    private JLabel Peso;
    private JLabel Empresa;
    private JTextField TxtEmpresa;
    private JTextField TxtReceptora;
    private JLabel EmpresaReceptora;
    private JComboBox pais;
    private JLabel Prioridad;
    private JRadioButton a1RadioButton;
    private JRadioButton a2RadioButton;
    private JRadioButton a3RadioButton;
    private JCheckBox inspeccionadoEnAduanasCheckBox;
    private JTextArea TxtEstado;
    private JLabel Estado;
    private JLabel operaciones;
    private JLabel Pais;
    private JLabel Contenido;
    private JButton apilarButton;
    private JButton desapilarButton;
    private JTextField nºDeColumnaTextField;
    private JButton mostrarDatosContenedorButton;
    private JTextField IDContenedorTextField;
    private JButton cuantosButton;
    private JComboBox pais2;
    private JLabel Icono;
    private JRadioButton Hub1;
    private JRadioButton hub2;
    private JRadioButton Hub3;

    Puerto p;



}
