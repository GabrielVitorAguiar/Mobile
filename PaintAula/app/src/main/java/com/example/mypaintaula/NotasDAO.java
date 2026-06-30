package com.example.mypaintaula;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class NotasDAO {

    private SQLiteDatabase banco;

    public NotasDAO(Context context){

        banco = context.openOrCreateDatabase(
                "banco",
                Context.MODE_PRIVATE,
                null
        );

        banco.execSQL(
                "CREATE TABLE IF NOT EXISTS notas(" +
                        "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "titulo TEXT," +
                        "texto TEXT)"
        );

    }

    public void inserir(Nota nota){

        ContentValues values = new ContentValues();

        values.put("titulo", nota.getTitulo());
        values.put("texto", nota.getTexto());

        banco.insert("notas", null, values);

    }

    public ArrayList<Nota> listar(){

        ArrayList<Nota> lista = new ArrayList<>();

        Cursor cursor = banco.rawQuery(
                "SELECT _id, titulo, texto FROM notas ORDER BY _id DESC",
                null
        );

        if(cursor.moveToFirst()){

            do{

                Nota nota = new Nota();

                nota.setId(cursor.getInt(0));
                nota.setTitulo(cursor.getString(1));
                nota.setTexto(cursor.getString(2));

                lista.add(nota);

            }while(cursor.moveToNext());

        }

        cursor.close();

        return lista;

    }

    public Nota buscarPorId(int id){

        Cursor cursor = banco.rawQuery(
                "SELECT _id, titulo, texto FROM notas WHERE _id=?",
                new String[]{String.valueOf(id)}
        );

        Nota nota = null;

        if(cursor.moveToFirst()){

            nota = new Nota();

            nota.setId(cursor.getInt(0));
            nota.setTitulo(cursor.getString(1));
            nota.setTexto(cursor.getString(2));

        }

        cursor.close();

        return nota;

    }

    public void atualizar(Nota nota){

        ContentValues values = new ContentValues();

        values.put("titulo", nota.getTitulo());
        values.put("texto", nota.getTexto());

        banco.update(
                "notas",
                values,
                "_id=?",
                new String[]{String.valueOf(nota.getId())}
        );

    }

    public void excluir(int id){

        banco.delete(
                "notas",
                "_id=?",
                new String[]{String.valueOf(id)}
        );

    }

}