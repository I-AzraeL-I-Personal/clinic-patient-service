package com.mycompany.patientservice.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Getter
@Setter
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "patient.contact",
                attributeNodes = @NamedAttributeNode("contact")
        )
})
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    @NotNull(message = "patient uuid cannot be null")
    private UUID patientUUID;

    @Column(nullable = false)
    @NotBlank(message = "firstName cannot be blank")
    @Size(max = 50, message = "firstName cannot be longer than {max}")
    private String firstName;

    @Size(max = 50, message = "middleName cannot be longer than {max}")
    private String middleName;

    @Column(nullable = false)
    @NotBlank(message = "lastName cannot be blank")
    @Size(max = 50, message = "lastName cannot be longer than {max}")
    private String lastName;

    @Column(nullable = false, unique = true)
    @NotNull(message = "pesel cannot be null")
    @PESEL
    private String pesel;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "gender cannot be null")
    private Gender gender;

    @OneToOne(mappedBy = "patient", optional = false, cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Contact contact;
}
