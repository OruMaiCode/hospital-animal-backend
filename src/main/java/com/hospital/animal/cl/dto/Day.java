package com.hospital.animal.cl.dto;

import lombok.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
public class Day extends DatabaseRegister {
   private List<Event> events;
   private Date date;

}
