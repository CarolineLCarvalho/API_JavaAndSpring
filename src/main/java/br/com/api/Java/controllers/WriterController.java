package br.com.api.Java.controllers;

import br.com.api.Java.dto.WriterDto;
import br.com.api.Java.dto.WriterResponseDto;
import br.com.api.Java.entities.Writer;
import br.com.api.Java.repositories.WriterRepository;
import br.com.api.Java.services.WriterService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/writers")
public class WriterController {
    private final WriterService writerService;
    private final WriterRepository writerRepository;

    public WriterController(WriterService writerService, WriterRepository writerRepository){
        this.writerService = writerService;
        this.writerRepository = writerRepository;
    }
    @GetMapping("/{id}")
    public ResponseEntity<WriterResponseDto> getWriter(@PathVariable Long id) {
       return ResponseEntity.ok(this.writerService.getWriter(id));
    }
    @GetMapping
    public ResponseEntity<List<WriterDto>> getWriters() {
        return ResponseEntity.ok(this.writerService.getWriters());
    }
    @PostMapping
    public ResponseEntity<Writer> createWriter(@RequestBody Writer writer) {
        Writer createdWriter = this.writerService.createWriter(writer);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdWriter.getId()).toUri();
        return ResponseEntity.created(uri).body(createdWriter);
    }
    @PutMapping("/{id}")
    public ResponseEntity<WriterDto> updateBook(@PathVariable Long id, @Valid @RequestBody WriterDto writerDto) {
        Writer updatedWriter = writerService.updateWriter(id, writerDto);
        WriterDto response = new WriterDto(updatedWriter);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWriter(@PathVariable Long id) {
        writerService.deleteWriter(id);
        return ResponseEntity.noContent().build();
    }

}
