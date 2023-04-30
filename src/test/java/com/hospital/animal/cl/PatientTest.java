package com.hospital.animal.cl;

import com.hospital.animal.cl.dto.Coordinates;
import com.hospital.animal.cl.dto.Location;
import com.hospital.animal.cl.dto.Patient;
import com.hospital.animal.cl.services.impl.PatientServiceImpl;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;

@SpringBootTest()
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PatientTest {
    @Autowired
     PatientServiceImpl patientService;

    private static Patient patient;
    private static final String NAME="Capitán";
    private static final Integer AGE=4;
    public static final String AGE_UNIT="Años";
    public static final String SPECIE="Perro";
    public static final String RACE = "Quiltro";
    public static final String BIRTH_CONTROL = "Castrado";
    public static final Double WEIGHT=2.45;
    public static final String WEIGHT_UNIT="KG";
    public static final String ADDRESS="Address 1";
    public static final Double LATITUDE=-73.092;
    public static final Double LONGITUDE=-34.434;
    public static final Coordinates COORDINATES=new Coordinates(LATITUDE,LONGITUDE);
    public static final Location LOCATION= new Location(ADDRESS,COORDINATES);
    @BeforeAll
    static void setup(){
        patient = new Patient();
        patient.setName(NAME);
        patient.setAge(AGE);
        patient.setAgeUnit(AGE_UNIT);
        patient.setSpecie(SPECIE);
        patient.setRace(RACE);
        patient.setBirthControl(BIRTH_CONTROL);
        patient.setWeight(WEIGHT);
        patient.setWeightUnit(WEIGHT_UNIT);
        patient.setLocation(LOCATION);

    }
    @Order(1)
    @Test
    void createPatient() throws InterruptedException, ExecutionException {
        patient =patientService.create(patient);
        Assertions.assertNotNull(patient.getuID());
    }
}
