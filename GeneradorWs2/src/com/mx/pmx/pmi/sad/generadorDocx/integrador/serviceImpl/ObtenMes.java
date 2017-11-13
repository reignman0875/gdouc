package com.mx.pmx.pmi.sad.generadorDocx.integrador.serviceImpl;

public class ObtenMes {

	public static String numeroLetra(String numero) {
		int numeroMes = Integer.parseInt(numero);
		String letraMes = new String();
		
		switch(numeroMes) {
		case 1:
			letraMes="Enero";
			break;
		case 2:
			letraMes="Febrero";
			break;
		case 3:
			letraMes="Marzo";
			break;
		case 4:
			letraMes="Abril";
			break;
		case 5:
			letraMes="Mayo";
			break;
		case 6:
			letraMes="Junio";
			break;
		case 7:
			letraMes="Julio";
			break;
		case 8:
			letraMes="Agosto";
			break;
		case 9:
			letraMes="Septiembre";
			break;
		case 10:
			letraMes="Octubre";
			break;
		case 11:
			letraMes="Noviembre";
			break;
		case 12:
			letraMes="Diciembre";
			break;
		default:
			letraMes="";
			break;
		}
		
		return letraMes;
	}
}
