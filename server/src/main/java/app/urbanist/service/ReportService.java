package app.urbanist.service;

import app.urbanist.entity.Report;
import app.urbanist.model.binding.ReportAddModel;
import app.urbanist.model.view.ReportDetailsModel;
import app.urbanist.model.view.ReportViewModel;

import java.util.List;

public interface ReportService {
    boolean addNewReport(ReportAddModel ram);

    List<ReportViewModel> getAll();

    ReportDetailsModel getReportDetails(Long id);

    Report getOne(Long id);
}
