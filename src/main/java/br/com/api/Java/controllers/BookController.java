package br.com.api.Java.controllers;

import br.com.api.Java.dto.BookRequestDto;
import br.com.api.Java.dto.BookResponseDto;
import br.com.api.Java.entities.Book;
import br.com.api.Java.entities.Writer;
import br.com.api.Java.repositories.WriterRepository;
import br.com.api.Java.services.BookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final WriterRepository writerRepository;

    public BookController(BookService bookService, WriterRepository writerRepository) {
        this.bookService = bookService;
        this.writerRepository = writerRepository;
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBook(@PathVariable Long id) {
        Book book = this.bookService.getBook(id);
        BookResponseDto dto = new BookResponseDto(book);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getBooks() {
        List<BookResponseDto> books = bookService.getBooks()
                .stream()
                .toList();
        return ResponseEntity.ok(books);
    }
    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody BookRequestDto bookRequest) {
        Writer writer = writerRepository.findById(bookRequest.getWriterId())
                .orElseThrow(() -> new RuntimeException("Writer not found"));

        Book book = new Book();
        book.setTitle(bookRequest.getTitle());
        book.setPages(bookRequest.getPages());
        book.setWriter(writer);

        Book savedBook = bookService.createBook(book);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedBook.getId())
                .toUri();

        return ResponseEntity.created(uri).body(savedBook);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody BookRequestDto bookRequest) {
        Book existingBook = bookService.getBook(id);
        existingBook.setTitle(bookRequest.getTitle());
        existingBook.setPages(bookRequest.getPages());

        Book updatedBook = bookService.updateBook(id, existingBook);
        return ResponseEntity.ok(updatedBook);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}