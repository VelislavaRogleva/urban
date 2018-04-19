package app.urbanist.controller;

import app.urbanist.model.binding.CommentAddModel;
import app.urbanist.model.view.CommentViewModel;
import app.urbanist.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comments/add")
    public ResponseEntity<?> addComment(@Valid @RequestBody CommentAddModel cam){
        boolean result = this.commentService.addComment(cam);

        if (!result) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/comments/get/{id}")
    public ResponseEntity<?> getComments(@PathVariable Long id) {
        List<CommentViewModel> cvm = this.commentService.getComments(id);

        if (cvm == null) return new ResponseEntity<>("Report not found", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(cvm, HttpStatus.OK);
    }
}
