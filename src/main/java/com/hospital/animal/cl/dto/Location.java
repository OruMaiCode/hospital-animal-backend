package com.hospital.animal.cl.dto;

import lombok.*;


@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Location {
    private String address;
    private Coordinates coordinates;
}
