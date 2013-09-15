package com.example.construccion;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Crear_proyecto extends Activity {
	
	private Spinner estados,ubicaciones;
	private EditText nombres,encargado,cant,cost,tie;
	private Button guardar_boton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crear_proyecto);
		
		guardar_boton = (Button)findViewById(R.id.bot_save);
		
		estados = (Spinner) findViewById(R.id.spin_estados);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.estados, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		estados.setAdapter(adapter);
		
		ubicaciones = (Spinner) findViewById(R.id.spin_ubicacion);
		adapter = ArrayAdapter.createFromResource(this,R.array.ubicacion, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		ubicaciones.setAdapter(adapter);
		
		guardar_boton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                crear_registro();
            }
        });
		
	}
	
	private void get_values(){
		nombres=(EditText)findViewById(R.id.edit_nombre);
		encargado=(EditText)findViewById(R.id.edit_encargado);
		cant=(EditText)findViewById(R.id.edit_cantidad);
		cost=(EditText)findViewById(R.id.edit_costo);
		tie=(EditText)findViewById(R.id.edit_tiempo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.crear_proyecto, menu);
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
	
	private void crear_registro(){
		get_values();
		SqliteConnect sq = new SqliteConnect(this);
		sq.abrir();
		long h =sq.insertar(nombres.getText().toString(), ubicaciones.getSelectedItemPosition()+1, estados.getSelectedItemPosition()+1, encargado.getText().toString(), Integer.parseInt(cant.getText().toString()), Double.parseDouble(cost.getText().toString()),Integer.parseInt(tie.getText().toString()));
		System.out.println(h);
		sq.cerrar();
		Intent i = getIntent();
		i.putExtra("resultado", "1");
		setResult(RESULT_OK,i);
		onBackPressed();
	}

}
