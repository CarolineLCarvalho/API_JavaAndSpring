package br.com.api.Java.dto;

import jakarta.validation.constraints.*;

public class BookRequestDto {
    @NotBlank
    private String title;

    @Min(1)
    private int pages;

    @NotNull
    private Long writerId;

    // Construtor padrão necessário para o Jackson
    public BookRequestDto() {
    }

    // Construtor com parâmetros
    public BookRequestDto(String title, int pages, Long writerId) {
        this.title = title;
        this.pages = pages;
        this.writerId = writerId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Long getWriterId() {
        return writerId;
    }

    public void setWriterId(Long writerId) {
        this.writerId = writerId;
    }
}