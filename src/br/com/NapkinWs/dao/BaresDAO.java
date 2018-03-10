package br.com.NapkinWs.dao;

/**
 * ==========================================================================================
 * esta classe contem todos os metodos de consulta, inserção, alteração e exclusao dos dados
 * @author IAGO GEORGE
 * ==========================================================================================
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.NapkinWs.factory.Conexao;
import br.com.NapkinWs.model.Bares;
import br.com.NapkinWs.model.Usuarios;

public class BaresDAO extends Conexao {

	/**
	 * estes metodo é responsavel por criar uma instancia da classe BaresDAO
	 * 
	 * 
	 * 
	 */

	public static BaresDAO instancia;

	public static BaresDAO pegaInstancia() {

		if (instancia == null) {
			instancia = new BaresDAO();
		}
		return instancia;

	}

	/**
	 * 
	 * 
	 * Metodo que lista todos os  bares
	 */
	
	
	public ArrayList<Bares> listaTodos() {
		ArrayList<Bares> bares = null;
		Connection conexao = null;
		PreparedStatement ppst = null;
		ResultSet result = null;

		bares = new ArrayList<Bares>();
		conexao = conectarBD();
		try {
			ppst = conexao.prepareStatement("select * from bar order by id");
			result = ppst.executeQuery();

			while (result.next()) {

				Bares bar = new Bares();

				bar.setId(result.getInt("id"));
				bar.setEmail(result.getString("email"));
				bar.setEndereco(result.getString("endereco"));
				bar.setEspecialidade(result.getString("especialidade"));
				bar.setLatitude(result.getDouble("latitude"));
				bar.setLongitude(result.getDouble("longitude"));
				bar.setNome(result.getString("nome"));
				bar.setSite(result.getString("site"));
				bar.setTelefone(result.getString("telefone"));

				bares.add(bar);

			}

		} catch (Exception e) {
			System.out
					.println("erro ao listar todos os registro, muita merda foi feita se voce esta lendo essa mensagem");
		} finally {

			FechaConexao(conexao, ppst, result);

		}
		return bares;
	}

	
	/**
	 * este metodo é responsavel por retornar todos  os bares que estejam em um raio definido pelo usuario
	 * sao passados como parametros a latitude longitude e raio para a busca, do USUARIO. 
	 * Para realizar este cálculo, as variáveis já deverão estar convertidas para radianos. 
	 * Para encontrar a distância entre dois pontos, basta multiplicar o resultado desta equação por 6380. 
	 * O valor 6380 corresponde ao raio do planeta terra, medido em Quilômetros.
	 * 
	 * 

	 */
	
	
	public ArrayList<Bares> defineRaio(double latitude, double longitude, int radius) {
		
		//algum lugar longe
		 /*-70.020258
		-87.850416*/
		
		//lugar perto do maxabomba
		//-8.020258 |-34.850416
		
		ArrayList<Bares> baresdist = null;
		ResultSet res = null;
		Connection con = null;
		PreparedStatement pps = null;

		baresdist = new ArrayList<Bares>();
		con = conectarBD();
		try {
			pps = con
					.prepareStatement("SELECT * FROM bar WHERE ACOS(COS( RADIANS( latitude )) * COS( RADIANS( "
							+ latitude
							+ ")) * COS( RADIANS( "
							+ longitude
							+ ")-RADIANS( "
							+ longitude
							+ " )) + SIN( RADIANS( latitude ) ) * SIN( RADIANS( "
							+ latitude + " ) ) ) * 6380 < "+radius+"");

			res = pps.executeQuery();

			while (res.next()) {

				Bares bar = new Bares();

				bar.setId(res.getInt("id"));
				bar.setEmail(res.getString("email"));
				bar.setEndereco(res.getString("endereco"));
				bar.setEspecialidade(res.getString("especialidade"));
				bar.setLatitude(res.getDouble("latitude"));
				bar.setLongitude(res.getDouble("longitude"));
				bar.setNome(res.getString("nome"));
				bar.setSite(res.getString("site"));
				bar.setTelefone(res.getString("telefone"));

				baresdist.add(bar);

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			FechaConexao(con, pps, res);
		}
		return baresdist;
	
		
	}

	/**
	 * 
	 * Metodo responsavel por listar todos os usuarios cadastrados
	 * 
	 * 
	 */
	
	
	
	
	public ArrayList<Usuarios> ListAllUsers() {
		ArrayList<Usuarios> users = null;
		ResultSet rest = null;
		PreparedStatement pps = null;
		Connection con = null;

		users = new ArrayList<Usuarios>();

		con = conectarBD();

		try {

			pps = con.prepareStatement("SELECT * FROM usuarios");
			
			rest  = pps.executeQuery();
			
			
			while (rest.next()) {
				
				Usuarios user = new Usuarios();

				user.setId(rest.getInt("id"));
				user.setEmail(rest.getString("email"));
				user.setLatitude(rest.getDouble("latitude"));
				user.setLongitude(rest.getDouble("longitude"));
				user.setRadius(rest.getDouble("radius"));
				user.setTelefone(rest.getString("telefone"));
				user.setNome(rest.getString("nome"));
				user.setFoto(rest.getString("foto"));
				user.setProfile(rest.getString("profile"));
				
				
				
				users.add(user);
				
			}

		} catch (Exception e) {
			e.printStackTrace();

		}finally{
			
			FechaConexao(con, pps, rest);
			
		}
		return users;

	}
	
	
	/**
	 * 
	 *metodo resposanvel pelo cadastrado de usuarios 
	 *
	 */
	
	public void addUser(String email, String lat, String lng, int radius,String telefone,String nome,String foto, String profile){
		
		ResultSet resut = null;
		Connection con = null;
		PreparedStatement pps = null;
		
		con = conectarBD();
		
		
	
		try {

			pps = con.prepareStatement("INSERT INTO usuarios (email, latitude, longitude,radius,telefone,nome,foto,profile)VALUES(?, ?, ?,?,?,?,?,?)");

			
			pps.setString(1, email);
			pps.setString(2, lat);
			pps.setString(3, lng);
			pps.setInt(4, radius);
			pps.setString(5,telefone);
			pps.setString(6, nome);
			pps.setString(7, foto);
			pps.setString(8, profile);
	
			pps.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally{
			
			FechaConexao(con, pps, resut);
		}
		
		
		
		
	}
	
	/**
	 *metodo responsavel pelo busca de um usuario em especifico, na consulta SQL desse metodo é passado como parametro
	 *o numero de telefone do usuario ao qual se deseja retornar os dados. 
	 * 
	 *
	 */
	
	
	public Usuarios buscarPorFone(String telefone) {

		Usuarios user = null;

		ResultSet resuet = null;

		Connection cone = null;

		PreparedStatement ppst = null;

		cone = conectarBD();

		try {

			ppst = cone
					.prepareStatement("SELECT * FROM usuarios where telefone = ?");

			ppst.setString(1, telefone);

			resuet = ppst.executeQuery();

			if (resuet.next()) {

				user = new Usuarios();
				user.setId(resuet.getInt("id"));
				user.setEmail(resuet.getString("email"));
				user.setLatitude(resuet.getDouble("latitude"));
				user.setLongitude(resuet.getDouble("longitude"));
				user.setRadius(resuet.getDouble("radius"));
				user.setTelefone(resuet.getString("telefone"));
				user.setNome(resuet.getString("nome"));
				user.setFoto(resuet.getString("foto"));
				user.setProfile(resuet.getString("profile"));
				

			}

			else {

				return null;

			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			FechaConexao(cone, ppst, resuet);

		}

		return user;

	}
	
	
	/**
	 * este metodo é responsavel por retornar todos  os USUARIOS que estejam em um raio definido pelo usuario
	 * sao passados como parametros a latitude longitude e raio para a busca, do USUARIO. 
	 * Para realizar este cálculo, as variáveis já deverão estar convertidas para radianos. 
	 * Para encontrar a distância entre dois pontos, basta multiplicar o resultado desta equação por 6380. 
	 * O valor 6380 corresponde ao raio do planeta terra, medido em Quilômetros.
	 * 
	 * 

	 */
	
	public ArrayList<Usuarios> retornaUsersNoRaio(double latitude, double longitude,
			int radius) {

		// algum lugar longe
		/*-70.020258
		-87.850416*/

		// lugar perto do maxabomba
		// -8.020258 |-34.850416

		ArrayList<Usuarios> UserRange = null;
		ResultSet res = null;
		Connection con = null;
		PreparedStatement pps = null;

		UserRange = new ArrayList<Usuarios>();
		con = conectarBD();
		try {
			pps = con
					.prepareStatement("SELECT * FROM usuarios WHERE ACOS(COS( RADIANS( latitude )) * COS( RADIANS( "
							+ latitude
							+ ")) * COS( RADIANS( "
							+ longitude
							+ ")-RADIANS( "
							+ longitude
							+ " )) + SIN( RADIANS( latitude ) ) * SIN( RADIANS( "
							+ latitude + " ) ) ) * 6380 < " + radius + "");

			res = pps.executeQuery();

			while (res.next()) {

				Usuarios user = new Usuarios();

				
				user.setEmail(res.getString("email"));
				user.setLatitude(res.getDouble("latitude"));
				user.setLongitude(res.getDouble("longitude"));
				user.setRadius(res.getDouble("radius"));
				user.setTelefone(res.getString("telefone"));
				user.setNome(res.getString("nome"));
				user.setFoto(res.getString("foto"));
				user.setProfile(res.getString("profile"));
				
				
				UserRange.add(user);

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			FechaConexao(con, pps, res);
		}
		return UserRange;

	}
	
	/**
	 * metodo que altera os dados de localiazação do usuario, quando a localização do mesmo muda essas informações 
	 * sao atualizadas na base de dados.
	 * 
	 */
	
	
	public void mudaLocalizacao(double latitude, double longitude, int radius, String telefone){

		ResultSet r = null;
		Connection c = null;
		PreparedStatement p = null;

		c = conectarBD();
		
		try {
			
			p = c.prepareStatement("UPDATE usuarios SET latitude = ?, longitude = ?, radius = ? WHERE telefone = ?");
			
			p.setDouble(1, latitude);
			p.setDouble(2, longitude);
			p.setInt(3, radius);
			p.setString(4, telefone);
			
			p.executeUpdate();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			FechaConexao(c, p, r
);
			
		}
	


	}
	
}
