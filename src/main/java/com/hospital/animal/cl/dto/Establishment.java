package com.hospital.animal.cl.dto;

import com.hospital.animal.cl.services.firebase.FirebaseRegister;
import lombok.*;

import java.util.List;
@NoArgsConstructor

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

public class Establishment extends FirebaseRegister {

    private Location location;
    private User owner;
    private Byte interval;
    private Boolean active;
    private OfficeHours officeHours;
    private String avatar;
    private String cellNumber;
    private List<String> specialities;

}
