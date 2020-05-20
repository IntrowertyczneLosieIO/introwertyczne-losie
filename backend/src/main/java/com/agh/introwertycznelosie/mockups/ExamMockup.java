package com.agh.introwertycznelosie.mockups;

import com.agh.introwertycznelosie.data.Exam;
import com.agh.introwertycznelosie.data.Major;
import com.agh.introwertycznelosie.data.RecruitmentCycle;
import com.agh.introwertycznelosie.services.MajorService;
import com.agh.introwertycznelosie.services.RecruitmentCycleService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ExamMockup {

    public ExamMockup(){}

    private String name;
    private String major;

    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public Long getRecruitmentCycleId() {
        return recruitmentCycleId;
    }

    private String startDate;
    private String endDate;
    private Long recruitmentCycleId;

    public Long getId() {
        return id;
    }

    private Long id;

    public ExamMockup(Exam exam)
    {
        this.id = exam.getId();
        this.name = exam.getName();
        this.major = exam.getMajor().getFullName();
        this.startDate = exam.getStartDate().toString().substring(0,10);
        this.endDate = exam.getEndDate().toString().substring(0,10);
        this.recruitmentCycleId = exam.getRecruitmentCycle().getId();
    }

    public Exam mockToExam(RecruitmentCycleService recruitmentCycleService, MajorService majorService)
    {
        Exam exam = new Exam();
        exam.setName(name);
        Major major = majorService.findByFullName(this.major);

        exam.setMajor(major);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            exam.setStartDate(sdf.parse(startDate));
            exam.setEndDate(sdf.parse(endDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        RecruitmentCycle recruitmentCycle = null;
        //TODO set recruitmentCycle after front can handle it
        if (recruitmentCycleId!=null) {
            recruitmentCycle = recruitmentCycleService.get(recruitmentCycleId);
        }
        if (recruitmentCycle==null)
        {
            recruitmentCycle = new RecruitmentCycle();
            recruitmentCycle = recruitmentCycleService.save(recruitmentCycle);
        }
        exam.setRecruitmentCycle(recruitmentCycle);
        return exam;
    }
}
