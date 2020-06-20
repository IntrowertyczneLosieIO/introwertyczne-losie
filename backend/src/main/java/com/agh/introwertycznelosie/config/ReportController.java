package com.agh.introwertycznelosie.config;

import com.agh.introwertycznelosie.data.Faculty;
import com.agh.introwertycznelosie.services.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ReportController {
    private int reportNo = 0;

    @Autowired
    FacultyService facultyService;

    private File createFile() {
        try {
            File file = new File("report" + this.reportNo + ".txt");
            if (file.createNewFile()) {
                System.out.println("Report created: " + file.getName());
                return file;
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }

    private void writeReport(File file, String report) {
        try {
            FileWriter myWriter = new FileWriter(file);
            myWriter.write(report);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private File create_faculty_report(Long id){
        Faculty currentFaculty = facultyService.get(id);
        String report = "";
        StringBuilder reportBuilder = new StringBuilder(report);
        reportBuilder.append("REPORT FOR FACULTY ")
                .append(currentFaculty.getName())

    }
}
