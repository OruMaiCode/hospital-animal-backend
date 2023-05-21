package com.hospital.animal.cl.dto;

import lombok.*;

import java.util.List;
@NoArgsConstructor

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

public class Establishment extends DatabaseRegister {

    private Location location;
    private User owner;
    private Byte interval;
    private Boolean active;
    private OfficeHours officeHours;
    private String avatar;
    private String cellNumber;
    private List<String> specialities;
    private List<Professional> professionals;

}
