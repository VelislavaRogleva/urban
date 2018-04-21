package app.urbanist;

import app.urbanist.entity.Comment;
import app.urbanist.entity.Report;
import app.urbanist.entity.User;
import app.urbanist.model.binding.CommentAddModel;
import app.urbanist.model.view.CommentViewModel;
import app.urbanist.repository.CommentRepository;
import app.urbanist.repository.UserRepository;
import app.urbanist.service.CommentServiceImpl;
import app.urbanist.service.ReportService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CommentServiceTests {

    private static final Long REPORT_ID = 1L;
    private static final Long USER_ID = 1L;
    private static final Long INVALID_ID = 18L;

    @Mock
    private CommentRepository commentRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ReportService reportService;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Before
    public void setUp() {

        User user = new User();
        user.setId(USER_ID);
        Report report = new Report();
        report.setId(REPORT_ID);

        when(this.commentRepository.save(any())).thenAnswer(i -> i.getArgument(0));
        when(this.reportService.getOne(REPORT_ID)).thenReturn(report);

        when(this.userRepository.findByUsername(any())).thenReturn(user);

        when(this.reportService.getOne(INVALID_ID)).thenReturn(null);
    }

    @Test
    public void testAddComment_withValidComment_shouldReturnTrue() {

        CommentAddModel cam = new CommentAddModel();
        cam.setContent("Content");
        cam.setReportId(REPORT_ID);

        Comment result = this.commentService.addComment(cam);

        assertEquals("Comment's content wasn't added correctly", cam.getContent(), result.getContent());
        assertEquals("Comment's report id wasn't correctly added", cam.getReportId(), result.getReport().getId());
    }

    @Test
    public void testAddComment_withInvalidReportId_shouldReturnFalse() {

        CommentAddModel cam = new CommentAddModel();
        cam.setContent("Content");
        cam.setReportId(INVALID_ID);


        Comment result = this.commentService.addComment(cam);

        assertEquals("Adding a comment to a non-existent report should return null", null, result);
    }


    @Test
    public void testGetComments_withInvalidReportid_shouldReturnNull() {
        List<CommentViewModel> comments = this.commentService.getComments(INVALID_ID);

        assertEquals("Getting comments from a non-existent report should return null", null, comments);
    }


}
