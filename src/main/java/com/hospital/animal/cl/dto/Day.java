package com.hospital.animal.cl.dto;

import com.hospital.animal.cl.services.firebase.FirebaseRegister;
import lombok.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
public class Day extends FirebaseRegister {
   private List<Event> events;
   private Date date;

}
