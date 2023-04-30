package com.hospital.animal.cl.dto;

import lombok.*;

import java.util.List;


@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class OfficeHours {
    private Boolean urgency;
    private String start;
    private String end;
    private List<String> days;
}
