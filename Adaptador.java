package com.example.purebabd;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.purebabd.Titular;
import com.example.purebabd.R;

public class Adaptador extends ArrayAdapter<Titular> {

	Activity context;
	Titular[] datos;
	
	public Adaptador (Activity context, Titular[] datos) {
		super(context, R.layout.listedit, datos);
		this.datos = datos;
		this.context = context;
	}
	
	@Override
	public View getView (int position, View convertView, ViewGroup parent){
		
		LayoutInflater inflater = context.getLayoutInflater();
		View item = inflater.inflate(R.layout.listedit, null);
		
		TextView nombre = (TextView) item.findViewById(R.id.nombre);
		TextView disponible = (TextView) item.findViewById(R.id.disponible);
		ImageView imagen = (ImageView) item.findViewById(R.id.imagen);
		ImageView imgdisponible = (ImageView) item.findViewById(R.id.imgdisponible);
		
		nombre.setText(datos[position].getNombre());
		disponible.setText(datos[position].getDisponible());
		imagen.setImageResource(datos[position].getImagen());
		imgdisponible.setImageResource(datos[position].getImgdisponible());
		
		return item;		
		
	}

}
