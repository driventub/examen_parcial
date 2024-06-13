package org.example.servicios;

import java.util.List;

import org.example.db.Book;

public interface IBook {
    void insert(Book libro);
    List<Book> buscarTodos();
    Book buscarPorId(Integer id);
    void eliminarPorId(Integer id);
    void actualizarPorId(Book book);
}
