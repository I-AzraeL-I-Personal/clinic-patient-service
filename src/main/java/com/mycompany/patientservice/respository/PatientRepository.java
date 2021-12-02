package com.mycompany.patientservice.respository;

import com.mycompany.patientservice.model.Patient;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @EntityGraph("patient.contact")
    Optional<Patient> findWithContactByPatientUUID(UUID patientUUID);

    Optional<Patient> findByPatientUUID(UUID patientUUID);
    boolean existsByPatientUUID(UUID patientUUID);
    void deleteByPatientUUID(UUID patientUUID);
}
