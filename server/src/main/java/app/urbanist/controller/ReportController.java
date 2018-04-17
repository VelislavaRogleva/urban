package app.urbanist.controller;

import app.urbanist.model.binding.ReportAddModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {


    @PostMapping("/reports/add")
    public void addReport(@RequestBody ReportAddModel ram){


    }
}
