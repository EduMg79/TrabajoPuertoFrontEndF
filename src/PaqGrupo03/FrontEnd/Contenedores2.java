package PaqGrupo03.FrontEnd;

import PaqC03.Contenedor;

import javax.swing.*;

public class Contenedores2 extends JFrame {


    private JPanel Panel2;
    private JTextField TxtNid;
    private JTextField Txtpeso;
    private JTextArea TxtDesc;
    private JTextField TxtEmpresa;
    private JTextField TxTReceptora;
    private JTextField pais;
    private JRadioButton a1RadioButton;
    private JRadioButton a2RadioButton;
    private JRadioButton a3RadioButton;
    private JCheckBox inspeccionadoEnAduanasCheckBox;
    private JLabel txtPais;

    public Contenedores2(Contenedor c) {
        setTitle("Datos Contenedor");
        setSize(600, 500);
         setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        Panel2.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setContentPane(Panel2);
        TxtNid.setText(String.valueOf(c.getNidentificador()));
        Txtpeso.setText(String.valueOf(c.getPeso()));
        TxtDesc.setText(c.getDesccontenido());
        TxtEmpresa.setText(c.getEmpresaenvio());
        TxTReceptora.setText(c.getEmpresareceptor());
        pais.setText(c.getPais());
        if(c.isInspeccionado()){
            inspeccionadoEnAduanasCheckBox.setSelected(true);
        }
        if(c.getPrioridad()==1){
            a1RadioButton.setSelected(true);
        }
        else if(c.getPrioridad()==2){
            a2RadioButton.setSelected(true);
        }
        else a3RadioButton.setSelected(true);

    }



}
