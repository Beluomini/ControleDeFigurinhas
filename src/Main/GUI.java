package Main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultCaret;
import myTools.Ferramentas;
import myTools.UsarGridBagLayout;

/**
 *
 * @author radames
 */
class GUI extends JFrame {
    
    int qtdFigurinhasAlbum = 0; //valor de figurinhas totais no album

    private Container cp;
    private CardLayout cardLayout = new CardLayout();
    private BorderLayout bd = new BorderLayout();
    private JPanel pnPrincipal = new JPanel(cardLayout);
    private JPanel pnBordaInferior = new JPanel(new FlowLayout());
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Date hoje = new Date();
    private JLabel lbData = new JLabel(sdf.format(hoje));
    private JLabel lbData1 = new JLabel("Hoje: ");
//---------------------------MENU-------------------------
    private JPanel pnMenu = new JPanel(bd);
    Icon img2002 = new ImageIcon(getClass().getResource("/Imagens/album2002.png"));
    private JButton bt2002 = new JButton(img2002);
    Icon img2006 = new ImageIcon(getClass().getResource("/Imagens/album2006.png"));
    private JButton bt2006 = new JButton(img2006);
    Icon img2010 = new ImageIcon(getClass().getResource("/Imagens/album2010.png"));
    private JButton bt2010 = new JButton(img2010);
    Icon img2014 = new ImageIcon(getClass().getResource("/Imagens/album2014.png"));
    private JButton bt2014 = new JButton(img2014);
    Icon img2018 = new ImageIcon(getClass().getResource("/Imagens/album2018.png"));
    private JButton bt2018 = new JButton(img2018);
    Icon img2022 = new ImageIcon(getClass().getResource("/Imagens/album2022.png"));
    private JButton bt2022 = new JButton(img2022);
    

//---------------------------ALBUM------------------------
    private JPanel pnAlbum = new JPanel(bd);
    private JPanel pnNorte = new JPanel();
    private JPanel pnOeste = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pnLeste = new JPanel(new BorderLayout());
    private JPanel pnLeste1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pnCentro = new JPanel();
    private JPanel pnSul = new JPanel(cardLayout);
    private JPanel pnSulMsg = new JPanel();
    private JPanel pnSulListagem = new JPanel(new GridLayout(1, 1));

    private JLabel lbFigurinha = new JLabel("Figurinha");
    private JLabel lbExistencia = new JLabel("Existencia: ");
    private JLabel lbFaltantes = new JLabel("Faltam: ");
    private JLabel lbImagem2002 = new JLabel();
    private JLabel lbImagem2006 = new JLabel();
    private JLabel lbImagem2010 = new JLabel();
    private JLabel lbImagem2014 = new JLabel();
    private JLabel lbImagem2018 = new JLabel();
    private JLabel lbImagem2022 = new JLabel();

    private JTextField tfFigurinha = new JTextField(5);
    private JTextField tfExistencia = new JTextField(5);
    private JTextField tfFaltantes = new JTextField(5);

    Icon imgBuscar = new ImageIcon(getClass().getResource("/Imagens/lupa.png"));
    private JButton btBuscar = new JButton(imgBuscar);
    private JButton btLimpar = new JButton(imgBuscar);
    Icon imgColar = new ImageIcon(getClass().getResource("/Imagens/colar.png"));
    private JButton btColar = new JButton(imgColar);
    Icon imgDescolar = new ImageIcon(getClass().getResource("/Imagens/descolarTransparente.png"));
    private JButton btDescolar = new JButton(imgDescolar);
    Icon imgListar = new ImageIcon(getClass().getResource("/Imagens/listar.png"));
    private JButton btListar = new JButton(imgListar);
    Icon imgSalvar = new ImageIcon(getClass().getResource("/Imagens/salvar.png"));
    private JButton btSalvar = new JButton(imgSalvar);
    Icon imgCancelar = new ImageIcon(getClass().getResource("/Imagens/cancelar.png"));
    private JButton btCancelar = new JButton(imgCancelar);
    private JButton btGravar = new JButton();
    Icon imgVoltar = new ImageIcon(getClass().getResource("/Imagens/voltarMenu.png"));
    private JButton btVoltar = new JButton(imgVoltar);
    Icon imgPerfil = new ImageIcon(getClass().getResource("/Imagens/perfil.png"));
    private JButton btPerfil = new JButton(imgPerfil);
    
    private JColorChooser colorchooser = new JColorChooser();
    private Color color;
    private JButton btMudarCor = new JButton("Personalizar");
    
    
    String[] colunas = new String[]{"Figurinhas","Album"};
    String[][] dados = new String[0][1];

    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    JScrollPane scrollList = new JScrollPane();

    private JScrollPane scrollMensagem = new JScrollPane(); //barra de rolagem

    JTextArea textAreaMsg = new JTextArea(5, 150); //campo para texto com várias linhas

    private boolean inserindo; //esta variável controla se é uma operação de INSERT ou UPDATE no botão salvar

    private Controle controle = new Controle(); //essa é a classe de processamento (controle da lista de contatos)
    private Figurinha figurinha = new Figurinha(); //ver classe contato

    DefaultCaret caret = (DefaultCaret) textAreaMsg.getCaret(); //para que haja rolagem automática do textArea
    UsarGridBagLayout usarGridBagLayoutCentro = new UsarGridBagLayout(pnCentro);

    Ferramentas fer = new Ferramentas();

    private String qualAlbum = "";
    
    //métodos auxiliares
    private void setLog(String msg) {
        textAreaMsg.append(msg + "\n");
        textAreaMsg.setCaretPosition(textAreaMsg.getDocument().getLength());
    }

    private void limparValoresDosAtributos() {
        tfFigurinha.setText("");
        tfExistencia.setText("");
        tfFaltantes.setText(String.valueOf(qtdFigurinhasAlbum - controle.qtdFigurinhasTotal()));
    }
    
    private void limparCoresDosAtributos() {
        tfFigurinha.setBackground(Color.WHITE);
        tfExistencia.setBackground(Color.WHITE);
    }

    //construtor da classe GUI
    public GUI() {
        //abrir o arquivo
        List<String> listaAuxiliar = fer.abrirArquivo("Figurinhas.txt");
        if (listaAuxiliar != null) {
            for (int i = 0; i < listaAuxiliar.size(); i++) {
                String aux[] = listaAuxiliar.get(i).split(";");
                Figurinha c = new Figurinha(Integer.valueOf(aux[0]), aux[1]);
                controle.inserir(c);
            }
        }

        //faz com que a última linha do 
        //jTextArea seja exibida
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        scrollMensagem.setViewportView(textAreaMsg);
        scrollMensagem.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//esconde a barra horizontal

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(pnPrincipal);
        cp.add(pnBordaInferior, BorderLayout.SOUTH);
        pnBordaInferior.setSize(900, 50);
        pnBordaInferior.setBackground(Color.LIGHT_GRAY);
        
        pnPrincipal.add(pnMenu, "pnMenu");
        pnPrincipal.add(pnAlbum, "pnAlbum");
        pnAlbum.add(pnNorte, BorderLayout.NORTH);
        pnAlbum.add(pnCentro, BorderLayout.CENTER);
        pnAlbum.add(pnSul, BorderLayout.SOUTH);
        
        pnBordaInferior.add(lbData1);
        pnBordaInferior.add(lbData);
//-----------------------------------------------------PAINEL MENU-----------------------------------------------------------
        pnMenu.setLayout(new GridLayout(2,3));
        
        pnMenu.add(bt2002);
        bt2002.setToolTipText("Album de 2002");
        bt2002.setBackground(Color.WHITE);
        pnMenu.add(bt2006);
        bt2006.setToolTipText("Album de 2006");
        bt2006.setBackground(Color.WHITE);
        pnMenu.add(bt2010);
        bt2010.setToolTipText("Album de 2010");
        bt2010.setBackground(Color.WHITE);
        pnMenu.add(bt2014);
        bt2014.setToolTipText("Album de 2014");
        bt2014.setBackground(Color.WHITE);
        pnMenu.add(bt2018);
        bt2018.setToolTipText("Album de 2018");
        bt2018.setBackground(Color.WHITE);
        pnMenu.add(btPerfil);
        btPerfil.setToolTipText("Perfil");
        btPerfil.setBackground(Color.WHITE);
        
        btPerfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                PERFIL perfil = new PERFIL();
                dispose();
            }
        });
        
        
        bt2002.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                cardLayout.show(pnPrincipal, "pnAlbum");
                qtdFigurinhasAlbum = 576;
                lbImagem2002 = new JLabel (img2002);
                pnLeste.add(lbImagem2002, BorderLayout.CENTER);
                qualAlbum = "2002";
            }
        });
        
        bt2006.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                cardLayout.show(pnPrincipal, "pnAlbum");
                qtdFigurinhasAlbum = 596;
                lbImagem2006 = new JLabel (img2006);
                pnLeste.add(lbImagem2006, BorderLayout.CENTER);
                qualAlbum = "2006";
            }
        });
        
        bt2010.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                cardLayout.show(pnPrincipal, "pnAlbum");
                qtdFigurinhasAlbum = 638;
                lbImagem2010 = new JLabel (img2010);
                pnLeste.add(lbImagem2010, BorderLayout.CENTER);
                qualAlbum = "2010";
            }
        });
        
        bt2014.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                cardLayout.show(pnPrincipal, "pnAlbum");
                qtdFigurinhasAlbum = 643;
                lbImagem2014 = new JLabel (img2014);
                pnLeste.add(lbImagem2014, BorderLayout.CENTER);
                qualAlbum = "2014";
            }
        });
        
        bt2018.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                cardLayout.show(pnPrincipal, "pnAlbum");
                qtdFigurinhasAlbum = 681;
                lbImagem2018 = new JLabel (img2018);
                pnLeste.add(lbImagem2018, BorderLayout.CENTER);
                qualAlbum = "2018";
            }
        });
//-----------------------------------------------------PAINEL ALBUM ----------------------------------------------------------
        pnNorte.setLayout(new GridLayout(1, 2));
        pnNorte.add(pnOeste);
        pnNorte.add(pnLeste);
        pnLeste.setLayout(new GridLayout(1, 2));
        pnLeste.add(pnLeste1);
          
        pnOeste.add(btVoltar);
        btVoltar.setToolTipText("Voltar para menu");
        pnOeste.add(lbFigurinha);
        pnOeste.add(tfFigurinha);
        pnOeste.add(btBuscar);
        btBuscar.setToolTipText("Buscar");
        pnOeste.add(btLimpar);
        btLimpar.setToolTipText("Pesquisar");
        pnOeste.add(btColar);
        btColar.setToolTipText("Colar");
        pnOeste.add(btDescolar);
        btDescolar.setToolTipText("Descolar");
        pnOeste.add(btSalvar);
        btSalvar.setToolTipText("Confirmar");
        pnOeste.add(btCancelar);
        btCancelar.setToolTipText("Cancelar");
        pnOeste.add(btListar);
        btListar.setToolTipText("Listar figurinhas");
        
        pnLeste1.add(btMudarCor);
        btMudarCor.setSize(50, 80);
        
   

        usarGridBagLayoutCentro.add(lbExistencia, tfExistencia, lbFaltantes, tfFaltantes);
        tfExistencia.setEditable(false);
        tfFaltantes.setEditable(false);
        

        UsarGridBagLayout usarGridBagLayoutSul = new UsarGridBagLayout(pnSulMsg);
        usarGridBagLayoutSul.add(new JLabel("Mensagem:"), scrollMensagem);
        pnSul.add(pnSulMsg, "pnMsg");
        pnSul.add(pnSulListagem, "pnLst");

        pnSul.setBackground(Color.red);

        btSalvar.setVisible(false);
        btCancelar.setVisible(false);
        btColar.setVisible(false);
        btDescolar.setVisible(false);
        btLimpar.setVisible(false);

        textAreaMsg.setEditable(false);
        
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                btGravar.doClick();
                // Sai   
                dispose();
            }
        });

        btGravar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                //converter a lista<contato> em lista de string
                List<String> listaStr = controle.listar();
                fer.salvarArquivo("Figurinhas.txt", listaStr);
            }
        });
//%%%%%%%%%%%%%%%%%%%%%%%%%BOTAO ALTERAR COR%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

        btMudarCor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               color =  colorchooser.showDialog(null, "Escolher Cor", null);
               pnSulListagem.setBackground(color);
                    pnSulMsg.setBackground(color);
                    pnCentro.setBackground(color);
                    pnAlbum.setBackground(color);
                    pnNorte.setBackground(color);
                    pnOeste.setBackground(color);
                    pnLeste.setBackground(color);
                    pnLeste1.setBackground(color);
               
            }
        });

//=========================BOTAO VOLTAR MENU====================================
        btVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                cardLayout.show(pnPrincipal, "pnMenu");
                pnLeste.removeAll();
                cardLayout.show(pnSul, "pnMsg");
            }
        });


// ------------------------BOTAO LIMPAR-----------------------------------------
        btLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                tfFigurinha.setEditable(true);
                btLimpar.setVisible(false);
                btBuscar.setVisible(true);
                btCancelar.setVisible(false);
                btColar.setVisible(false);
                btDescolar.setVisible(false);
                btListar.setVisible(true);
                btSalvar.setVisible(false);
            }
        });

// ------------------------BOTAO BUSCAR ----------------------------------------        
        btBuscar.addActionListener((ActionEvent e) -> {
            Date hoje = new Date();
            if (tfFigurinha.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(pnAlbum, "Pensei que você queria pesquisar algo... ");
            } else {
                try{
                    if (Integer.valueOf(tfFigurinha.getText().trim())>qtdFigurinhasAlbum || Integer.valueOf(tfFigurinha.getText().trim())<=0){
                    JOptionPane.showMessageDialog(pnAlbum, "Esta figurinha não existe nesse album");
                    btColar.setVisible(false);
                    }else{
                        try {
                            tfFigurinha.setBackground(Color.green);
                            cardLayout.show(pnSul, "pnMsg");
                            figurinha = controle.buscar(Integer.valueOf(tfFigurinha.getText()), qualAlbum);
                            if (figurinha == null) { //nao achou
                                btColar.setVisible(true);
                                btDescolar.setVisible(false);
                                tfExistencia.setText("Não");
                                tfExistencia.setBackground(Color.RED);
                                setLog("Não tem no album, pode colar se tiver...");
                            } else { //achou
                                btDescolar.setVisible(true);
                                btColar.setVisible(false);
                                tfFigurinha.setText(String.valueOf(figurinha.getFigurinha()));
                                tfExistencia.setText("Sim");
                                tfExistencia.setBackground(Color.GREEN);
                            }
                        } catch (Exception x) {
                            tfFigurinha.selectAll();
                            tfFigurinha.requestFocus();
                            tfFigurinha.setBackground(Color.red);
                            setLog("Erro no tipo de dados da chave. (" + x.getMessage() + ")");
                        }//else//else
                    }
                }catch (Exception x) {
                    JOptionPane.showMessageDialog(pnAlbum, "Aposto que na figurinha o numero está diferente (coloque um numero natural)");
                }
            }
            tfFigurinha.selectAll();
            tfFigurinha.requestFocus();
            tfFaltantes.setText(String.valueOf(qtdFigurinhasAlbum - controle.qtdFigurinhasTotal()));
            tfFigurinha.setEditable(false);
            btLimpar.setVisible(true);
            btBuscar.setVisible(false);
        });

//*********************** BOTÃO SALVAR ****************************************        
        btSalvar.addActionListener((ActionEvent e) -> {
            Date hoje = new Date();
            Figurinha contatoOriginal = figurinha; //para pesquisar na lista
            //precisamos do contato original (para depois modificar)
            if (inserindo) {
                figurinha = new Figurinha(); //criar um novo contato
            }
            //transfere os valores da GUI para classe contato
            figurinha.setFigurinha(Integer.valueOf(tfFigurinha.getText()));
            figurinha.setAlbum(qualAlbum);

            if (inserindo) { //a variavel inserindo é preenchida nos
                controle.inserir(figurinha);
                setLog("Inseriu: " + figurinha.getFigurinha());
            }

            //voltar para tela inicial
            tfFigurinha.requestFocus();
            tfFigurinha.selectAll();
            tfFigurinha.setEditable(true);
            btSalvar.setVisible(false);
            btCancelar.setVisible(false);
            btBuscar.setVisible(true);
            btLimpar.setVisible(false);
            btListar.setVisible(true);
            tfFaltantes.setText(String.valueOf(qtdFigurinhasAlbum - controle.qtdFigurinhasTotal()));
            if(Integer.valueOf(tfFaltantes.getText().trim()) == 0){
                JOptionPane.showMessageDialog(pnAlbum, "PARABÉNS, VOÇÊ COMPLETOU O ALBUM");
            }
        });

//**************** Cancelar alteração ou inclusão ********************
        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                tfFigurinha.requestFocus();
                tfFigurinha.selectAll();
                tfFigurinha.setEditable(true);
                btColar.setVisible(false);
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                btBuscar.setVisible(true);
                btLimpar.setVisible(false);
                btListar.setVisible(true);
                limparValoresDosAtributos();
                setLog("Cancelou a colagem");
                tfFaltantes.setText(String.valueOf(qtdFigurinhasAlbum - controle.qtdFigurinhasTotal()));
            }
        });

//||||||||||||||||||||||||||| BOTÃO COLAR |||||||||||||||||||||||||||||||||||
        btColar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                btColar.setVisible(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btLimpar.setVisible(false);
                btListar.setVisible(false);
                inserindo = true;
                tfExistencia.setText("Sim");
                tfExistencia.setBackground(Color.GREEN);
                setLog("Certeza que quer colar esta figurinha?");
            }
        });

//======================= LISTAR =============================================
        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                cardLayout.show(pnSul, "pnLst");
                scrollList.setPreferredSize(tabela.getPreferredSize());
                pnSulListagem.add(scrollList);
                scrollList.setViewportView(tabela);
                List<String> listaDeFigurinhas = controle.listar();//busca a lista de contatos
                String[] aux;
                colunas = new String[]{"Figurinhas","Album"};
                dados = new String[0][1];
                model.setDataVector(dados, colunas);
                for (int i = 0; i < listaDeFigurinhas.size(); i++) {
                    aux = listaDeFigurinhas.get(i).split(";");
                    String[] linha = new String[]{aux[0], aux[1]};
                    if(aux[1].equals(qualAlbum)){
                    // String[] linha = new String[]{"João", "15 ", "Masculino"};
                    model.addRow(linha);                       
                    }
                }
                limparValoresDosAtributos();
                limparCoresDosAtributos();
                btColar.setVisible(false);
                btDescolar.setVisible(false);
            }
        });
//***************************** DESCOLAR *************************************
        btDescolar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                int dialogResult = JOptionPane.showConfirmDialog(pnAlbum, "Vai descolar: "
                        + tfFigurinha.getText() + "?", "Descolar do album", NORMAL);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    controle.descolar(figurinha);
                    tfExistencia.setText("Não");
                    tfExistencia.setBackground(Color.RED);
                    setLog("Descolou: " + figurinha.getFigurinha());
                    btDescolar.setVisible(false);
                    tfFaltantes.setText(String.valueOf(qtdFigurinhasAlbum - controle.qtdFigurinhasTotal()));
                }
            }
        });

//$$$$$$$$$$$$$$ FIM DOS LISTENERS $$$$$$$$$$$$$
        // parâmetros para janela inicial
        setSize(900, 400);
        setTitle("Albuns da copa");
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
