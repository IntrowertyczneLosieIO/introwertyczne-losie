package com.agh.introwertycznelosie.mockups;

import com.agh.introwertycznelosie.data.Exam;
import com.agh.introwertycznelosie.data.Major;
import com.agh.introwertycznelosie.data.RecruitmentCycle;
import com.agh.introwertycznelosie.services.MajorService;
import com.agh.introwertycznelosie.services.RecruitmentCycleService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ExamMockup {

    public ExamMockup(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getRecruitmentCycleId() {
        return recruitmentCycleId;
    }

    public void setRecruitmentCycleId(Long recruitmentCycleId) {
        this.recruitmentCycleId = recruitmentCycleId;
    }

    private String name;
    private String major;
    private Date startDate;
    private Date endDate;
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
        this.startDate = exam.getStartDate();
        this.endDate = exam.getEndDate();
        this.recruitmentCycleId = exam.getRecruitmentCycle().getId();
    }

    public Exam mockToExam(RecruitmentCycleService recruitmentCycleService, MajorService majorService)
    {
        Exam exam = new Exam();
        exam.setName(name);
        Major major = majorService.findByFullName(this.major);

        exam.setMajor(major);
        exam.setStartDate(startDate);
        exam.setEndDate(endDate);
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
