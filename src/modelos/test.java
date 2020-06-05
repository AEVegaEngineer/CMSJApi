package modelos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.json.JsonObject;

import api.MysqlConnection;
import io.github.mattvass.resultsetmapper.JsonResultSet;
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
			System.out.println("Error consultando getTestById");
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
