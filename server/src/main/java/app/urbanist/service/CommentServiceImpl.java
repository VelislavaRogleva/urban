package app.urbanist.service;

import app.urbanist.entity.Comment;
import app.urbanist.entity.Report;
import app.urbanist.model.binding.CommentAddModel;
import app.urbanist.model.view.CommentViewModel;
import app.urbanist.repository.CommentRepository;
import app.urbanist.repository.ReportRepository;
import app.urbanist.repository.UserRepository;
import app.urbanist.util.ModelParser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, ReportRepository reportRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
    }

    @Override
    public boolean addComment(CommentAddModel cam) {
        Comment comment = new Comment();
        comment.setContent(cam.getContent());
        comment.setPublishedOn(new Date());
        comment.setReport(this.reportRepository.getOne(cam.getReportId()));
        comment.setUser(this.userRepository.getOne(cam.getUserId()));

        this.commentRepository.save(comment);

        return true;
    }

    @Override
    public List<CommentViewModel> getComments(Long reportId) {
        Optional<Report> opt = this.reportRepository.findById(reportId);
        if (!opt.isPresent()) return null;

        List<Comment> comments = this.commentRepository.findAllByReportOrderByPublishedOn(opt.get());

        List<CommentViewModel> commentViewModels = new ArrayList<>();
        for (Comment comment : comments) {
            CommentViewModel cvm = ModelParser.getInstance().map(comment, CommentViewModel.class);
            cvm.setUsername(comment.getUser().getUsername());
            commentViewModels.add(cvm);
        }

        return commentViewModels;
    }
}
