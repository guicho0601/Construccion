package com.example.construccion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSqlite extends SQLiteOpenHelper{
	
	 private static final String __DATABASE = "dbTest";
	 private static final int __VERSION = 1;
	
	 //Instrucción SQL para crear las tablas 
	 private final String sql = "CREATE TABLE 'proyecto' ('idproyecto' INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , 'nombre' varchar, 'idubicacion' integer, 'idestado' integer, 'encargado' varchar, 'cantidad_empleados' integer DEFAULT 0, 'costo_aproximado' DOUBLE DEFAULT 0, 'tiempo_aproximado' integer DEFAULT 0)";

	 /**
	 * Constructor de clase
	 * */
	 public AdminSqlite(Context context) {  
	  super( context, __DATABASE, null, __VERSION );  
	 }

	 @Override
	 public void onCreate(SQLiteDatabase db) {  
	   db.execSQL( sql );   
	 }

	 @Override
	 public void onUpgrade( SQLiteDatabase db,  int oldVersion, int newVersion ) {  
	  if ( newVersion > oldVersion )
	  {
	   //elimina tabla
	   db.execSQL( "DROP TABLE IF EXISTS proyecto ");
	   //y luego creamos la nueva tabla
	   db.execSQL( sql );
	  }
	 }
}
