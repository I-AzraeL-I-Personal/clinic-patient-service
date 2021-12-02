package com.mycompany.patientservice.dto;

import com.mycompany.patientservice.model.Gender;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PatientDto {

    private UUID patientUUID;

    private String firstName;

    private String middleName;

    private String lastName;

    private String pesel;

    private Gender gender;

    private ContactDto contact;
}
