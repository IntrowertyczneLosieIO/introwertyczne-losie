package com.agh.introwertycznelosie;

import com.agh.introwertycznelosie.data.*;
import com.agh.introwertycznelosie.repositories.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@SpringBootApplication
public class IntrowertyczneLosieApplication {


    public static void main(String[] args) {
        SpringApplication.run(IntrowertyczneLosieApplication.class, args);
    }


    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    MajorRepository majorRepository;
    @Autowired
    ExamRepository examRepository;
    @Autowired
    RecruitmentRepository recruitmentRepository;
    @Autowired
    RecruitmentCycleRepository recruitmentCycleRepository;
    @Autowired
    SubexamRepository subexamRepository;

    private Faculty faculty1, faculty2;
    private Major major1, major2, major3;
    private Room room1, room2;
    private Person person1, person2, person3, person4, person5;
    private List<DateRange> availableDates1, availableDates2;
    private Recruitment recruitment1, recruitment2;
    private RecruitmentCycle recruitmentCycle1, recruitmentCycle2, recruitmentCycle3;
    private Exam exam1, exam2, exam3, exam4;
    private Subexam subexam1, subexam2, subexam3, subexam4, subexam5, subexam6;


    private void setDatabase() {
        availableDates1 = new LinkedList<>();
        availableDates1.add(new DateRange("2020-08-13 14:00", "2020-08-13 20:00"));
        availableDates1.add(new DateRange("2020-08-13 10:00", "2020-08-13 12:00"));
        availableDates2 = new LinkedList<>();
        availableDates2.add(new DateRange("2020-08-13 14:00", "2020-08-13 18:00"));
        availableDates2.add(new DateRange("2020-08-13 09:00", "2020-08-13 13:00"));
        room1 = new Room(150, 170, "D17", "1.38", availableDates1);
        room2 = new Room(100, 120, "D17", "2.34", availableDates2);
        faculty1 = new Faculty("Wydział Informatyki, Elektroniki i Telekomunikacji", "WIEiT");
        faculty2 = new Faculty("Wydział Matematyki Stosowanej", "WMS");
        person1 = new Person("Janina", "Wędlina", "666777888", "j.wedlina@agh.edu.pl");
        person2 = new Person("Agata", "Sałata", "111222333", "a.salata@agh.edu.pl");
        person3 = new Person("Jan", "Chrzan", "110000222", "j.chrzan@agh.edu.pl");
        person4 = new Person("Wojciech", "Korzeń", null, "w.korzen@agh.edu.pl");
        person5 = new Person("Jurek", "Ogórek", null, "j.ogorek@agh.edu.pl");
        major1 = new Major(faculty1, "Informatyka", "Inf", ModeOfStudy.fullTime, 170, person1, person2, true, "");
        major2 = new Major(faculty1, "Data Science", "DS", ModeOfStudy.partTime, 30, person3, null, true, "Kierunek dla wybranych");
        major3 = new Major(faculty2, "Teoria grafów", "Grafy", ModeOfStudy.partTime, 40, person4, person5, false, "");
        recruitment1 = new Recruitment("Rekrutacja LATO 2020/2021", 2020, Semester.winter);
        recruitment2 = new Recruitment("Rekrutacja ZIMA 2021/2022", 2021, Semester.summer);
        recruitmentCycle1 = new RecruitmentCycle(recruitment1, 1);
        recruitmentCycle2 = new RecruitmentCycle(recruitment1, 2);
        recruitmentCycle3 = new RecruitmentCycle(recruitment2, 1);
        exam1 = new Exam("EgzaminInf2.wiet", major1, new Date(2020, Calendar.AUGUST, 1), new Date(2020, Calendar.AUGUST, 2), recruitmentCycle1);
        exam2 = new Exam("EgzaminInf2.2.wiet", major1, new Date(2020, Calendar.AUGUST, 10), new Date(2020, Calendar.AUGUST, 11), recruitmentCycle2);
        exam3 = new Exam("EgzaminDS2.wiet", major2, new Date(2020, Calendar.AUGUST, 3), new Date(2020, Calendar.AUGUST, 4), recruitmentCycle1);
        exam4 = new Exam("Grafy2.ms", major3, new Date(2021, Calendar.JANUARY, 3), new Date(2021, Calendar.JANUARY, 4), recruitmentCycle3);
        subexam1 = new Subexam(exam1, room1, new Date(2020, Calendar.AUGUST, 1), LocalTime.parse("13:00"));
        subexam2 = new Subexam(exam1, room2, new Date(2020, Calendar.AUGUST, 2), LocalTime.parse("13:00"));
        subexam3 = new Subexam(exam2, room2, new Date(2020, Calendar.AUGUST, 10), LocalTime.parse("15:00"));
        subexam4 = new Subexam(exam2, room1, new Date(2020, Calendar.AUGUST, 11), LocalTime.parse("17:00"));
        subexam5 = new Subexam(exam3, room1, new Date(2020, Calendar.AUGUST, 3), LocalTime.parse("11:00"));
        subexam6 = new Subexam(exam4, room1, new Date(2021, Calendar.JANUARY, 3), LocalTime.parse("17:00"));
    }

    @Bean
    InitializingBean sendDatabase() {
        return () -> {
            setDatabase();
            facultyRepository.save(faculty1);
            facultyRepository.save(faculty2);
            roomRepository.save(room1);
            roomRepository.save(room2);
            majorRepository.save(major1);
            majorRepository.save(major2);
            majorRepository.save(major3);
            recruitmentRepository.save(recruitment1);
            recruitmentRepository.save(recruitment2);
            recruitmentCycleRepository.save(recruitmentCycle1);
            recruitmentCycleRepository.save(recruitmentCycle2);
            recruitmentCycleRepository.save(recruitmentCycle3);
            examRepository.save(exam1);
            examRepository.save(exam2);
            examRepository.save(exam3);
            examRepository.save(exam4);
            subexamRepository.save(subexam1);
            subexamRepository.save(subexam2);
            subexamRepository.save(subexam3);
            subexamRepository.save(subexam4);
            subexamRepository.save(subexam5);
            subexamRepository.save(subexam6);
        };
    }
}
