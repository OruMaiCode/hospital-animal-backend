package com.hospital.animal.cl.dto;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class Location {
    @Getter
    @Setter
    private String address;
    private Coordinates coordinates;
}
