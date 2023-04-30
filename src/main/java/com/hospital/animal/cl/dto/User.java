package com.hospital.animal.cl.dto;

import com.hospital.animal.cl.services.firebase.FirebaseRegister;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
public class User extends FirebaseRegister {
    private String name ;
    private String email ;
    private Integer age ;
    private String avatar ;

    
}
