package app.urbanist;

import app.urbanist.entity.Report;
import app.urbanist.entity.User;
import app.urbanist.model.binding.CommentAddModel;
import app.urbanist.model.view.CommentViewModel;
import app.urbanist.repository.CommentRepository;
import app.urbanist.repository.ReportRepository;
import app.urbanist.repository.UserRepository;
import app.urbanist.service.CommentService;
import app.urbanist.service.CommentServiceImpl;
import app.urbanist.service.ReportService;
import app.urbanist.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
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

    private static final Long reportId = 1L;
    private static final Long userId = 1L;
    private static final Long invalidId = 18L;

    @Mock
    private CommentRepository commentRepository;
    @Mock
    private UserService userService;
    @Mock
    private ReportService reportService;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Before
    public void setUp() {

        User user = new User();
        Report report = new Report();

        when(this.commentRepository.save(any())).thenAnswer(i -> i.getArgument(0));
        when(this.userService.getOne(userId)).thenReturn(user);
        when(this.reportService.getOne(reportId)).thenReturn(report);

        when(this.userService.getOne(invalidId)).thenReturn(null);
        when(this.reportService.getOne(invalidId)).thenReturn(null);
    }

    @Test
    public void testAddComment_withValidComment_shouldReturnTrue() {

        CommentAddModel cam = new CommentAddModel();
        cam.setContent("Content");
        cam.setReportId(reportId);
        cam.setUserId(userId);

        boolean result = this.commentService.addComment(cam);

        assertTrue("Comment wasn't added correctly", result);
    }

    @Test
    public void testAddComment_withInvalidReportId_shouldReturnFalse() {

        CommentAddModel cam = new CommentAddModel();
        cam.setContent("Content");
        cam.setReportId(invalidId);
        cam.setUserId(userId);

        boolean result = this.commentService.addComment(cam);

        assertEquals("Adding a comment to a non-existent report should return false", false, result);
    }

    @Test
    public void testAddComment_withInvalidUserId_shouldReturnFalse() {

        CommentAddModel cam = new CommentAddModel();
        cam.setContent("Content");
        cam.setReportId(reportId);
        cam.setUserId(invalidId);

        boolean result = this.commentService.addComment(cam);

        assertEquals("Adding a comment to a non-existent user should return false", false, result);
    }

    @Test
    public void testGetComments_withInvalidReportid_shouldReturnNull() {
        List<CommentViewModel> comments = this.commentService.getComments(invalidId);

        assertEquals("Getting comments from a non-existent report should return null", null, comments);
    }


}
