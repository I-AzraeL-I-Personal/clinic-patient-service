package com.mycompany.patientservice.dto;

import com.mycompany.patientservice.model.Gender;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
public class CreatePatientDto {

    @NotNull(message = "patient uuid cannot be null")
    private UUID patientUUID;

    @NotBlank(message = "firstName cannot be blank")
    @Size(max = 50, message = "firstName cannot be longer than {max}")
    private String firstName;

    @Size(max = 50, message = "middleName cannot be longer than {max}")
    private String middleName;

    @NotBlank(message = "lastName cannot be blank")
    @Size(max = 50, message = "lastName cannot be longer than {max}")
    private String lastName;

    @PESEL(message = "Invalid pesel format")
    @NotNull(message = "pesel cannot be null")
    private String pesel;

    @NotNull(message = "gender cannot be null")
    private Gender gender;

    @NotNull(message = "contact cannot be null")
    private ContactDto contact;
}
