package com.hospital.animal.cl.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Professional extends User{
    private String role;
    private List<String> specialities;


}
