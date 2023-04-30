package com.hospital.animal.cl.dto;


import lombok.*;

import java.util.List;

@NoArgsConstructor

@Data
@EqualsAndHashCode(callSuper = false)

public class Client extends User{

    private Location location;
    private List<Patient> responsibleOf;


}
