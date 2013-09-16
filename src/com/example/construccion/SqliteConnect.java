package com.example.construccion;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SqliteConnect{
	
	AdminSqlite sqliteHelper;
	SQLiteDatabase db;

	 /** Constructor de clase */
	 public SqliteConnect(Context context)
	 {
		 sqliteHelper = new AdminSqlite( context );
	 }

	 /** Abre conexion a base de datos */
	 public void abrir(){
		 db = sqliteHelper.getWritableDatabase();   
	 }

	 /** Cierra conexion a la base de datos */
	 public void cerrar()
	 {
		 sqliteHelper.close();  
	 }
	 
	 public ArrayList<String> consultar_nombre(){
		 ArrayList<String> lista = new ArrayList<String>();
		 Cursor cursor = db.query("proyecto", new String[]{"nombre"}, null, null, null, null, null);
		 if( cursor.moveToFirst() )
		  {
		   do{
			   lista.add(cursor.getString(0)); 
		   }while( cursor.moveToNext() );
		  }
		 return lista;
	 }
	 
	 public ArrayList<String> consultar_id(){
		 ArrayList<String> lista = new ArrayList<String>();
		 Cursor cursor = db.query("proyecto", new String[]{"idproyecto"}, null, null, null, null, null);
		 if( cursor.moveToFirst() )
		  {
		   do{
			   lista.add(cursor.getString(0)); 
		   }while( cursor.moveToNext() );
		  }
		 return lista;
	 }
	 
	 public long insertar( String nombre,int ubicacion,int estado, String encargado, int cantidad,double costo,int tiempo)
	 {
	  
		 ContentValues contentValues = new ContentValues();
		 contentValues.put( "nombre" , nombre);
		 contentValues.put( "encargado", encargado);
		 contentValues.put( "idubicacion" , ubicacion);
		 contentValues.put("idestado", estado);
		 contentValues.put("cantidad_empleados", cantidad);
		 contentValues.put("costo_aproximado", costo);
		 contentValues.put("tiempo_aproximado", tiempo);
	                      //table, nullColumnHack, values
	  	return db.insert( "proyecto" , null, contentValues );
	 }
	 
	 public Dictionary<String,String> consulta(int id){
		 Dictionary<String,String> d=new Hashtable<String, String>();
		 Cursor cursor = db.query("proyecto", new String[]{"nombre","encargado","idubicacion","idestado","cantidad_empleados","costo_aproximado","tiempo_aproximado"}, "idproyecto="+id, null, null, null, null);
		 if( cursor.moveToFirst() )
		  {
		   do{
			   for(int i=0;i<cursor.getColumnCount();i++){
				   d.put(cursor.getColumnName(i), cursor.getString(i));
			   }
		   }while( cursor.moveToNext() );
		  }
		 return d;
	 }
	 
	 public void eliminar(int id){
		 db.delete( "proyecto" , "idproyecto=" + id ,  null);
	 }
	 
	 public void modificar(int id,String nombre,int ubicacion,int estado, String encargado, int cantidad,double costo,int tiempo){
		 ContentValues contentValues = new ContentValues();
		 contentValues.put( "nombre" , nombre);
		 contentValues.put( "encargado", encargado);
		 contentValues.put( "idubicacion" , ubicacion);
		 contentValues.put("idestado", estado);
		 contentValues.put("cantidad_empleados", cantidad);
		 contentValues.put("costo_aproximado", costo);
		 contentValues.put("tiempo_aproximado", tiempo);
		 db.update("proyecto", contentValues,"idproyecto="+id ,null);
	 }
}
