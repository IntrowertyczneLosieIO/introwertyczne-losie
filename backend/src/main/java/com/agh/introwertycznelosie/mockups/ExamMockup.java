package com.agh.introwertycznelosie.mockups;

import com.agh.introwertycznelosie.data.Exam;
import com.agh.introwertycznelosie.data.Major;
import com.agh.introwertycznelosie.data.ModeOfStudy;
import com.agh.introwertycznelosie.data.RecruitmentCycle;
import com.agh.introwertycznelosie.services.MajorService;
import com.agh.introwertycznelosie.services.RecruitmentCycleService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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

    public String getModeOfStudy() {
        return modeOfStudy;
    }

    public void setModeOfStudy(String modeOfStudy) {
        this.modeOfStudy = modeOfStudy;
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
    //TODO remove JsonProperty after changing on front
    @JsonProperty("faculty")
    private String major;
    private String modeOfStudy;
    private Date startDate;
    private Date endDate;
    private Long recruitmentCycleId;

    public ExamMockup(Exam exam)
    {
        this.name = exam.getName();
        this.major = exam.getMajor().getFullName();
        this.modeOfStudy = exam.getModeOfStudy().toString();
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
        exam.setModeOfStudy(ModeOfStudy.fromString(modeOfStudy));
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
