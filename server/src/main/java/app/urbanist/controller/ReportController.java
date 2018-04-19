package app.urbanist.controller;

import app.urbanist.model.binding.ReportAddModel;
import app.urbanist.model.view.ReportDetailsModel;
import app.urbanist.model.view.ReportViewModel;
import app.urbanist.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/reports/add")
    public void addReport(@Valid @RequestBody ReportAddModel ram){
        this.reportService.addNewReport(ram);
    }

    @GetMapping("/reports/all")
    public List<ReportViewModel> allReports() {
        return this.reportService.getAll();
    }

    @GetMapping("/reports/details/{id}")
    public ResponseEntity<?> reportDetails(@PathVariable("id") Long id) {

        ReportDetailsModel rdm = this.reportService.getOne(id);

        if (rdm == null) return new ResponseEntity<>("Report not found", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(rdm, HttpStatus.OK);
    }
}
