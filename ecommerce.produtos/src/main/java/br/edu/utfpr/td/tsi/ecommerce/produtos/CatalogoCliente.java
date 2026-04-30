package br.edu.utfpr.td.tsi.ecommerce.produtos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CatalogoCliente {
	public static void main(String[] args) {
		try {
			// URL do endpoint
			URL url = new URL("http://localhost:8081/ecommerce.produtos/catalogo");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			// Configurações da requisição
			con.setRequestMethod("GET");
			con.setRequestProperty("Accept", "application/json");

			// Lê a resposta
			int status = con.getResponseCode();
			System.out.println("Status HTTP: " + status);

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// Exibe o JSON retornado
			System.out.println("Resposta do servidor:");
			System.out.println(response.toString());

			con.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
