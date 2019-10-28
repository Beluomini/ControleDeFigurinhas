/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import myTools.Ferramentas;

/**
 *
 * @author Lucas
 */
public class PERFIL extends JFrame {
    
    DateTextField tfDate = new DateTextField();
    private Container cp;
    
    private JPanel pnSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pnMeio = new JPanel(new GridLayout(4, 2));
    private JPanel pnAdicional = new JPanel();
    private JPanel pnAdicional1 = new JPanel();
    
    private JLabel lbTitulo = new JLabel("VOLTAR PARA MENU (SEM SALVAR)");
    Icon imgVoltar = new ImageIcon(getClass().getResource("/Imagens/voltarMenu.png"));
    private JButton btMenu = new JButton(imgVoltar);
    private JButton btEditar = new JButton("Editar");
    
    private JLabel lbNome = new JLabel("Nome: ");
    private JTextField tfNome = new JTextField();
    private JLabel lbIdade = new JLabel("Idade: ");
    private JTextField tfIdade = new JTextField(11);
    private JLabel lbDataNasc = new JLabel("Data de Nascimento: ");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    String[] times = { "Brasil","Rússia","Irã","Coreia do Sul","Japão","Arábia","Austrália","Tunísia","Nigéria","Marrocos","Senegal","Egito","México","Costa Rica","Panamá","Uruguai","Argentina",
"Colômbia","Peru","França","Portugal","Alemanha","Sérvia","Polônia","Inglaterra","Espanha","Bélgica","Islândia","Suíça","Croácia","Suécia","Dinamarca" };
    JComboBox cbTimes = new JComboBox(times);
        
    private JLabel lbCampeao = new JLabel("Quem vai ganhar a copa?");
    private JTextField tfCampeao1 = new JTextField();
    private JButton btAtualizar = new JButton("Atualizar");
    
    ListaPerfil listaPerfil = new ListaPerfil();
    
    private List<String> perfil;
    Perfilo perfilo = new Perfilo();

    Ferramentas fer = new Ferramentas();
    
    public PERFIL() {
        List<String> listaAuxiliar = fer.abrirArquivo("Perfil.txt");
        if (listaAuxiliar != null) {
            for (int i = 0; i < listaAuxiliar.size(); i++) {
                String aux[] = listaAuxiliar.get(i).split(";");
                Perfilo c = new Perfilo(aux[0], aux[1], aux[2], aux[3]);
                listaPerfil.inserir(c);
                tfNome.setText(aux[0]);
                tfIdade.setText(aux[1]);
                tfCampeao1.setText(aux[2]);
                tfDate.setText(aux[3]);
            }
        }
        
        setSize(700, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("PERFIL");
        cp.setBackground(Color.red);
        
        cp.add(pnSuperior, BorderLayout.NORTH);
        pnSuperior.add(btMenu);
        pnSuperior.add(lbTitulo, BorderLayout.CENTER);
        pnSuperior.add(btEditar);
      
        
                    
        
        cp.add(pnMeio, BorderLayout.SOUTH);
        pnMeio.add(lbNome);
        pnMeio.add(tfNome);
        tfNome.setEditable(false);
        pnMeio.add(lbIdade);
        pnMeio.add(tfIdade);
        tfIdade.setEditable(false);
        pnMeio.add(lbDataNasc);
        pnMeio.add(tfDate);
        tfDate.setToolTipText("Clique para editar");
        pnMeio.add(pnAdicional);
        pnMeio.add(pnAdicional1);
                  
        pnAdicional.add(lbCampeao);
        pnAdicional.add(cbTimes);
        pnAdicional1.add(tfCampeao1);
        tfCampeao1.setEditable(false);
        pnAdicional1.add(btAtualizar);
       
        pnSuperior.setBackground(Color.RED);
        pnAdicional.setBackground(Color.RED);
        pnAdicional1.setBackground(Color.RED);
        pnMeio.setBackground(Color.RED);
        
        btMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI gui = new GUI();
                dispose();
            }
        });

        
        btAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {  
                try{
                    if(Float.valueOf(tfIdade.getText().trim())<=0 || Float.valueOf(tfIdade.getText().trim())%1!=0){
                            JOptionPane.showMessageDialog(pnMeio, "Coloque uma idade válida (Numeros naturais)");                   
                    }else{
                    Perfilo perfilo1 = new Perfilo(tfNome.getText().trim(), tfIdade.getText().trim(),
                    String.valueOf(cbTimes.getSelectedItem()),simpleDateFormat.format(tfDate.getDate()));
                    listaPerfil.inserir(perfilo1);
                    perfil = listaPerfil.listar();
                    fer.salvarArquivo("Perfil.txt", perfil);
                    GUI gui = new GUI();
                    dispose();
                    }
                }catch (Exception x) {
                    tfIdade.setEditable(true);
                    tfIdade.selectAll();
                    tfIdade.setText("");
                    JOptionPane.showMessageDialog(pnMeio, "Coloque uma idade válida (Numeros naturais)");
                }
            }
        });
        
        btEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaPerfil.Limpar("Perfil.txt", "");
                tfNome.setEditable(true);
                tfIdade.setEditable(true);
            }
        });
        
        setLocationRelativeTo(null);//centraliza no monitor
        setVisible(true);
    }
}
