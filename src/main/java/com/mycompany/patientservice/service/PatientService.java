package com.mycompany.patientservice.service;

import com.mycompany.patientservice.dto.CreatePatientDto;
import com.mycompany.patientservice.dto.PatientDto;
import com.mycompany.patientservice.exception.DataAlreadyExistsException;
import com.mycompany.patientservice.exception.DataNotFoundException;
import com.mycompany.patientservice.model.Patient;
import com.mycompany.patientservice.respository.PatientRepository;
import com.mycompany.patientservice.respository.VoivodeshipRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final VoivodeshipRepository voivodeshipRepository;
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public PatientDto get(UUID uuid) {
        var patient = patientRepository.findByPatientUUID(uuid)
                .orElseThrow(() -> new DataNotFoundException(uuid.toString(), "User not found"));
        return modelMapper.map(patient, PatientDto.class);
    }

    @Transactional(readOnly = true)
    public PatientDto getWithContact(UUID uuid) {
        var patient = patientRepository.findWithContactByPatientUUID(uuid)
                .orElseThrow(() -> new DataNotFoundException(uuid.toString(), "User not found"));
        return modelMapper.map(patient, PatientDto.class);
    }

    @Transactional
    public PatientDto update(CreatePatientDto patientDto, UUID uuid) {
        Patient patient = patientRepository.findWithContactByPatientUUID(uuid)
                .orElseThrow(() -> new DataNotFoundException(uuid.toString(), "User not found"));
        modelMapper.map(patientDto, patient);
        patient.getContact().setVoivodeship(voivodeshipRepository.getOne(patient.getContact().getVoivodeship().getId()));

        return modelMapper.map(patientDto, PatientDto.class);
    }

    @Transactional
    public PatientDto create(CreatePatientDto patientDto) {
        if (patientRepository.existsByPatientUUID(patientDto.getPatientUUID())) {
            throw new DataAlreadyExistsException(patientDto.getPatientUUID().toString(), "User already exists");
        }
        Patient patient = modelMapper.map(patientDto, Patient.class);
        patient.getContact().setPatient(patient);
        patient.getContact().setVoivodeship(voivodeshipRepository.getOne(patientDto.getContact().getVoivodeship().getId()));

        patientRepository.save(patient);
        return modelMapper.map(patientDto, PatientDto.class);
    }

    @Transactional
    public void delete(UUID uuid) {
        patientRepository.deleteByPatientUUID(uuid);
    }
}
