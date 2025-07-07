package br.com.api.Java.dto;

import br.com.api.Java.entities.Writer;
import java.util.List;
import java.util.stream.Collectors;

public class WriterResponseDto {
    private Long id;
    private String name;
    private List<BookResponseDto> books;

    public WriterResponseDto(Writer writer) {
        this.id = writer.getId();
        this.name = writer.getName();

        if (writer.getBooks() != null) {
            this.books = writer.getBooks().stream()
                    .map(book -> new BookResponseDto(
                            book.getId(),
                            book.getTitle(),
                            book.getPages(),
                            writer.getName()))
                    .collect(Collectors.toList());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookResponseDto> getBooks() {
        return books;
    }

    public void setBooks(List<BookResponseDto> books) {
        this.books = books;
    }
}
