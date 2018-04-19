package app.urbanist.service;

import app.urbanist.entity.Comment;
import app.urbanist.entity.Report;
import app.urbanist.entity.User;
import app.urbanist.model.binding.CommentAddModel;
import app.urbanist.model.view.CommentViewModel;
import app.urbanist.repository.CommentRepository;
import app.urbanist.repository.UserRepository;
import app.urbanist.util.ModelParser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ReportService reportService;
    private final UserService userService;

    public CommentServiceImpl(CommentRepository commentRepository, ReportService reportService, UserService userService) {
        this.commentRepository = commentRepository;
        this.reportService = reportService;
        this.userService = userService;
    }

    @Override
    public boolean addComment(CommentAddModel cam) {
        Comment comment = new Comment();
        comment.setContent(cam.getContent());
        comment.setPublishedOn(new Date());

        Report report = this.reportService.getOne(cam.getReportId());
        if (report == null) return false;

        User user = this.userService.getOne(cam.getUserId());
        if (user == null) return false;

        comment.setReport(this.reportService.getOne(cam.getReportId()));
        comment.setUser(this.userService.getOne(cam.getUserId()));

        this.commentRepository.save(comment);

        return true;
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
