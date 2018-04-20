package app.urbanist.service;

import app.urbanist.entity.Comment;
import app.urbanist.model.binding.CommentAddModel;
import app.urbanist.model.view.CommentViewModel;

import java.util.List;

public interface CommentService {
    Comment addComment(CommentAddModel cam);

    List<CommentViewModel> getComments(Long reportId);
}
