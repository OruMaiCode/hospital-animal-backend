package com.hospital.animal.cl;

import com.hospital.animal.cl.dto.Coordinates;
import com.hospital.animal.cl.dto.Location;
import com.hospital.animal.cl.dto.Patient;
import com.hospital.animal.cl.services.firebase.impl.PatientServiceImpl;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
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
    void createPatient() throws InterruptedException {
        patient =patientService.create(patient);
        Assertions.assertNotNull(patient.getUid());
    }
    @Order(2)
    @Test
    void getPatient() throws InterruptedException, ExecutionException {
        Patient patient1 = patientService.get(patient.getUid());
        Assertions.assertEquals(NAME,patient1.getName());
        Assertions.assertEquals(AGE,patient1.getAge());
        Assertions.assertEquals(AGE_UNIT,patient1.getAgeUnit());
        Assertions.assertEquals(SPECIE,patient1.getSpecie());
        Assertions.assertEquals(RACE,patient1.getRace());
        Assertions.assertEquals(BIRTH_CONTROL,patient1.getBirthControl());
        Assertions.assertEquals(WEIGHT,patient1.getWeight());
        Assertions.assertEquals(WEIGHT_UNIT,patient1.getWeightUnit());
        Assertions.assertEquals(LOCATION,patient1.getLocation());
    }

    @Order(3)
    @Test
    void getAllPatients() throws InterruptedException, ExecutionException {
        List<Patient> patients = patientService.getAll();
        Assertions.assertFalse(patients.isEmpty());
        patients.forEach(patient->{
            Assertions.assertEquals(NAME,patient.getName());
            Assertions.assertEquals(AGE,patient.getAge());
            Assertions.assertEquals(AGE_UNIT,patient.getAgeUnit());
            Assertions.assertEquals(SPECIE,patient.getSpecie());
            Assertions.assertEquals(RACE,patient.getRace());
            Assertions.assertEquals(BIRTH_CONTROL,patient.getBirthControl());
            Assertions.assertEquals(WEIGHT,patient.getWeight());
            Assertions.assertEquals(WEIGHT_UNIT,patient.getWeightUnit());
            Assertions.assertEquals(LOCATION,patient.getLocation());
        });
    }

    @Order(4)
    @Test
    void editPatient() throws InterruptedException, ExecutionException {
        Integer age = patient.getAge();
        Integer newAge = age+10;
        patient.setAge(newAge);
        patientService.update(patient);
        Patient patient1 = patientService.get(patient.getUid());
        Assertions.assertEquals(patient1.getAge(),newAge);
        patient1.setAge(age);
        patientService.update(patient1);
        Assertions.assertEquals(patient1.getAge(),age);
    }
    @Order(5)
    @Test
    void deletePatient() throws InterruptedException {
        Assertions.assertTrue(patientService.delete(patient.getUid()));
    }
}
