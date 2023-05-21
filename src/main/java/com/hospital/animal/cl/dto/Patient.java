package com.hospital.animal.cl.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class Patient extends DatabaseRegister {

    private Integer age ;
    @NotNull
    private String name;
    private String ageUnit;
    private String specie;
    private String race ;
    private String birthControl;
    private Double weight;
    private String weightUnit;
    private Location location;

}
