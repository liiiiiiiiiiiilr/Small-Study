package je.project.service;

import je.project.domain.Report;
import je.project.domain.User;

import java.util.List;

public interface ReportService {
    void writeReport(Report report);
    Report geReportByID(int id);
    void deleteReportByID(int id);
    List<Report> getAllReport();
    void deleteAllReport();
    void update(Report report);
    void updateStatus(Report report);
    void insertRepair(Integer id);
    User getUser(Integer id);
}
