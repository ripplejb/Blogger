package blogger.services.comments;

import blogger.models.Comment;
import io.reactivex.Maybe;

import java.util.List;

public interface CommentsService {
    List<Comment> getLatestComments(Long commentId);
    Comment save(String comment, String userEmail, Long parentId);
}
