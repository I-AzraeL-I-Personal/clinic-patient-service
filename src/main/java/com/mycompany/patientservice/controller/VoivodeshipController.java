package com.mycompany.patientservice.controller;

import com.mycompany.patientservice.dto.VoivodeshipDto;
import com.mycompany.patientservice.service.VoivodeshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class VoivodeshipController {

    private final VoivodeshipService voivodeshipService;

    @GetMapping("/voivodeships")
    public ResponseEntity<List<VoivodeshipDto>> getAllVoivodeships() {
        return ResponseEntity.ok(voivodeshipService.getAllVoivodeships());
    }
}
