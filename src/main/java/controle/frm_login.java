
package controle;

import conexao.Conexao;
import java.sql.SQLException;

import javax.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.*;
public class frm_login extends JFrame{
    Conexao con_client;

    JPasswordField SenhaText; 
    JLabel Titu,usu,senha;
    JTextField UsuarioText;
    JButton log,cad;

    public frm_login(){
        con_client = new Conexao();
        con_client.conecta();

        setTitle("*** login de acesso***");
        Container tela = getContentPane();
        setLayout(null);
        
        //images
        ImageIcon icon = new ImageIcon("IMGs/Logar.jpg");
        ImageIcon cadButt = new ImageIcon("IMGs/cadButt.png");
        ImageIcon loginButt = new ImageIcon("IMGs/LoginButt.png");
        setIconImage(icon.getImage());
        Titu = new JLabel("Acesso ao Sistema");
        usu = new JLabel("Usuario:");
        senha = new JLabel("Senha:");

        UsuarioText = new JTextField();
        SenhaText = new JPasswordField(120);

        log = new JButton("Logar",loginButt);
        cad = new JButton("Cadastrar",cadButt);

        Titu.setFont(new Font("Courier New",Font.BOLD,20));
        usu.setFont(new Font("Courier New",Font.BOLD,15));
        senha.setFont(new Font("Courier New",Font.BOLD,15));
        UsuarioText.setBackground(new Color(245,222,179));
        SenhaText.setBackground(new Color(245,222,179));
        tela.setBackground(new Color(222,184,135));
        //setBounds

        Titu.setBounds(200,50,290,25);
        usu.setBounds(160,150,100,25);
        senha.setBounds(180,200,100,25);

        UsuarioText.setBounds(240,150,100,25);
        SenhaText.setBounds(240,200,100,25);

        log.setBounds(240,280,100,25);
        cad.setBounds(225,310,130,25);

        tela.add(Titu);
        tela.add(usu);
        tela.add(senha);
        tela.add(UsuarioText);
        tela.add(SenhaText);
        tela.add(log);
        tela.add(cad);


        setSize(600, 500);
        setVisible(true);
        setLocationRelativeTo(null);

        log.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                try {
                    String pesquisa = "select * from tblusuario where usuario like '" + UsuarioText.getText()+"'&& senha = "+SenhaText.getText()+"";
                    con_client.executaSQL(pesquisa);
                    if (con_client.resultset.first()) {
                        Tela mostra = new Tela();
                        mostra.setVisible(true);
                        dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"\n Usuario não cadastrado!!","Mensagem do Progroma",JOptionPane.INFORMATION_MESSAGE);
                        con_client.desconecta();
                        System.exit(0);
                    }
                } catch (SQLException errosql) {
                    JOptionPane.showMessageDialog(null, "\n Usuário não cadastrado!!!!","Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE); 
                    
                }
            }
        });
        cad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                frm_cadastrar cadas = new frm_cadastrar();
                cadas.setVisible(true);
                dispose();
            }
        });
    }
    public static void main(String[] args) {
        frm_login app = new frm_login();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
