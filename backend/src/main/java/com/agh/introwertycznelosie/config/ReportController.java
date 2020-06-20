package com.agh.introwertycznelosie.config;

import com.agh.introwertycznelosie.data.*;
import com.agh.introwertycznelosie.services.FacultyService;
import com.agh.introwertycznelosie.services.MajorService;
import com.agh.introwertycznelosie.services.SubexamService;
import com.qkyrie.markdown2pdf.Markdown2PdfConverter;
import com.qkyrie.markdown2pdf.internal.exceptions.ConversionException;
import com.qkyrie.markdown2pdf.internal.exceptions.Markdown2PdfLogicException;
import com.qkyrie.markdown2pdf.internal.reading.SimpleStringMarkdown2PdfReader;
import com.qkyrie.markdown2pdf.internal.writing.SimpleFileMarkdown2PdfWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/")
public class ReportController {

    Logger logger = LogManager.getLogger(ReportController.class);
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

    @Autowired
    MajorService majorService;
    @Autowired
    FacultyService facultyService;
    @Autowired
    SubexamService subexamService;

    @GetMapping(path = "report/major/pdf/{id}")
    public void download_major_report_pdf(@PathVariable Long id, HttpServletResponse response) throws IOException {
        File file = this.createFile("pdf");
        String report = create_major_report(id, true);
        this.writeReportPDF(file, report);

        response.setHeader("Content-disposition", "attachment; filename=raport_kierunek.pdf");


        OutputStream out = response.getOutputStream();
        if (file != null) {
            FileInputStream in = new FileInputStream(file);

            IOUtils.copy(in, out);
            out.close();
            in.close();
            file.delete();
        }
    }
    @GetMapping(path = "report/major/md/{id}")
    public void download_major_report_md(@PathVariable Long id, HttpServletResponse response) throws IOException {
        File file = this.createFile("md");
        String report = create_major_report(id, true);
        this.writeReportMD(file, report);

        response.setHeader("Content-disposition", "attachment; filename=raport_kierunek.md");


        OutputStream out = response.getOutputStream();
        if (file != null) {
            FileInputStream in = new FileInputStream(file);

            IOUtils.copy(in, out);
            out.close();
            in.close();
            file.delete();
        }
    }

    @GetMapping(path = "report/faculty/pdf/{id}")
    public void download_faculty_report_pdf(@PathVariable Long id, HttpServletResponse response) throws IOException {
        File file = this.createFile("pdf");
        String report = create_faculty_report(id);
        this.writeReportPDF(file, report);

        response.setHeader("Content-disposition", "attachment; filename=raport_wydzial.pdf");

        OutputStream out = response.getOutputStream();
        if (file != null) {
            FileInputStream in = new FileInputStream(file);

            IOUtils.copy(in, out);
            out.close();
            in.close();
            file.delete();
        }
    }

    @GetMapping(path = "report/faculty/md/{id}")
    public void download_faculty_report_md(@PathVariable Long id, HttpServletResponse response) throws IOException {
        File file = this.createFile("md");
        String report = create_faculty_report(id);
        this.writeReportMD(file, report);

        response.setHeader("Content-disposition", "attachment; filename=raport_wydzial.md");


        OutputStream out = response.getOutputStream();
        if (file != null) {
            FileInputStream in = new FileInputStream(file);

            IOUtils.copy(in, out);
            out.close();
            in.close();
            file.delete();
        }
    }


    private File createFile(String extension) {
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String reportNo = sdf.format(timestamp.getTime());
            File file = new File("reports/report" + reportNo + "." + extension);
            if (file.createNewFile()) {
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

    private void writeReportPDF(File file, String report) {
        byte[] bytes = report.getBytes(StandardCharsets.US_ASCII);

        String newReport = new String(bytes, StandardCharsets.UTF_8);
        try {
            Markdown2PdfConverter.
                    newConverter()
                    .readFrom(new SimpleStringMarkdown2PdfReader(newReport))
                    .writeTo(new SimpleFileMarkdown2PdfWriter(file))
                    .doIt();
        } catch (ConversionException | Markdown2PdfLogicException e) {
            e.printStackTrace();
        }
    }
    private void writeReportMD(File file, String report) {
        try {
        FileWriter myWriter = new FileWriter(file);
        myWriter.write(report);
        myWriter.close();
        } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        }
    }

    private String create_major_report(Long id, boolean header){
        Major major = majorService.get(id);
        String report = "";
        StringBuilder reportBuilder = new StringBuilder(report);
        if (header)
            reportBuilder.append("# RAPORT: ")
                .append(major.getFaculty().getName())
                .append("\n## Rekrutacja: ")
                .append(major.getRecruitment().getAcronym());
        reportBuilder
            .append("\n\n\n# Kierunek: ")
            .append(major.getFullName())
            .append(" (")
            .append(major.getShortName())
            .append(")\n")
            .append("## Tryb: ");
        if (major.getMode() == ModeOfStudy.fullTime)
            reportBuilder.append("dzienny\n\n");
        else
            reportBuilder.append("zaocznie\n\n");
        reportBuilder.append("### Osoby do kontaktu:\n")
                .append("1. ")
                .append(major.getContactPerson1().getFirstName())
                .append(" ")
                .append(major.getContactPerson1().getLastName())
                .append("\n\n\t**mail**: ")
                .append(major.getContactPerson1().getMail())
                .append(", **telefon**: ")
                .append(major.getContactPerson1().getPhoneNo())
                .append("\n");
        if (major.getContactPerson2() != null)
                reportBuilder.append("2. ")
                        .append(major.getContactPerson2().getFirstName())
                        .append(" ")
                        .append(major.getContactPerson2().getLastName())
                        .append("\n\n\t**mail**: ")
                        .append(major.getContactPerson2().getMail())
                        .append(", **telefon**: ")
                        .append(major.getContactPerson2().getPhoneNo())
                        .append("\n");
        reportBuilder.append("\n### Egzaminy:\n");
        for (Exam exam: major.getExams()) {
            reportBuilder.append("* **")
                    .append(exam.getName())
                    .append("**: ")
                    .append(exam.getStartDate().toString(), 0, 10)
                    .append(" - ")
                    .append(exam.getEndDate().toString(), 0, 10)
                    .append("\n\n");
            for (Subexam subexam: subexamService.getByExam_Id(exam.getId())) {
                reportBuilder.append("\t* Data: ")
                        .append(subexam.getDate().toString(), 0, 10)
                        .append(", ")
                        .append(subexam.getTime())
                        .append("\n\n\t\t**Sala**: ")
                        .append(subexam.getRoom().getLocalization())
                        .append(", ")
                        .append(subexam.getRoom().getNumber())
                        .append(" (")
                        .append(subexam.getRoom().getRecommendedCapacity())
                        .append(" osób)\n\n");
            }
        }
        if (header)
            logger.info("Created report for major " + major.toString());
        return reportBuilder.toString();
    }

    private String create_faculty_report(Long id) {
        Faculty faculty = facultyService.get(id);
        String report = "";
        StringBuilder reportBuilder = new StringBuilder(report);
        reportBuilder.append("# RAPORT: ")
                .append(faculty.getName())
                .append("\n## Rekrutacja: ")
                .append(faculty.getRecruitment().getAcronym())
                .append("\n");
        for (Major major: majorService.findByFacultyId(id)) {
            reportBuilder.append(create_major_report(major.getId(), false))
            .append("\n\n");
        }
        logger.info("Created report for major " + faculty.toString());
        return reportBuilder.toString();
    }

}
