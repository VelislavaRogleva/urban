package app.urbanist.service;

import app.urbanist.model.binding.CommentAddModel;
import app.urbanist.model.view.CommentViewModel;

import java.util.List;

public interface CommentService {
    boolean addComment(CommentAddModel cam);

    List<CommentViewModel> getComments(Long reportId);
}
