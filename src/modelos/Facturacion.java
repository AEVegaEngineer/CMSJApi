package modelos;

import javax.json.JsonObject;

import util.Consultas;

public class Facturacion {
	private String facById = 
			"select * from facturacion_cabecera as fcab "
			+ "join facturacion_cuerpo as fcue on fcab.id = fcue.fk_cabecera "
			+ "where fcab.id = ";
	private String facAll = "select * from facturacion_cabecera";
	private String params = "";
	public JsonObject getFacturacionAll() {
		Consultas a = new Consultas();
		try {
			return a.consultar(facAll);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error consultando Facturaciones");
			e.printStackTrace();
		}
		return null;
	}
	public JsonObject getFacturacionById() {
		Consultas a = new Consultas();
		try {
			return a.consultar(facById+params);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error consultando getTestByTestDato1");
			e.printStackTrace();
		}
		return null;
	}
	
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}	
}
