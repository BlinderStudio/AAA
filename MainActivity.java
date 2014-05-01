package com.example.purebabd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	ListView lista;
	static TextView cargando;
	static ProgressBar progressbar;
	static Boolean cargado = false;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lista = (ListView) findViewById(R.id.listview);
		cargando = (TextView) findViewById(R.id.cargando);
		
		
		Tarea1 tarea1 = new Tarea1();
		tarea1.cargarContenido(getApplicationContext());
		tarea1.onPreExecute();
		tarea1.execute(lista);
	}
	
	static class Tarea1 extends AsyncTask<ListView, Void, ArrayAdapter<Titular>>{
		
		Context contexto;
		ListView lista;
		InputStream is;
		ArrayList<Titular> adaptador = new ArrayList<Titular>();
		
		public void cargarContenido(Context contexto){
			this.contexto = contexto;
		}
		
		@Override
		protected void onPreExecute(){
			
		}
		
		
		@Override
		protected ArrayAdapter<Titular> doInBackground(ListView... params) {

			lista = params[0];
			String resultado = "fallo";
			Titular cli;
			
			HttpClient cliente =  new DefaultHttpClient();
			HttpGet peticionGet = new HttpGet("http://decotv.netne.net/nacionales.php");
			
			try {
				HttpResponse response = cliente.execute(peticionGet);
				HttpEntity contenido = response.getEntity();
				is = contenido.getContent();
			} catch (ClientProtocolException e) {
				// TODO: handle exception
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			BufferedReader buferlector = new BufferedReader(new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();
			String linea = null;
			
			try {
				while ((linea = buferlector.readLine())!=null){
					sb.append(linea);
				}
			} catch (IOException e) {
				// TODO: handle exception
			}
			try {
				is.close();
			} catch (IOException e) {
				// TODO: handle exception
			}
			
			resultado = sb.toString();
			
			try {
				JSONArray arrayJson = new JSONArray(resultado);
				for (int i = 0; i < arrayJson.length(); i++) {
					JSONObject objetoJson = arrayJson.getJSONObject(i);
					cli = new Titular(objetoJson.getString("nombre"), objetoJson.getString("disponible"),objetoJson.getInt("imagen"),objetoJson.getInt("imgdisponible"), objetoJson.getString("rtmpurl"));
					adaptador.add(cli);
					System.out.println(objetoJson);
					
				}
				
				
			} catch (JSONException e) {
				// TODO: handle exception
			}
			
			ArrayAdapter<Titular> adaptador = new ArrayAdapter<Titular>(contexto, R.layout.listedit);
			
			return adaptador;
		}
		
		protected void onPostExecute(ArrayAdapter<Titular> adaptador){

			lista.setAdapter(adaptador);
			cargando.setText("");
			
			
			
			
			
		}
		
		
	}


}
