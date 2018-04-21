package app.urbanist.service;

import app.urbanist.entity.Comment;
import app.urbanist.entity.Report;
import app.urbanist.entity.User;
import app.urbanist.model.binding.CommentAddModel;
import app.urbanist.model.view.CommentViewModel;
import app.urbanist.repository.CommentRepository;
import app.urbanist.repository.UserRepository;
import app.urbanist.util.ModelParser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ReportService reportService;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, ReportService reportService, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.reportService = reportService;
        this.userRepository = userRepository;
    }

    @Override
    public Comment addComment(CommentAddModel cam) {
        Comment comment = new Comment();
        comment.setContent(cam.getContent());
        comment.setPublishedOn(new Date());

        Report report = this.reportService.getOne(cam.getReportId());
        if (report == null) return null;

        User user = this.userRepository.findByUsername((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if (user == null) return null;

        comment.setReport(this.reportService.getOne(cam.getReportId()));
        comment.setUser(user);

        return this.commentRepository.save(comment);
    }

    @Override
    public List<CommentViewModel> getComments(Long reportId) {
        Report report = this.reportService.getOne(reportId);
        if (report == null) return null;

        List<Comment> comments = this.commentRepository.findAllByReportOrderByPublishedOn(report);

        List<CommentViewModel> commentViewModels = new ArrayList<>();
        for (Comment comment : comments) {
            CommentViewModel cvm = ModelParser.getInstance().map(comment, CommentViewModel.class);
            cvm.setUsername(comment.getUser().getUsername());
            commentViewModels.add(cvm);
        }

        return commentViewModels;
    }
}
