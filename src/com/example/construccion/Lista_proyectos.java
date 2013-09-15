package com.example.construccion;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class Lista_proyectos extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_proyectos);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista_proyectos, menu);
		return true;
	}
	
	public void crear_registro(View view){
		System.out.println("Vamos a crear un registro");
	}

}
