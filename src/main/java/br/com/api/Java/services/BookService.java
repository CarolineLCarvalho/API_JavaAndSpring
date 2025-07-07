package br.com.api.Java.services;

import br.com.api.Java.dto.BookResponseDto;
import br.com.api.Java.entities.Book;
import br.com.api.Java.entities.Writer;
import br.com.api.Java.repositories.BookRepository;
import br.com.api.Java.repositories.WriterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final WriterRepository writerRepository;

    public BookService(BookRepository bookRepository, WriterRepository writerRepository) {
        this.bookRepository = bookRepository;
        this.writerRepository = writerRepository;
    }

    public List<BookResponseDto> getBooks() {
        return this.bookRepository.findAll().stream()
                .map(book -> new BookResponseDto(
                        book.getId(),
                        book.getTitle(),
                        book.getPages(),
                        book.getWriter().getName()  // apenas o nome do autor
                ))
                .collect(Collectors.toList());
    }

    public Book getBook(Long id) {
        return this.bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    public Book createBook(Book book) {
        Long writerId = book.getWriter().getId();
        Writer writer = writerRepository.findById(writerId)
                .orElseThrow(() -> new RuntimeException("Writer not found with id: " + writerId));
        book.setWriter(writer);
        return this.bookRepository.save(book);
    }

    public Book updateBook(Long id, Book book) {
        Book existingBook = getBook(id);
        existingBook.setTitle(book.getTitle());
        existingBook.setPages(book.getPages());

        if (book.getWriter() != null && !book.getWriter().getId().equals(existingBook.getWriter().getId())) {
            Writer newWriter = writerRepository.findById(book.getWriter().getId())
                    .orElseThrow(() -> new RuntimeException("Writer not found"));
            existingBook.setWriter(newWriter);
        }

        return bookRepository.save(existingBook);
    }

    public void deleteBook(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow();
        this.bookRepository.delete(book);
    }
}
