package modelos;

import javax.json.JsonObject;

import org.json.simple.JSONObject;

import util.Consultas;

public class Facturacion {
	private String facById = "select * from facturacion_cabecera as fcab join facturacion_cuerpo as fcue on fcab.id = fcue.fk_cabecera where fcab.id = ";
	private String facByAgrupacion = "select cue.*, cab.* from facturacion_cuerpo as cue \r\n" + 
			"JOIN facturacion_cabecera as cab on cue.fk_cabecera = cab.id\r\n" + 
			"JOIN siscol_obrasoci as os on os.CODIGOOBRASOCIAL = cue.fk_os\r\n" + 
			"join api_users_os as apiusers on apiusers.permisos_agrupacion = os.CODIGOAGRUPACION\r\n" + 
			"WHERE apiusers.permisos_agrupacion = ";
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
	public JsonObject getFacturacionById(String params) {
		this.params = params;
		Consultas a = new Consultas();
		try {
			return a.consultar(facById+params);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error consultando facturación por ID");
			e.printStackTrace();
		}
		return null;
	}
	public JSONObject getFacturacionByAgrupacion(String params) {
		this.params = params;
		Consultas a = new Consultas();
		try {
			return a.consultar(facByAgrupacion+params);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error consultando facturación por agrupacion");
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
