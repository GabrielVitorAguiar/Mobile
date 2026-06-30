package com.example.mypaintaula;

import android.content.Context;

import java.util.ArrayList;

public class NotaController {

    private NotasDAO dao;

    public NotaController(Context context) {
        dao = new NotasDAO(context);
    }

    public void salvar(Nota nota) {
        dao.inserir(nota);
    }

    public ArrayList<Nota> listar() {
        return dao.listar();
    }

    public Nota buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    public void atualizar(Nota nota) {
        dao.atualizar(nota);
    }

    public void excluir(int id) {
        dao.excluir(id);
    }

}