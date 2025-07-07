package br.com.api.Java.services;

import br.com.api.Java.dto.WriterDto;
import br.com.api.Java.dto.WriterResponseDto;
import br.com.api.Java.entities.Writer;
import br.com.api.Java.repositories.WriterRepository;
import jakarta.validation.Valid;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//regras da api com os metodos http
@Service
public class WriterService {
    private final WriterRepository writerRepository;

    public WriterService(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }
    public List<WriterDto> getWriters() {
        return this.writerRepository.findAll().stream()
                .map(writer -> new WriterDto(
                        writer.getId(),
                        writer.getName(),
                        writer.getBirthday()
                ))
                .collect(Collectors.toList());
    }

    public WriterResponseDto getWriter(Long id) {
        Writer writer = writerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Writer not found"));

        // Força o carregamento dos livros
        Hibernate.initialize(writer.getBooks());

        return new WriterResponseDto(writer);
    }
    public Writer createWriter(Writer writer) {
        return this.writerRepository.save(writer);
    }
    public Writer updateWriter(Long id, @Valid WriterDto writerDto) {
        Writer writer = writerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Writer not found with id: " + id));

        writer.setName(writerDto.getName());
        return writerRepository.save(writer);
    }

    public void deleteWriter(Long id) {
        Writer writer = writerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Writer not found with id: " + id));
        writerRepository.deleteById(writer.getId());
    }

}
