package org.example.servicios;

import java.util.List;

import org.example.db.Book;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class BookImpl implements IBook{

    @PersistenceContext
    private EntityManager em;


    @Override
    public void insert(Book libro) {
        em.persist(libro);
        
    }

    @Override
    public List<Book> buscarTodos() {
        return em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }

    @Override
    public Book buscarPorId(Integer id) {
        return em.find(Book.class, id);
    }

    @Override
    public void eliminarPorId(Integer id) {
        Book book = this.buscarPorId(id);
        em.remove(book);
    }

    @Override
    public void actualizarPorId(Book book) {
        em.merge(book);
    }

}
