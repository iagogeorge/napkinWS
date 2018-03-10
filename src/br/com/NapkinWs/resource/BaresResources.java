package br.com.NapkinWs.resource;

import java.util.ArrayList;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import br.com.NapkinWs.controller.BaresController;
import br.com.NapkinWs.model.Bares;
import br.com.NapkinWs.model.Usuarios;

/**
 * esta classe contem os metodos REST de acesso ao webservice
 * 
 * 
 * @author IAGO GEORGE
 *
 */
@Path("/bar")   // o @path define a URI do recurso que nesse caso será /bar



public class BaresResources {

	@GET // utilizando apenas o verbo GET
	@Path("/defineRaio/{lat}/{lng}/")
	@Produces("application/json")// define qual tipo MIME é retornado
	public ArrayList<Bares> defineRaio(@PathParam("lat") String lat, @PathParam("lng") String lng, @DefaultValue("1") @QueryParam("radius") int radius){
		return new BaresController().defineRaio(lat, lng, radius);

	}

	@GET
	@Path("/listaTodos")
	@Produces("application/json")
	public ArrayList<Bares> listaTodos() {

		return new BaresController().listaTodos();
	}

	// http://localhost:8080/NapkinWs/bar/defineRaio/-8.020258/-34.850416/?radius=1

	@GET
	@Path("/listAllUsers")
	@Produces("application/json")
	public ArrayList<Usuarios> listAllUsers() {
		return new BaresController().listAllUsers();
		
}
	

	@GET
	@Path("/addUser/{email}/{lat}/{lng}/{radius}/{telefone}/{nome}/{foto}/{profile}/")
	@Produces("application/json")
	
	public void addUser(@PathParam("email")String email,@PathParam("lat") String lat, @PathParam("lng") String lng, @PathParam("radius")int radius,@PathParam("telefone")String telefone, @PathParam("nome")String nome, @PathParam("foto")String foto, @PathParam("profile")String profile){
		//return new 
		new BaresController().addUser(email, lat, lng, radius, telefone,nome, foto, profile);
		
	}
	@GET
	@Path("/buscarPorFone/{telefone}/")
	@Produces("application/json")
	
	public Usuarios buscarPorFone(@PathParam("telefone") String telefone){
		 return new BaresController().buscarPorFone(telefone);
	}
	
	@GET
	@Path("/buscaUserNoRaio/{lat}/{lng}/{radius}")
	@Produces("application/json")
	public ArrayList<Usuarios> buscaUserNoRaio(@PathParam("lat") String lat,
			@PathParam("lng") String lng,@PathParam("radius") int radius) {
		return new BaresController().retornaUserNoRaio(lat, lng, radius);

	}
	
	@GET
	@Path("/mudaLocalizacao/{latitude}/{longitude}/{radius}/{telefone}/")
	@Produces("application/json")
	
	public void mudaLocalizacao(@PathParam("latitude") double latitude,@PathParam("longitude") double longitude,@PathParam("radius") int radius,@PathParam("telefone") String telefone){
		
		new BaresController().mudaLocalizacao(latitude, longitude, radius, telefone);
	}
	
}
