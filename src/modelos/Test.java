package modelos;

import javax.json.JsonObject;

import util.Consultas;

public class Test {
	private String TestByTestDato1 = "select * from tablatest where testDato1 = ";
	private String TestById = "select * from tablatest where testId = ";
	private String TestAll = "select * from tablatest";
	private String params = "";
	public JsonObject getTestById() {
		Consultas a = new Consultas();
		try {
			return a.consultar(TestById+params);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error consultando getTestById");
			e.printStackTrace();
		}
		return null;
	}
	public JsonObject getTestAll() {
		Consultas a = new Consultas();
		try {
			return a.consultar(TestAll);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error consultando getTestAll");
			e.printStackTrace();
		}
		return null;
	}
	public JsonObject getTestByTestDato1() {
		Consultas a = new Consultas();
		try {
			return a.consultar(TestByTestDato1+params);
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
