package com.hospital.animal.cl.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Date;

@Data
@NoArgsConstructor
public class Event {
    private String uid;
    private String description;
    private List<String> reasons;
    private Boolean emergency;
    private Boolean commissionPayed;
    private Date date;
    private String start;
    private String end;
    private User scheduledBy;
    private Client client;
    private Patient patient;
    private Professional professional;
    private Establishment ownerOf;

}
