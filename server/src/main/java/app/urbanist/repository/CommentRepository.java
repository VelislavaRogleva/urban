package app.urbanist.repository;

import app.urbanist.entity.Comment;
import app.urbanist.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

    List<Comment> findAllByReportOrderByPublishedOn(Report report);
}
