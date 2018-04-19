package app.urbanist.service;

import app.urbanist.entity.Image;
import app.urbanist.entity.Report;
import app.urbanist.model.binding.ImageUploadModel;
import app.urbanist.model.binding.ReportAddModel;
import app.urbanist.model.view.ImageViewModel;
import app.urbanist.model.view.ReportDetailsModel;
import app.urbanist.model.view.ReportViewModel;
import app.urbanist.repository.ReportRepository;
import app.urbanist.repository.UserRepository;
import app.urbanist.util.ModelParser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;

    private final ImageService imageService;

    public ReportServiceImpl(ReportRepository reportRepository, UserRepository userRepository, ImageService imageService) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
        this.imageService = imageService;
    }

    @Override
    public boolean addNewReport(ReportAddModel ram) {
        Report report = ModelParser.getInstance().map(ram, Report.class);

        report.setUser(this.userRepository.findByUsername((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
        report.setPublishedOn(new Date());

        this.reportRepository.save(report);

        for (ImageUploadModel imageUploadModel : ram.getImages()) {
            Image image = ModelParser.getInstance().map(imageUploadModel, Image.class);
            image.setReport(report);
            this.imageService.save(image);
        }

        return true;
    }

    @Override
    public List<ReportViewModel> getAll() {

        List<Report> all = this.reportRepository.findAll();
        List<ReportViewModel> viewModels = new ArrayList<>();
        for (Report report : all) {
            ReportViewModel rvm = ModelParser.getInstance().map(report, ReportViewModel.class);
            rvm.setUsername(report.getUser().getUsername());
            viewModels.add(rvm);
        }

        return viewModels;
    }

    @Override
    public ReportDetailsModel getOne(Long id) {
        Optional<Report> opt = this.reportRepository.findById(id);
        if (!opt.isPresent()) return null;

        Report report = opt.get();

        ReportDetailsModel rdm = ModelParser.getInstance().map(report, ReportDetailsModel.class);

        rdm.setUsername(report.getUser().getUsername());

        List<ImageViewModel> imageViewModels = new ArrayList<>();

        for (Image image : report.getImages()) {

            String downloadLink = this.imageService.getDownloadLink(image.getFile());

            if (downloadLink != null) {
                ImageViewModel ivm = new ImageViewModel();
                ivm.setCaption(image.getCaption());
                ivm.setUrl(downloadLink);
                imageViewModels.add(ivm);
            }
        }

        rdm.setImages(imageViewModels);
        return rdm;
    }
}
