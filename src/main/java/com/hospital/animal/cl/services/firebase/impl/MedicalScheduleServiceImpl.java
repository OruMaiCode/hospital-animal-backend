package com.hospital.animal.cl.services.firebase.impl;

import com.hospital.animal.cl.dto.Day;
import com.hospital.animal.cl.dto.Event;
import com.hospital.animal.cl.services.firebase.commons.FirebaseModel;
import com.hospital.animal.cl.services.firebase.interfaces.FirebaseRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@NoArgsConstructor
@Service
public class MedicalScheduleServiceImpl implements FirebaseRepository<Day> {

    private static final  String outputPattern="yyyy-MM-dd";
    @Autowired
    private FirebaseModel<Day> firebaseModel;
    @Override
    public List<Day> getAll() throws ExecutionException, InterruptedException {
        return build().getAll();
    }

    @Override
    public Day getByUid(String uid) throws ExecutionException, InterruptedException {
        return this.build().getByUid(uid);
    }

    @Override
    public Day create(Day day) throws ExecutionException, InterruptedException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(outputPattern);
        String date=simpleDateFormat.format(day.getDate());
        return this.build().create(day,date);
    }

    public Day addEvent(Event event) throws ExecutionException, InterruptedException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(outputPattern);
        String date=simpleDateFormat.format(event.getDate());
        Day day = this.getByUid(date);
        if(day.getEvents().getFirst()==null){
            Day newDay=new Day();
            newDay.setEvents(List.of(event));
            newDay.setDate(new Date());
            newDay.setUid(date);
            this.create(newDay);
        }
        day.getEvents().add(event);
        return day;

    }
    @Override
    public Day update(Day day) throws ExecutionException, InterruptedException {
        return this.build().update(day);
    }

    @Override
    public Boolean delete(String uid) throws InterruptedException {

        return this.build().delete(uid);
    }
    public FirebaseModel<Day> build(){
        this.firebaseModel.build(Day.class);
        return this.firebaseModel;
    }
}
