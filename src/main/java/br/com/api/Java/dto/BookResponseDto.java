package br.com.api.Java.dto;

import br.com.api.Java.entities.Book;

public class BookResponseDto {
        private Long id;
        private String title;
        private int pages;
        private String writerName;

        public BookResponseDto(Long id, String title, int pages, String writerName) {
            this.id = id;
            this.title = title;
            this.pages = pages;
            this.writerName = writerName;
        }
    public BookResponseDto(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.pages = book.getPages();
        this.writerName = book.getWriter().getName();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPages() {
        return pages;
    }

    public String getWriterName() {
        return writerName;
    }
}



