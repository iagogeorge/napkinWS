package br.com.NapkinWs.controller;

import java.util.ArrayList;

import br.com.NapkinWs.dao.BaresDAO;
import br.com.NapkinWs.model.Bares;
import br.com.NapkinWs.model.Usuarios;

public class BaresController {

	public ArrayList<Bares> listaTodos() {
		return BaresDAO.pegaInstancia().listaTodos();

	}

	public ArrayList<Bares> defineRaio(String lat, String lng, int radius) {
		double latitude = 0;
		try{
			latitude = new Double(lat);
		}catch(Exception e){
		}
		double longitude = 0;
		try{
			longitude = new Double(lng);
		}catch(Exception e){
		}
		return BaresDAO.pegaInstancia().defineRaio(latitude, longitude, radius);
	}
	
	public ArrayList<Usuarios> listAllUsers() {
		return BaresDAO.pegaInstancia().ListAllUsers();

	}

	public void addUser(String email, String lat, String lng, int radius, String telefone,String nome, String foto, String profile) {

		double latitude = 0;
		try {
		latitude = new Double(lat);
		} catch (Exception e) {
			e.printStackTrace();
		}

		double longitude = 0;

		try {
			longitude = new Double(lng);
		} catch (Exception e) {
			e.printStackTrace();
		}

		 
		BaresDAO.pegaInstancia().addUser(email, lat, lng, radius, telefone,nome,foto,profile);

	}
public Usuarios buscarPorFone(String telefone){
		
		return BaresDAO.pegaInstancia().buscarPorFone(telefone);
	}
	
public ArrayList<Usuarios> retornaUserNoRaio(String lat, String lng, int radius) {
	double latitude = 0;
	try {
		latitude = new Double(lat);
	} catch (Exception e) {
	}
	double longitude = 0;
	try {
		longitude = new Double(lng);
	} catch (Exception e) {
	}
	return BaresDAO.pegaInstancia().retornaUsersNoRaio(latitude, longitude, radius);
	}

public void mudaLocalizacao(double latitude, double longitude, int radius, String telefone){
	
	BaresDAO.pegaInstancia().mudaLocalizacao(latitude, longitude, radius, telefone);
	
	
	}

}
