package com.example.construccion;

import java.util.Dictionary;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Ver_proyectos extends Activity {
	
	private int idproyecto;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ver_proyectos);
		Intent intent = getIntent();
        String message = intent.getStringExtra("id");
        int id = Integer.parseInt(message);
        set_values(id);
        idproyecto = id;
        Button b = (Button)findViewById(R.id.mod_button);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                modificar();
            }
        });
        b = (Button)findViewById(R.id.eli_button);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                eliminar();
            }
        });
	}
	
	private void modificar(){
		Intent i=getIntent();
		i.putExtra("resultado","3");
		i.putExtra("id", idproyecto+"");
		System.out.println("Mando: "+idproyecto);
		setResult(RESULT_OK,i);
		onBackPressed();
	}
	
	private void eliminar(){
		 AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
		 myAlertDialog.setTitle("Eliminar registro");
		 myAlertDialog.setMessage("¿Seguro que desea eliminar este registro?");
		 myAlertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

		  public void onClick(DialogInterface arg0, int arg1) {
			  eliminar_registro();
			  Intent i = getIntent();
			  i.putExtra("resultado", "2");
			  setResult(RESULT_OK,i);
			  onBackPressed();
		  }});
		 myAlertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		       
		  public void onClick(DialogInterface arg0, int arg1) {
			  
		  }});
		 myAlertDialog.show();
	}
	
	private void eliminar_registro(){
		SqliteConnect sq = new SqliteConnect(this);
		sq.abrir();
		sq.eliminar(idproyecto);
		sq.cerrar();
	}
	
	private void set_values(int id){
		SqliteConnect sq = new SqliteConnect(this);
		sq.abrir();
		Dictionary<String,String> d = sq.consulta(id);
		sq.cerrar();
		
		int u = Integer.parseInt(d.get("idubicacion"));
		int e = Integer.parseInt(d.get("idestado"));
		String es="",ub="";
		switch(u){
			case 1:
				es="Inagurado";
				break;
			case 2:
				es="En construccion";
				break;
			case 3:
				es="Finalizado";
				break;
		}
		switch(e){
			case 1:
				ub="Municipio";
				break;
			case 2:
				ub="Aldea";
				break;
			case 3:
				ub="Canton";
				break;
		}		
		TextView tv = (TextView)findViewById(R.id.text_nombre);
		tv.setText("Nombre: "+d.get("nombre"));
		tv = (TextView)findViewById(R.id.text_encargado);
		tv.setText("Encargado: "+d.get("encargado"));
		tv = (TextView)findViewById(R.id.text_ubicacion);
		tv.setText("Ubicacion: "+ub);
		tv = (TextView)findViewById(R.id.text_estado);
		tv.setText("Estado: "+es);
		tv = (TextView)findViewById(R.id.text_cantidad);
		tv.setText("No. Empleados: "+d.get("cantidad_empleados"));
		tv = (TextView)findViewById(R.id.text_costo);
		tv.setText("Costo aprox: "+d.get("costo_aproximado"));
		tv = (TextView)findViewById(R.id.text_tiempo);
		tv.setText("Tiempo aprox: "+d.get("tiempo_aproximado"));				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ver_proyectos, menu);
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
