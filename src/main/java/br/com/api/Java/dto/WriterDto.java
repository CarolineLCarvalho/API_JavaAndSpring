package br.com.api.Java.dto;

import br.com.api.Java.entities.Writer;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class WriterDto {
    private Long id;

    @NotNull
    private String name;
    private LocalDate birthday;

    public WriterDto() {
    }

    public WriterDto(Long id, String name, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }

    public WriterDto(Writer writer) {
        this.id = writer.getId();
        this.name = writer.getName();
        this.birthday = writer.getBirthday();
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
