package controle;
//Para ver o resultado desse código vá para o arquivo "RodarTela"
import java.awt.*;
import java.sql.SQLException;
import java.text.*;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

import javax.swing.table.DefaultTableModel;
import java.sql.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import conexao.Conexao;

public class Tela extends JFrame {
    Conexao con_cliente;

    JLabel rCodigo, rNome, rEmail, rTel, rData,PesquiCli;
    JTextField tcodigo, tnome, temail,Pesqui;
    JFormattedTextField data,tTelefone;
    JTable tblClientes;
    JScrollPane scp_tabela;

    JButton pri,ant,prox,ult,newR,grav,alt,exc,pesqui,sair;
    MaskFormatter tel,Nasc;
    public Tela() {
    con_cliente = new Conexao();
    con_cliente.conecta();
    
    setTitle("Tabela Banco de dados");
    setResizable(false);
    setLayout(null);

    //Imagens
    ImageIcon icone = new ImageIcon("IMGs/Icon.png");
    ImageIcon novoReg = new ImageIcon("IMGs/NewIcon.png");
    ImageIcon gravarIcon = new ImageIcon("IMGs/SaveIcon.png");
    ImageIcon AlterarIcon = new ImageIcon("IMGs/AltIcon.png");
    ImageIcon excluirIcon = new ImageIcon("IMGs/ExcluirRegIcon.png");
    ImageIcon PesqIcon = new ImageIcon("IMGs/PesqIcon.png");
    setIconImage(icone.getImage());
    
    // Labels
    rCodigo = new JLabel("Código:");
    rNome = new JLabel("Nome:");
    rEmail = new JLabel("Email:");
    rTel = new JLabel("Telefone:");
    rData = new JLabel("Data de Nascimento:");
    PesquiCli = new JLabel("Pesquisar por Nome do Cliente::");

    //Mascara
    try {
        Nasc = new MaskFormatter("##/##/####");
         tel = new MaskFormatter("(##)####-####");
        tel.setPlaceholderCharacter('_');
        Nasc.setPlaceholderCharacter('_');
    } catch (ParseException excp) {
        
    }
    // TextFields
    tcodigo = new JTextField();
    tnome = new JTextField();
    temail = new JTextField();
    data = new JFormattedTextField(Nasc);
    tTelefone = new JFormattedTextField(tel);
    Pesqui = new JTextField();
    //buttons
    pri = new JButton("Primeiro");
    ant = new JButton("Anterior");
    prox = new JButton("Proximo");
    ult = new JButton("Ultimo");

    newR = new JButton("Novo Registro",novoReg);
    grav = new JButton("Gravar",gravarIcon);
    alt = new JButton("Alterar",AlterarIcon);
    exc = new JButton("Excluir",excluirIcon);

    pesqui = new JButton("Pesquisar",PesqIcon);
    sair = new JButton("Sair");
    
    

    // Configurações das posições dos Labels e TextFields e buttons
    rCodigo.setBounds(50, 30, 50, 25);
    tcodigo.setBounds(160, 30, 50, 25);

    rNome.setBounds(50, 70, 100, 25);
    tnome.setBounds(160, 70, 300, 25);

    rEmail.setBounds(50, 110, 100, 25);
    temail.setBounds(160, 110, 300, 25);

    rTel.setBounds(50, 150, 100, 25);
    tTelefone.setBounds(160, 150, 150, 25);

    rData.setBounds(50, 190, 150, 25);
    data.setBounds(210, 190, 100, 25);

    pri.setBounds(50,550,100,25);
    ant.setBounds(150,550,100,25);
    prox.setBounds(250,550,100,25);
    ult.setBounds(350,550,100,25);

    newR.setBounds(50,290,150,25);
    grav.setBounds(210,290,120,25);
    alt.setBounds(350,290,120,25);
    exc.setBounds(490,290,120,25);

    PesquiCli.setBounds(50,620,190,25);
    Pesqui.setBounds(290,620,250,25);
    pesqui.setBounds(550,620,130,25);

    sair.setBounds(720,620,100,25);

    

    // Adiciona os componentes ao Container
    Container tela = getContentPane();
    tela.add(rCodigo);
    tela.add(tcodigo);
    tela.add(rNome);
    tela.add(tnome);
    tela.add(rEmail);
    tela.add(temail);
    tela.add(rTel);
    tela.add(tTelefone);
    tela.add(rData);
    tela.add(data);
    tela.add(Pesqui);
    tela.add(PesquiCli);

    tela.add(pri);
    tela.add(ant);
    tela.add(prox);
    tela.add(ult);

    tela.add(newR);
    tela.add(grav);
    tela.add(alt);
    tela.add(exc);

    tela.add(pesqui);
    tela.add(sair);

    //visual do codigo
    tcodigo.setBackground(new Color(245,222,179));
    tTelefone.setBackground(new Color(245,222,179));
    temail.setBackground(new Color(245,222,179));
    tnome.setBackground(new Color(245,222,179));
    data.setBackground(new Color(245,222,179));
    

    tela.setBackground(new Color(222,184,135));
    

    //função botões
    pri.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            try {
                con_cliente.resultset.first();
                mostrarDados();
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "Não foi possivel aceesar o primeiro registro \n"+erro,"Mensagem do Progroma",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    });
    ant.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            try {
                con_cliente.resultset.previous();
                mostrarDados();
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "Não foi possivel aceesar o primeiro registro \n"+erro,"Mensagem do Progroma",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    });

    prox.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            try {
                con_cliente.resultset.next();
                mostrarDados();
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "Não foi possivel aceesar o primeiro registro \n"+erro,"Mensagem do Progroma",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    });
    ult.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            try {
                con_cliente.resultset.last();
                mostrarDados();
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "Não foi possivel aceesar o primeiro registro \n"+erro,"Mensagem do Progroma",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    });
    newR.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
            //limpar caixa de texto
            tcodigo.setText("");
            tnome.setText("");
            data.setText("");
            tTelefone.setText("");
            temail.setText("");
            tcodigo.requestFocus();//leva o mouse para essa caixa de texto

        }
    });
    grav.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
            String nome = tnome.getText();
            String dataNasc = data.getText();
            String tel = tTelefone.getText();
            String email = temail.getText();

            try {
                String insert_sql="insert into tbclientes (nome,telefone, email, dt_nasc) values ('" + nome + "','" + tel + "','" + email + "','" + dataNasc + "')";            
                con_cliente.statement.executeUpdate(insert_sql);
                JOptionPane.showMessageDialog(null,"Gravação realizada com sucesso","Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);

                con_cliente.executaSQL("select * from tbclientes order by cod");
                preencherTabela();
            } 
                catch (SQLException errosql) {
                    JOptionPane.showMessageDialog(null,"\n Erro de gravação: \n"+errosql,"Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
                }
        }
    });
    alt.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
            String nome = tnome.getText();
            String dataNasc = data.getText();
            String tel = tTelefone.getText();
            String email = temail.getText();
            String sql;
            String msg = "";

            try {
                if (tcodigo.getText().equals("")) {
                    sql="insert into tbclientes (nome,telefone, email, dt_nasc) values ('" + nome + "','" + tel + "','" + email + "','" + dataNasc + "')"; 
                    msg = "Gravação de um novo registro";         
                 }
                 else{
                    sql = "update tbclientes set nome='" + nome + "',telefone='" + tel + "', email='" + email + "', dt_nasc='" + dataNasc + "' where cod = " + tcodigo.getText();
                 }
                 con_cliente.statement.executeUpdate(sql);
                 JOptionPane.showMessageDialog(null,"Gravação realizada com sucesso","Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
                
                 con_cliente.executaSQL("select * from tbclientes order by cod");
                 preencherTabela();
            }  catch (SQLException errosql) {
                JOptionPane.showMessageDialog(null,"\n Erro de gravação: \n"+errosql,"Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    });
    exc.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
            String sql ="";
            try {
                int respostta = JOptionPane.showConfirmDialog(rootPane,"Deseja excluir o registro: ","Confirmar Exclusão",JOptionPane.YES_NO_CANCEL_OPTION);
                if (respostta == 0) {
                    sql = "delete from tbclientes where cod ="+tcodigo.getText();
                    int excluir = con_cliente.statement.executeUpdate(sql);
                    if (excluir == 1) {
                        JOptionPane.showMessageDialog(null,"Exclusão realizada com sucesso","Mensagem do Progroma",JOptionPane.INFORMATION_MESSAGE);
                        con_cliente.executaSQL("select * from tbclientes order by cod");
                        con_cliente.resultset.first();
                        preencherTabela();
                        posicionarRegistro();
                    } else {
                        JOptionPane.showMessageDialog(null,"Operação cancelada pelo usuario","Mensagem do Progroma",JOptionPane.INFORMATION_MESSAGE);
                    }
                } 
            } catch (SQLException execao) {
                JOptionPane.showMessageDialog(null,"Erro na exclusão: "+execao,"Mensagem do Progroma",JOptionPane.INFORMATION_MESSAGE);
            }

        }
    });
    pesqui.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
            try {
                String pesquisa = "select * from tbclientes where nome like'"+Pesqui.getText()+"%'";
                con_cliente.executaSQL(pesquisa);
                if (con_cliente.resultset.first()) {
                    preencherTabela();
                } else {
                    JOptionPane.showMessageDialog(null, "\n Nâo existe dados com este parametro!","Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException errosql) {
                    JOptionPane.showMessageDialog(null,"\n Os dados digitados não foram localizados: \n"+errosql,"Mensagem do Progroma",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    });
    sair.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
            int leave = JOptionPane.showConfirmDialog(rootPane,"Deseja mesmo sair?","Opção de Sair",JOptionPane.YES_NO_OPTION);
            if (leave == 0) {
                JOptionPane.showMessageDialog(null,"Encerrando Progroma","Mensagem do Progroma",JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
            else{
                JOptionPane.showMessageDialog(null,"Operação cancelada pelo usuario","Mensagem do Progroma",JOptionPane.INFORMATION_MESSAGE);
            }
            
        }
    });
    // Configurações da tabela
tblClientes = new javax.swing.JTable();
scp_tabela = new javax.swing.JScrollPane();

//posições da tabela
tblClientes.setBounds(50, 320, 700, 200);
scp_tabela.setBounds(50, 320, 700, 200);

tblClientes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
tblClientes.setFont(new java.awt.Font("Arial", 1, 12));

// Define a cor de fundo da tabela
Color backgroundColor = new Color(210,180,140);
tblClientes.setBackground(backgroundColor);
JViewport viewport = scp_tabela.getViewport();
viewport.setBackground(backgroundColor);

tblClientes.setModel(new javax.swing.table.DefaultTableModel(
    new Object[][]{
        {null, null, null, null, null},
        {null, null, null, null, null},
        {null, null, null, null, null},
        {null, null, null, null, null},
    },
    new String[]{"Código", "Nome", "Data de Nascimento", "Telefone", "Email"}
) {
    boolean[] canEdit = new boolean[]{
        false, false, false, false, false
    };

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex];
    }
});
scp_tabela.setViewportView(tblClientes);
tblClientes.setAutoCreateRowSorter(true); // Ativa a classificação da tabela

tela.add(scp_tabela);  // Adiciona apenas o JScrollPane ao Container

    // Configurações da janela
    setSize(920, 800);
    setVisible(true);
    setLocationRelativeTo(null);

    con_cliente.executaSQL("select * from tbclientes order by cod");
    preencherTabela();
    posicionarRegistro();
    }
    //metodo prencher tabela:
    public void preencherTabela(){
        tblClientes.getColumnModel().getColumn(0).setPreferredWidth(4);
        tblClientes.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblClientes.getColumnModel().getColumn(2).setPreferredWidth(11);
        tblClientes.getColumnModel().getColumn(3).setPreferredWidth(14);
        tblClientes.getColumnModel().getColumn(4).setPreferredWidth(100);

        DefaultTableModel modelo = (DefaultTableModel) tblClientes.getModel();
        modelo.setNumRows(0);

        try {
            con_cliente.resultset.beforeFirst();
            while (con_cliente.resultset.next()) {
              modelo.addRow(new Object[]{
                con_cliente.resultset.getString("cod"),con_cliente.resultset.getString("nome"),con_cliente.resultset.getString("dt_nasc"),con_cliente.resultset.getString("telefone"),con_cliente.resultset.getString("email")
              });
            }
        } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null,"\n Erro ao listar dados da tabela!!\n"+erro,"Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
        }


    //metodo para poscionar os dados
    }
    public void posicionarRegistro(){
        try {
            con_cliente.resultset.first();//posciona 1° registro na tabela
            mostrarDados();//chama o metodo que busca os dados na tabela
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"Não foi possível posicionar no primeiro registro: "+erro,"Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);        
        }
    }
    
    public void mostrarDados(){
        try {
            tcodigo.setText(con_cliente.resultset.getString("cod"));
            tnome.setText(con_cliente.resultset.getString("nome"));
            data.setText(con_cliente.resultset.getString("dt_nasc"));
            tTelefone.setText(con_cliente.resultset.getString("telefone"));
            temail.setText(con_cliente.resultset.getString("email"));
            

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"Não localizou os dados: "+erro,"Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);       
        }
    }
}


