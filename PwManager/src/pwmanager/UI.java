package pwmanager;

import com.itextpdf.text.DocumentException;
import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;
import org.json.JSONException;
import pwmanager.utils.*;

public class UI extends javax.swing.JFrame {

    public Image imgc = Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/intro.png"));
    public Image searchbtc = Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/search.png"));
    public Image aboutimg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/about.png"));
    public Image connecti = Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/conect.png"));
    public Image configur = Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/config.png"));
    public Image relatory = Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/report.png"));
    Icon image2 = new ImageIcon(imgc);
    Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
    JPopupMenu jpopContas = new JPopupMenu();
    JPopupMenu jpopGM = new JPopupMenu();
    Image imgcrack = Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/hash.png"));
    Image imgforc = Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/force.png"));
    Image imgaddacc = Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/add.png"));
    Image imgdelacc = Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/delete.png"));
    Image imgcopy = Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/copy.png"));
    public Image imageapp = Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/app.png"));
    Image imagesobre = Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/sobre.png"));
    Image online = Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/online.png"));
    Image gm = Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/gm.png"));
    Image cash = Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/cash.png"));
    Image contas = Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/contas.png"));
    Image refreshimg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/refresh.png"));
    Image imgperm = Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/perm.png"));
    Image rel = Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/relatorio.png"));
    Image apimg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/api.png"));
    Image licencaimg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/licenca.png"));
    Icon Licencaico = new ImageIcon(licencaimg);
    Icon apico = new ImageIcon(apimg);
    Icon icocrack = new ImageIcon(imgcrack);
    Icon aboutico = new ImageIcon(aboutimg);
    Icon icoforc = new ImageIcon(imgforc);
    Icon icorelatory = new ImageIcon(relatory);
    Icon icoconection = new ImageIcon(connecti);
    Icon icoconfig = new ImageIcon(configur);
    Icon icoaddacc = new ImageIcon(imgaddacc);
    Icon icodelacc = new ImageIcon(imgdelacc);
    Icon icocopy = new ImageIcon(imgcopy);
    Icon icoperm = new ImageIcon(imgperm);
    Icon searchico = new ImageIcon(searchbtc);
    JMenuItem ithashcrack = new JMenuItem("Quebrar senha", icocrack);
    JMenuItem itforcsenha = new JMenuItem("Forçar senha", icoforc);
    JMenuItem itaddacc = new JMenuItem("Cadastrar conta", icoaddacc);
    JMenuItem itdelacc = new JMenuItem("Deletar conta", icodelacc);
    JMenuItem itcopyid = new JMenuItem("Copiar id", icocopy);
    JMenuItem itcopylog = new JMenuItem("Copiar login", icocopy);
    JMenuItem itcopyemail = new JMenuItem("Copiar email", icocopy);
    Boolean isonline = false;
    MySQLcon conector;
    Connection con = null;
    Boolean dbclean = false;
    Statement st = null;
    ResultSet rs = null;
    Icon iconecash = new ImageIcon(cash);
    JMenuItem itaddcash = new JMenuItem("Adicionar gold", iconecash);
    Icon iconeonline;
    Icon iconegm;
    Icon iconecontas;
    public Icon iconeapp;
    Icon iconesobre;
    public mysqlconfig sqlconfig;
    public jsonWrite gravadados;
    public jsonRead recuperadados;
    public DefaultTableModel tbContas;
    public DefaultTableModel tbOnlines;
    public DefaultTableModel tbGM;
    public cadastro frontcadastro;
    public UI ui = this;
    public int idselecionado;
    public String contaselecionada;
    public String senhaselecionado;
    Boolean finded = false;
    Icon GMcon = new ImageIcon(gm);
    Icon refreshicon = new ImageIcon(refreshimg);
    JMenuItem itbegm = new JMenuItem("Tornar GM", GMcon);
    JMenuItem manageGm = new JMenuItem("Gerenciar GM", icoperm);
    JMenuItem removegm = new JMenuItem("Remover GM", GMcon);
    public ApiManager apimanager;

    public UI() throws IOException, JSONException, ClassNotFoundException, Exception {
        JOptionPane.showMessageDialog(null, "", "", 1, image2);
        initComponents();
        jMenu6.setIcon(aboutico);
        jMenu5.setIcon(searchico);
        jMenu1.setIcon(icoconfig);
        jMenu3.setIcon(icorelatory);
        jMenu2.setIcon(icoconection);
        jpopContas.add(itaddacc);
        jpopContas.add(itdelacc);
        jpopContas.add(itaddcash);
        jpopContas.add(itforcsenha);
        jpopContas.add(ithashcrack);
        jpopContas.add(itbegm);
        jpopContas.add(itcopyid);
        jpopContas.add(itcopylog);
        jpopContas.add(itcopyemail);
        jpopGM.add(manageGm);
        jpopGM.add(removegm);
        jTable1.setSelectionMode(0);
        jTable2.setSelectionMode(0);
        jTable3.setSelectionMode(0);
        setVisible(true);
        conector = new MySQLcon();
        sqlconfig = new mysqlconfig();
        iconeapp = new ImageIcon(imageapp);
        setIconImage(imageapp);
        iconecontas = new ImageIcon(contas);
        iconegm = new ImageIcon(gm);
        iconeonline = new ImageIcon(online);
        iconesobre = new ImageIcon(imagesobre);
        jTabbedPane1.setIconAt(0, iconecontas);
        jTabbedPane1.setIconAt(1, iconeonline);
        jTabbedPane1.setIconAt(2, iconegm);
        jTabbedPane1.setIconAt(3, apico);
        recuperadados = new jsonRead(sqlconfig);
        jButton1.setIcon(refreshicon);
        jButton2.setIcon(iconecash);
        jButton5.setIcon(iconecash);
        jButton3.setIcon(refreshicon);
        jButton4.setIcon(refreshicon);
        // listeners
        itaddacc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isonline) {
                    frontcadastro = new cadastro(con, sqlconfig, ui);
                }
            }
        });

        itforcsenha.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (isonline) {
                    idselecionado = Integer.valueOf(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 0)));
                    contaselecionada = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 1));
                    new forcasenha(contaselecionada, idselecionado, con, ui, sqlconfig);
                }
            }
        });

        itbegm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (isonline) {
                    int resultadobotao = JOptionPane.YES_NO_OPTION;
                    resultadobotao = JOptionPane.showConfirmDialog(rootPane, "Deseja tornar jogador GM ?", "Confirmar", resultadobotao);
                    if (resultadobotao == 0) {
                        idselecionado = Integer.valueOf(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 0)));
                        contaselecionada = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 1));
                        try {
                            addGm(contaselecionada, idselecionado);
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(rootPane, "Não foi possivel elevar permissões a GM\nRazão: " + ex.getMessage(), "Erro!", 0);
                        }
                    }
                }
            }
        });

        itdelacc.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (isonline) {
                    contaselecionada = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 1));
                    idselecionado = Integer.valueOf(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 0)));

                    int resultadobotao = JOptionPane.YES_NO_OPTION;
                    resultadobotao = JOptionPane.showConfirmDialog(rootPane, "Tem certeza que deseja deletar a conta ''" + contaselecionada + "'' ?", "Confirmar", resultadobotao);
                    if (resultadobotao == 0) {
                        try {
                            DeletaConta(contaselecionada, idselecionado);
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(rootPane, "Não foi possivel deletar a conta : " + ex.getMessage(), "Erro", 0);
                        }

                    }
                }
            }
        });
        itcopyemail.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String email = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 3));
                StringSelection selection = new StringSelection(email);
                clip.setContents(selection, null);
            }
        });

        itcopyid.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String id = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 0));
                StringSelection selection = new StringSelection(id);
                clip.setContents(selection, null);
            }
        });

        itcopylog.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String login = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 1));
                StringSelection selection = new StringSelection(login);
                clip.setContents(selection, null);
            }
        });
        ithashcrack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isonline) {
                    contaselecionada = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 1));
                    senhaselecionado = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 2));
                    try {
                        new GetHash(senhaselecionado, contaselecionada, sqlconfig);
                    } catch (IOException ex) {
                        Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        itaddcash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                idselecionado = Integer.valueOf(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 0)));
                contaselecionada = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 1));
                senhaselecionado = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 2));

                new cashsend(contaselecionada, idselecionado, con, ui);

            }
        });

        removegm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (isonline) {
                    idselecionado = Integer.valueOf(String.valueOf(jTable3.getValueAt(jTable3.getSelectedRow(), 0)));
                    contaselecionada = String.valueOf(jTable3.getValueAt(jTable3.getSelectedRow(), 1));
                    try {
                        removeGm(contaselecionada, idselecionado);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(removegm, "Não foi possível completar a operação\nRazão:" + ex.getMessage(), "Erro !", 0);
                    }
                }
            }

        });

        manageGm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (isonline) {
                    idselecionado = Integer.valueOf(String.valueOf(jTable3.getValueAt(jTable3.getSelectedRow(), 0)));
                    contaselecionada = String.valueOf(jTable3.getValueAt(jTable3.getSelectedRow(), 1));
                    try {
                        new editGm(contaselecionada, idselecionado, con, ui);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(removegm, "Não foi possivel completar a operação\nRazão: " + ex.getMessage(), "Erro !", 0);
                    }

                }
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PwManager [Bola] - ");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jTabbedPane1.setName("Contas"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Login", "Senha(hash)", "Email"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel4.setText("Total de contas");

        jLabel5.setText("0");

        jButton3.setText("Atualizar Lista");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setText("Enviar gold a todas as contas");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(535, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(20, 20, 20))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)))
        );

        jTabbedPane1.addTab("Contas", jPanel1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Login","Ultimo Login"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jLabel6.setText("Total online");

        jLabel7.setText("0");

        jButton1.setText("Atualizar Lista");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Enviar gold a todos online");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(25, 25, 25))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)))
        );

        jTabbedPane1.addTab("Online", jPanel2);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Login", "Ultimo Login"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        jButton4.setText("Atualizar Lista");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel8.setText("Total GM");

        jLabel9.setText("0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addGap(28, 28, 28))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)))
        );

        jTabbedPane1.addTab("GameMaster", jPanel3);

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton8.setText("Global");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Flood Global");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton6.setText("Renomear");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Resetar Banco");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton10.setText("Casamento");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("Banir/Mutar");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setText("Gerencia TW");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(233, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(234, 234, 234))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(162, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Serviços", jPanel4);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("MySQL:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("offline");

        jLabel10.setText("(c) Bola | Skype para contato e encomendas : adrianolls");

        jLabel11.setText("API:");

        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setText("offline");

        jMenuBar2.setBorderPainted(false);
        jMenuBar2.setFocusable(false);
        jMenuBar2.setRequestFocusEnabled(false);

        jMenu1.setText("Configuracao");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu1MousePressed(evt);
            }
        });
        jMenuBar2.add(jMenu1);

        jMenu2.setText("Conexão");

        jMenuItem1.setText("Conectar");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem1MousePressed(evt);
            }
        });
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);
        jMenu2.add(jSeparator1);

        jMenuItem2.setText("Desconectar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar2.add(jMenu2);

        jMenu5.setText("Pesquisar");
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu5MousePressed(evt);
            }
        });

        jMenuItem3.setText("Conta");
        jMenuItem3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem3MousePressed(evt);
            }
        });
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem3);
        jMenu5.add(jSeparator2);

        jMenuItem4.setText("Personagem");
        jMenu5.add(jMenuItem4);

        jMenuBar2.add(jMenu5);

        jMenu3.setText("Relatório de doações");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu3MousePressed(evt);
            }
        });
        jMenuBar2.add(jMenu3);

        jMenu6.setText("Sobre");
        jMenu6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu6MousePressed(evt);
            }
        });
        jMenuBar2.add(jMenu6);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(22, 22, 22)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addGap(22, 22, 22))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Contas");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public JRootPane getJr() {
        return this.rootPane;
    }

    private void jMenu6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu6MousePressed
        javax.swing.JOptionPane.showMessageDialog(rootPane, "Desenvolvido por (c) Bola\nVersão de compilação: 1.1\nInicio do desenvolvimento: 07/04/2015\nConclusão de projeto inicial: 14/04/2015\nSkype: adrianolls", "Sobre PwManager", 1, iconeapp);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu6MousePressed

    private void jMenu1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MousePressed
        if (isonline == false) {
            new mysqlfront(sqlconfig, this).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Encerre a sessão para alterar configurações");
        }

    }//GEN-LAST:event_jMenu1MousePressed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        if (isonline == true) {
            con = null;
            JOptionPane.showMessageDialog(rootPane, "Desconectado com sucesso!"); // caso a conexão funcione é exibido.
            jLabel3.setText("offline");
            jLabel3.setForeground(Color.red);
            jLabel12.setText("offline");
            jLabel12.setForeground(Color.red);
            isonline = false;
            apimanager = null;
        } else {
            JOptionPane.showMessageDialog(rootPane, "Sessão não encontrada");
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MousePressed

    }//GEN-LAST:event_jMenuItem1MousePressed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if (isonline == false) {
            try {
                con = MySQLcon.conect(sqlconfig);
                if (con != null) {
                    jLabel3.setText("online");
                    jLabel3.setForeground(Color.green);
                    isonline = true;
                    //SQL Way
                    tbContas = new DefaultTableModel() {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    };
                    jTable1.setModel(tbContas);
                    jTable1.setComponentPopupMenu(jpopContas);

                    tbContas.addColumn("Id");
                    tbContas.addColumn("Login");
                    tbContas.addColumn("Senha(hash)");
                    tbContas.addColumn("Email");

                    String query = "SELECT ID, name, passwd, email FROM users";
                    st = con.createStatement();
                    rs = st.executeQuery(query);
                    while (rs.next()) {
                        String[] contasStr = new String[]{String.valueOf(rs.getInt("ID")), rs.getString("name"), rs.getString("passwd"), rs.getString("email")};
                        tbContas.addRow(contasStr);
                    }

                    tbOnlines = new DefaultTableModel() {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    };
                    tbOnlines.addColumn("ID");
                    tbOnlines.addColumn("Login");
                    tbOnlines.addColumn("Ultimo Login");
                    jTable2.setModel(tbOnlines);

                    String queryonline = "SELECT * FROM pw.users INNER JOIN pw.point ON users.id = point.uid and point.zoneid=1";
                    rs = st.executeQuery(queryonline);
                    while (rs.next()) {
                        String[] contasStronline = new String[]{String.valueOf(rs.getInt("ID")), rs.getString("name"), rs.getString("lastlogin")};
                        tbOnlines.addRow(contasStronline);
                    }
                    jLabel7.setText(String.valueOf(jTable2.getRowCount()));

                    // Tabela GM
                    tbGM = new DefaultTableModel() {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    };

                    tbGM.addColumn("ID");
                    tbGM.addColumn(("Login"));
                    tbGM.addColumn("Ultimo Login");

                    new refreshJtableGM(con, ui);
                    jTable3.setComponentPopupMenu(jpopGM);
                    jTable3.setModel(tbGM);

                    if (jTable1.getRowCount() == 0) {
                        String[] contasStr = new String[]{null, null, null, null};
                        tbContas.addRow(contasStr);
                        dbclean = true;
                    }

                    if (dbclean == false) {
                        jLabel5.setText(String.valueOf(jTable1.getRowCount()));
                        JOptionPane.showMessageDialog(null, "Conectado ao MySQL com sucesso!");
                    } else {
                        jLabel5.setText(String.valueOf(0));
                        JOptionPane.showMessageDialog(null, "Conectado ao MySQL com sucesso!");
                    }
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                apimanager = new ApiManager(sqlconfig, ui);
                                if (apimanager.isvalidapi) {
                                    JOptionPane.showMessageDialog(rootPane, "Conectado a API com sucesso !", "Sucesso !", 1);
                                    jLabel12.setText("online");
                                    jLabel12.setForeground(Color.green);
                                } else {
                                    JOptionPane.showMessageDialog(rootPane, "Senha para API incorreta\nServiços da API desativados !", "Erro !", 0);
                                }
                            } catch (NoSuchAlgorithmException | IOException | JSONException ex) {
                                JOptionPane.showMessageDialog(rootPane, "Não foi possível conectar-se à API ou a senha está incorreta\nServiços da API desativados !", "Erro !", 0);
                            } catch (NullPointerException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }.start();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Encerre uma sessão para iniciar outra !", "Erro !", 0);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenu5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MousePressed

    }//GEN-LAST:event_jMenu5MousePressed

    private void jMenu3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MousePressed
        if (isonline) {
            try {
                new GerarPDF(con);
                JOptionPane.showMessageDialog(rootPane, "Relatório gerado\nRelatorio_Doações.pdf", "Sucesso !", 1);
            } catch (DocumentException | IOException ex) {
                JOptionPane.showMessageDialog(rootPane, "Não foi possivel gerar relatório", "Erro !", 0);

            } catch (SQLException ex) {
                Logger.getLogger(UI.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Ferramenta offline", "Erro !", 0);
        }
    }//GEN-LAST:event_jMenu3MousePressed

    private void jMenuItem3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem3MousePressed
        if (isonline) {
            String pesquisa = JOptionPane.showInputDialog(rootPane, "Insira o ID da conta ou Login desejado", "Pesquisar", 1);
            if (pesquisa != null) {
                int aba = jTabbedPane1.getSelectedIndex();
                localizarID(pesquisa, aba);
            }
            finded = false;
        } else {
            JOptionPane.showMessageDialog(rootPane, "Ferramenta offline", "Erro !", 0);
        }
    }//GEN-LAST:event_jMenuItem3MousePressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (isonline) {
            new refreshJtableGM(con, ui);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Ferramenta offline", "Erro !", 0);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (isonline) {
            new refreshjTableonline(con, this);
            int quantia = 0;
            String quantia2 = JOptionPane.showInputDialog(rootPane, "Insira a quantia desejada a todos os jogadores online", "Adicionar gold a todos onlines", 1);
            if (quantia2 != null) {
                quantia = Integer.valueOf(quantia2);
            }
            if (quantia2 != null) {
                String[] contasStronline = null;
                String queryonline = "SELECT * FROM pw.users INNER JOIN pw.point ON users.id = point.uid and point.zoneid=1";
                try {
                    rs = st.executeQuery(queryonline);
                    while (rs.next()) {
                        contasStronline = new String[]{String.valueOf(rs.getInt("ID"))};
                        addcash(Integer.valueOf(contasStronline[0]), quantia);
                    }
                    JOptionPane.showMessageDialog(rootPane, "Foi adicionado a quantia de: " + quantia + " golds a " + jTable2.getRowCount() + " jogadores online com sucesso !", "Sucesso !", 1);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Não foi possivel completar o pedido\nRazão: " + ex.getMessage(), "Erro", 0);
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Ferramenta offline", "Erro !", 0);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (isonline) {
            new refreshjTableonline(con, ui);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Ferramenta offline", "Erro !", 0);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (isonline) {
            new refreshJtable(con, this);
            int quantia = 0;
            String quantia2 = JOptionPane.showInputDialog(rootPane, "Insira a quantia desejada a ser enviada a todas as contas", "Adicionar gold a todas as contas", 1);
            if (quantia2 != null) {
                quantia = Integer.valueOf(quantia2);
            }
            if (quantia2 != null) {
                String[] contasStronline = null;
                String querycontas2 = "SELECT * FROM users";
                try {
                    rs = st.executeQuery(querycontas2);
                    while (rs.next()) {
                        contasStronline = new String[]{String.valueOf(rs.getInt("ID"))};
                        addcash(Integer.valueOf(contasStronline[0]), quantia);
                        
                    }
                    JOptionPane.showMessageDialog(rootPane, "Foi adicionado a quantia de: " + quantia + " golds a " + jTable1.getRowCount() + " Contas existentes !", "Sucesso !", 1);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Não foi possivel completar o pedido\nRazão: " + ex.getMessage(), "Erro", 0);
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Ferramenta offline", "Erro !", 0);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (isonline) {
            new refreshJtable(con, ui);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Ferramenta offline", "Erro !", 0);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if (apimanager != null) {
            if (isonline && apimanager.isvalidapi) {
                new ResetBankFront(this.ui, apimanager);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Ferramenta/API offline", "Erro !", 0);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Ferramenta/API offline", "Erro !", 0);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        if (apimanager != null) {
            if (isonline && apimanager.isvalidapi) {
                String mensagem;
                mensagem = JOptionPane.showInputDialog(rootPane, "Mensagem", "Broadcaster", 1);
                if (mensagem != null) {
                    try {
                        apimanager.Global(mensagem);
                        JOptionPane.showMessageDialog(rootPane, "Mensagem: " + mensagem + ", enviada com sucesso !", "Sucesso !", 1);
                    } catch (NoSuchAlgorithmException | IOException | JSONException ex) {
                        JOptionPane.showMessageDialog(rootPane, "Operação não pode ser concluída, verifique a API", "Erro !", 0);
                        ex.printStackTrace();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Ferramenta/API offline", "Erro !", 0);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Ferramenta/API offline", "Erro !", 0);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        if (apimanager != null) {
            if (isonline && apimanager.isvalidapi) {
                new FloodFront(this.ui, apimanager);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Ferramenta/API offline", "Erro !", 0);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Ferramenta/API offline", "Erro !", 0);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if (apimanager != null) {
            if (isonline && apimanager.isvalidapi) {
                new RenameFront(this.ui, apimanager);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Ferramenta/API offline", "Erro !", 0);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Ferramenta/API offline", "Erro !", 0);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        if (apimanager != null) {
            if (isonline && apimanager.isvalidapi) {
                new BanFront(apimanager, this.ui);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Ferramenta/API offline", "Erro !", 0);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Ferramenta/API offline", "Erro !", 0);
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        if (apimanager != null) {
            if (isonline && apimanager.isvalidapi) {
                new CasamentoFront(ui, apimanager);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Ferramenta/API offline", "Erro !", 0);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Ferramenta/API offline", "Erro !", 0);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        if (apimanager != null) {
            if (isonline && apimanager.isvalidapi) {
                new TwFront(ui, apimanager);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Ferramenta/API offline", "Erro !", 0);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Ferramenta/API offline", "Erro !", 0);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    
    }//GEN-LAST:event_formWindowOpened

    public void DeletaConta(String conta, int id) throws SQLException {
        String query = "DELETE FROM users WHERE name='" + conta + "' AND ID='" + id + "'";
        st = con.createStatement();
        st.executeUpdate(query);
        JOptionPane.showMessageDialog(rootPane, "Conta " + conta + " deletada com sucesso!", "Sucesso !", 1);
        new refreshJtable(con, this);
    }

    public void addGm(String conta, int id) throws SQLException {
        String query = "call addGM('" + id + "','1');";
        st = con.createStatement();
        st.executeUpdate(query);
        JOptionPane.showMessageDialog(rootPane, "Conta " + conta + " recebeu privilégios GM !", "Sucesso !", 1);
        new refreshJtable(con, this);
    }

    public void removeGm(String conta, int id) throws SQLException {
        String query = "call delUserPriv('" + id + "','1','0','2');";
        st = con.createStatement();
        st.executeUpdate(query);
        JOptionPane.showMessageDialog(rootPane, "Removeu privilégios da conta " + conta + " com sucesso !", "Sucesso !", 1);
        new refreshJtableGM(con, this);
    }

    public void addcash(int id, int quantia) throws SQLException {
        String query = "call usecash ( '" + id + "' , 1, 0, 1, 0, '" + 100 * quantia + "', 1, @error)";
        st = con.createStatement();
        st.executeQuery(query);
    }

    public void localizarID(String id, int aba) {
        if (aba == 0) {
            for (int linha = 0; linha < jTable1.getRowCount(); linha++) {
                String tabela = (String) jTable1.getValueAt(linha, 0);
                if (tabela.equals(id)) {
                    jTable1.setRowSelectionInterval(linha, linha);
                    scrollToVisible(jTable1, linha, 0);
                    finded = true;
                }
            }
            if (finded == false) {
                localizarLogin(id, aba);
            }
        }

        if (aba == 1) {
            for (int linha = 0; linha < jTable2.getRowCount(); linha++) {
                String tabela = (String) jTable2.getValueAt(linha, 0);
                if (tabela.equals(id)) {
                    jTable2.setRowSelectionInterval(linha, linha);
                    scrollToVisible(jTable2, linha, 0);
                    finded = true;
                }
            }
            if (finded == false) {
                localizarLogin(id, aba);
            }
        }
        if (aba == 2) {
            for (int linha = 0; linha < jTable3.getRowCount(); linha++) {
                String tabela = (String) jTable3.getValueAt(linha, 0);
                if (tabela.equals(id)) {
                    jTable3.setRowSelectionInterval(linha, linha);
                    scrollToVisible(jTable3, linha, 0);
                    finded = true;
                }
            }
            if (finded == false) {
                localizarLogin(id, aba);
            }
        }
    }

    public void localizarLogin(String id, int aba) {
        if (aba == 0) {
            for (int linha = 0; linha < jTable1.getRowCount(); linha++) {
                String tabela = (String) jTable1.getValueAt(linha, 1);
                if (tabela.equals(id)) {
                    jTable1.setRowSelectionInterval(linha, linha);
                    scrollToVisible(jTable1, linha, 0);
                    finded = true;
                }
            }
            if (finded == false) {
                JOptionPane.showMessageDialog(rootPane, "Não foi possível localizar o usuário " + id, "Erro !", 0);
            }
        }
        if (aba == 1) {
            for (int linha = 0; linha < jTable2.getRowCount(); linha++) {
                String tabela = (String) jTable2.getValueAt(linha, 1);
                if (tabela.equals(id)) {
                    jTable2.setRowSelectionInterval(linha, linha);
                    scrollToVisible(jTable2, linha, 0);
                    finded = true;
                }
            }
            if (finded == false) {
                JOptionPane.showMessageDialog(rootPane, "Não foi possível localizar o usuário " + id, "Erro !", 0);
            }
        }

        if (aba == 2) {
            for (int linha = 0; linha < jTable3.getRowCount(); linha++) {
                String tabela = (String) jTable3.getValueAt(linha, 1);
                if (tabela.equals(id)) {
                    jTable3.setRowSelectionInterval(linha, linha);
                    scrollToVisible(jTable3, linha, 0);
                    finded = true;
                }
            }
            if (finded == false) {
                JOptionPane.showMessageDialog(rootPane, "Não foi possível localizar o usuário " + id, "Erro !", 0);
            }
        }
    }

    public static void scrollToVisible(JTable table, int rowIndex, int vColIndex) {
        if (!(table.getParent() instanceof JViewport)) {
            return;
        }
        JViewport viewport = (JViewport) table.getParent();

        // This rectangle is relative to the table where the
        // northwest corner of cell (0,0) is always (0,0).
        Rectangle rect = table.getCellRect(rowIndex, vColIndex, true);

        // The location of the viewport relative to the table
        Point pt = viewport.getViewPosition();

        // Translate the cell location so that it is relative
        // to the view, assuming the northwest corner of the
        // view is (0,0)
        rect.setLocation(rect.x - pt.x, rect.y - pt.y);

        table.scrollRectToVisible(rect);

        // Scroll the area into view
        //viewport.scrollRectToVisible(rect);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    public javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JTable jTable1;
    public javax.swing.JTable jTable2;
    public javax.swing.JTable jTable3;
    // End of variables declaration//GEN-END:variables
}
