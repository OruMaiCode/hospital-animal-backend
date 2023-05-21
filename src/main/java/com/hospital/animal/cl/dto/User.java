package com.hospital.animal.cl.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
public class User extends DatabaseRegister {
    @NotNull
    private String name ;
    @NotNull
    private String email ;
    private Integer age ;
    private String avatar ;

    
}
