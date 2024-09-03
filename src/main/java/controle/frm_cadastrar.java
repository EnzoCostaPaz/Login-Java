
package controle;
import conexao.Conexao;
import java.sql.SQLException;

import javax.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.*;
import java.util.Arrays;
public class frm_cadastrar extends JFrame{
    Conexao con_client;

    JPasswordField SenhaText,confsenha; 
    JLabel Titu,usu,senha,confSenhaText;
    JTextField UsuarioText;
    JButton cad,volt;
    public frm_cadastrar(){
        con_client = new Conexao();
        con_client.conecta();

        setTitle("*** Cadastro de Usuario***");
        Container tela = getContentPane();
        setLayout(null);
        
        ImageIcon icon = new ImageIcon("IMGs/cad.png");
        ImageIcon cadButt = new ImageIcon("IMGs/cadButt.png");
        ImageIcon voltButt = new ImageIcon("IMGs/volt.png");
        setIconImage(icon.getImage());
        Titu = new JLabel("Cadastro no Sistema");
        usu = new JLabel("Usuario:");
        senha = new JLabel("Senha:");
        confSenhaText = new JLabel("Confirme sua senha:");
        confsenha = new JPasswordField(120);

        UsuarioText = new JTextField();
        SenhaText = new JPasswordField(120);

        cad = new JButton("Cadastrar",cadButt);
        volt = new JButton("Voltar",voltButt);

        Titu.setFont(new Font("Courier New",Font.BOLD,20));
        usu.setFont(new Font("Courier New",Font.BOLD,15));
        senha.setFont(new Font("Courier New",Font.BOLD,15));
        confSenhaText.setFont(new Font("Courier New",Font.BOLD,15));

        confsenha.setBackground(new Color(245,222,179));
        UsuarioText.setBackground(new Color(245,222,179));
        SenhaText.setBackground(new Color(245,222,179));
        tela.setBackground(new Color(222,184,135));
        //setBounds

        Titu.setBounds(200,50,290,25);
        usu.setBounds(160,150,100,25);
        senha.setBounds(180,200,100,25);
        confSenhaText.setBounds(64,230,190,25);

        UsuarioText.setBounds(240,150,100,25);
        SenhaText.setBounds(240,200,100,25);
        confsenha.setBounds(240,230,100,25);

        cad.setBounds(225,280,130,25);
        volt.setBounds(240,320,100,25);
        tela.add(Titu);
        tela.add(usu);
        tela.add(senha);
        tela.add(UsuarioText);
        tela.add(SenhaText);
        tela.add(cad);
        tela.add(confsenha);
        tela.add(confSenhaText);
        tela.add(volt);


        setSize(600, 500);
        setVisible(true);
        setLocationRelativeTo(null);

        cad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String Usu = UsuarioText.getText();
                char[] senha = SenhaText.getPassword();
                char[] confSenha = confsenha.getPassword();
                
                // Converte o array de caracteres para String
                String senhaString = new String(senha);
                String confSenhaString = new String(confSenha);
                
                if (Arrays.equals(senha, confSenha)) {
                    try {
                        // Monta a string SQL corretamente, usando a senha convertida para String
                        String cadastro_sql = "INSERT INTO tblusuario (usuario, senha) VALUES ('" + Usu + "', '" + senhaString + "')";
                        con_client.statement.executeUpdate(cadastro_sql);
                        JOptionPane.showMessageDialog(null,"Cadastro realizado com sucesso","Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
        
                    } catch (Exception errosql) {
                        JOptionPane.showMessageDialog(null, "\nFalha ao cadastrar usuário\n"+errosql,"Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE); 
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"\nConfirmação de senha diferente da senha digitada","Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });        
        volt.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e){
                frm_login voltar = new frm_login();
                voltar.setVisible(true);
                dispose();
            }
        });
    }
}
