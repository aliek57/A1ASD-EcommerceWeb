package br.edu.utfpr.td.tsi.ecommerce.loja;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
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
	
	public String processarPagamento(String dadosPagamentoJson) {
	    try {
	        URL url = new URL("http://localhost:8083/ecommerce.pagamento/pagamento");
	        HttpURLConnection con = (HttpURLConnection) url.openConnection();
	        con.setRequestMethod("POST");
	        con.setRequestProperty("Content-Type", "application/json");
	        con.setDoOutput(true);

	        try (OutputStream os = con.getOutputStream()) {
	            byte[] input = dadosPagamentoJson.getBytes("utf-8");
	            os.write(input, 0, input.length);
	        }

	        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
	        StringBuilder response = new StringBuilder();
	        String line;
	        while ((line = in.readLine()) != null) {
	            response.append(line.trim());
	        }
	        return response.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	public String enviarEmail(String emailJson) {
	    try {
	        URL url = new URL("http://localhost:8084/ecommerce.email/email");
	        HttpURLConnection con = (HttpURLConnection) url.openConnection();
	        con.setRequestMethod("POST");
	        con.setRequestProperty("Content-Type", "application/json");
	        con.setDoOutput(true);

	        try (OutputStream os = con.getOutputStream()) {
	            byte[] input = emailJson.getBytes("utf-8");
	            os.write(input, 0, input.length);
	        }

	        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
	        StringBuilder response = new StringBuilder();
	        String line;
	        while ((line = in.readLine()) != null) {
	            response.append(line.trim());
	        }
	        in.close();
	        return response.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
}
