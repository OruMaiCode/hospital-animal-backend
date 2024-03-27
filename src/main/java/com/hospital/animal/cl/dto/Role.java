package com.hospital.animal.cl.dto;



public enum Role {
     CLIENT("Client"),
    PROFESSIONAL("Professional");
    public String label;
    private Role(String label){
        this.label=label;
    }
}
