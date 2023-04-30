package com.hospital.animal.cl.dto;



public enum Entity {
     CLIENT("Client"),
    PROFESSIONAL("Professional");
    private String label;
    private Entity(String label){
        this.label=label;
    }
}
