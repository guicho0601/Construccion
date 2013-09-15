package com.example.construccion;

import java.util.ArrayList;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Lista_proyectos extends Activity {
	
	private ArrayList<String> ids;
	private ListView list;
	private Button nuevo_boton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_proyectos);
		
		
		list = (ListView) findViewById(R.id.listView1);
		nuevo_boton = (Button) findViewById(R.id.button1);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                
                //Toast.makeText(getApplicationContext(),((TextView) view).getText(), Toast.LENGTH_SHORT).show();
            	int h = Integer.parseInt(ids.get(position+1));
            	consultar_proyecto(h);
            }
        });
		nuevo_boton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                crear_registro();
            }
        });

		llenar_lista();
	}
	
	public void consultar_proyecto(int id){
		Intent intent = new Intent(this,Ver_proyectos.class);
		startActivity(i);
	}
	
	public void llenar_lista(){
		SqliteConnect sq = new SqliteConnect(this);
		sq.abrir();
		ArrayList<String> nombres = sq.consultar_nombre();
		ids = sq.consultar_id();
		sq.cerrar();
		list.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nombres));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista_proyectos, menu);
		return true;
	}
	
	public void crear_registro(){
		Intent i = new Intent(this,Crear_proyecto.class);
		int s = 1;
		startActivityForResult(i, s);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    if (resultCode == RESULT_CANCELED) {
	        Toast.makeText(this, "Resultado cancelado", Toast.LENGTH_SHORT)
	                .show();
	    } else {
	        llenar_lista();
	        Toast t =Toast.makeText(this, "Registro guardado exitosamente", Toast.LENGTH_SHORT);
	        t.show();
	    }
	}

}
