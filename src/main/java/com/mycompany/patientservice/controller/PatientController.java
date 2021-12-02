package com.mycompany.patientservice.controller;

import com.mycompany.patientservice.dto.CreatePatientDto;
import com.mycompany.patientservice.dto.PatientDto;
import com.mycompany.patientservice.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping(value = "/{uuid}")
    public ResponseEntity<PatientDto> get(@PathVariable UUID uuid) {
        return ResponseEntity.ok(patientService.get(uuid));
    }

    @GetMapping(value = "/{uuid}/with-contact")
    public ResponseEntity<PatientDto> getWithContact(@PathVariable UUID uuid) {
        return ResponseEntity.ok(patientService.getWithContact(uuid));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<PatientDto> update(@PathVariable UUID uuid, @Valid @RequestBody CreatePatientDto patientDto) {
        return ResponseEntity.ok(patientService.update(patientDto, uuid));
    }

    @PostMapping("")
    public ResponseEntity<PatientDto> create(@Valid @RequestBody CreatePatientDto patientDto) {
        return ResponseEntity.ok(patientService.create(patientDto));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
        patientService.delete(uuid);
        return ResponseEntity.ok().build();
    }
}
