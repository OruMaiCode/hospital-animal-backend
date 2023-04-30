package com.hospital.animal.cl.dto;

import com.hospital.animal.cl.services.firebase.FirebaseRegister;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Patient extends FirebaseRegister {

    private String privatename;
    private Integer age ;
    private String ageUnit;
    private String specie;
    private String race ;
    private String birthControl;
    private Double weight;
    private String weightUnit;
    private Location location;

}
