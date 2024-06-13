/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example;

import java.math.BigDecimal;

import org.apache.webbeans.config.WebBeansContext;
import org.apache.webbeans.spi.ContainerLifecycle;
import org.example.db.Book;
import org.example.servicios.IBook;

import com.google.gson.Gson;

import io.helidon.webserver.WebServer;
import jakarta.enterprise.inject.spi.CDI;

public class App {
    private static ContainerLifecycle lifecycle = null;

    public static void main(String[] args) {
        lifecycle = WebBeansContext.currentInstance().getService(ContainerLifecycle.class);
        lifecycle.startApplication(null);

        IBook servicio = CDI.current().select(IBook.class).get();

        // // IBook servicio = WebBeansContext.currentInstance().getBeanManagerBean()

        Book book = new Book();
        book.setId(1);
        book.setTitle("El alquimista");
        book.setAuthor("Paulo Coello");
        book.setIsbn("2323kiiei");
        book.setPrice(new BigDecimal(3));
        servicio.insert(book);

        System.out.println("Insertado");

        Gson gson = new Gson();
        servicio.buscarTodos().forEach(b -> System.out.println(b.getTitle()));

       WebServer.builder()
                .routing(it -> it
                        .get("/books", (req, res) -> res.send(gson.toJson(servicio.buscarTodos())))
                        .get("/books/{id}", (req, res) -> res.send(gson.toJson(servicio.buscarPorId(1))))
                        // .post("/books", (req, res) -> {
                        //     // Handle POST request for creating a book
                        //     Mono<Book> bookMono = req.body().as(Book.class) // Parse request body as Book object
                        //             .flatMap(book -> servicio.createBook(book)) // Call service method to create book
                        //             .onErrorResume(throwable -> Mono.error(new BadRequestException("Invalid book data"))); // Handle errors

                        //     bookMono.subscribe(createdBook -> res.send(OK, gson.toJson(createdBook))); // Send response with created book
                        // })
                        )

                .port(8080)
                .build()
                .start();
        // shutdown();

    }

    public static void shutdown() {
        lifecycle.stopApplication(null);
    }
}
