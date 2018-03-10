package br.com.NapkinWs.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



/**esta classe contem os metodos para realizar conexao com o banco de dados e o metodo para encerrar esta conexão.
 * 
 * @author IAGO GEORGE
 * @version 1.0
 *==
 */

public class Conexao {
	
	
	private static final String DRIVER="com.mysql.jdbc.Driver";
	private static final String URL="jdbc:mysql://localhost/napkin";
	private static final String USUARIO="root";
	private static final String SENHA="aluno";



	/**
	 * metodo que cria a conexao com o banco de dados
	 * @author IAGO GEORGE
	 * @version 1.0
	 */
	
	public Connection conectarBD(){
		
		Connection conexao = null;
		try {
			
			Class.forName(DRIVER);
			conexao = DriverManager.getConnection(URL,USUARIO,SENHA);
			
		} catch (Exception e) {
			System.out.println("erro ao tentar conexao com o banco de dados, provavelmente alguma merda foi feita, entao é bom verificar");
			e.printStackTrace();
		}
		return conexao;
	}
	
	
	public void FechaConexao(Connection conexao,PreparedStatement ppst,ResultSet result){
		try {
			
		if (conexao!=null) {
			conexao.close();
			}
		if (ppst!=null) {
			ppst.close();
			}
		if (result!=null) {
			result.close();
			}
		
		} catch (SQLException e) {
				System.out.println("ocorreu um erro ao tentar fechar a conexao com o banco, preste mais atenção no que vc esta fazendo");
				e.printStackTrace();
			}
		}
		
		
	}


