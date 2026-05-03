package br.edu.utfpr.td.tsi.ecommerce.loja;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConexaoCliente {
	public String buscarCatalogo() {
		try {
			URL url = new URL("http://localhost:8081/ecommerce.produtos/catalogo");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Accept", "application/json");

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			return response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String buscarCep(String cep) {
	    try {
	        URL url = new URL("http://localhost:8082/ecommerce.cep/cep/" + cep);
	        HttpURLConnection con = (HttpURLConnection) url.openConnection();
	        con.setRequestMethod("GET");

	        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	        String inputLine;
	        StringBuilder response = new StringBuilder();
	        while ((inputLine = in.readLine()) != null) {
	            response.append(inputLine);
	        }
	        in.close();
	        return response.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
}
