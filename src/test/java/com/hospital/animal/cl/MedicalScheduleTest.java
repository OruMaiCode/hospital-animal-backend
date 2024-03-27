package com.hospital.animal.cl;

import com.hospital.animal.cl.dto.*;
import com.hospital.animal.cl.services.firebase.impl.MedicalScheduleServiceImpl;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
@SpringBootTest()
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MedicalScheduleTest {


    @Autowired
    MedicalScheduleServiceImpl medicalScheduleService;
    private static Day day;
    private static final Date date=new Date();
    private static final List<Event> events=new ArrayList<>();


    @BeforeAll
    public static void setup(){
        day= new Day();
        day.setDate(new Date());
        day.setEvents(new ArrayList<>());

    }
    @Order(1)
    @Test
    void createMedicalSchedule() throws Exception {
        day = medicalScheduleService.create(day);
        Assertions.assertNotNull(day.getUid());
    }
    @Order(2)
    @Test
    void getAllMedicalSchedule() throws InterruptedException, ExecutionException {
        List<Day> days = medicalScheduleService.getAll();
        Assertions.assertFalse(days.isEmpty());
        days.stream().filter(d->d.getUid().equals(day.getUid())).forEach(c->{
            Assertions.assertEquals(date.toString(),c.getDate().toString());
            Assertions.assertEquals(events,c.getEvents());
            Assertions.assertNotNull(c.getUid());
        });
    }
    @Order(3)
    @Test
    void getMedicalSchedule() throws InterruptedException, ExecutionException {
        Day day1 = medicalScheduleService.getByUid(day.getUid());
        Assertions.assertEquals(date.toString(),day1.getDate().toString());
        Assertions.assertEquals(events,day1.getEvents());
        Assertions.assertNotNull(day1.getUid());
        Assertions.assertThrows(RuntimeException.class,()->{
            medicalScheduleService.getByUid(null);
        });
    }

    @Order(4)
    @Test
    void editMedicalSchedule() throws InterruptedException, ExecutionException {
        Date date1 = day.getDate();
         date1.setMinutes(day.getDate().getMinutes()+10);
        day.setDate(date1);
        medicalScheduleService.update(day);
        Day day1 = medicalScheduleService.getByUid(day.getUid());
        Assertions.assertEquals(day1.getDate(),date1);
        day1.setDate(day.getDate());
        medicalScheduleService.update(day1);
        Assertions.assertEquals(day1.getDate(),day.getDate());
    }

    @Order(5)
    @Test
    void deleteMedicalSchedule() throws InterruptedException {
        Assertions.assertTrue(medicalScheduleService.delete(day.getUid()));
    }
}
