package blogger.services.comments;

import blogger.models.Comment;

import java.util.List;

public interface CommentsService {
    List<Comment> getLatestComments();
    List<Comment> getLatestChildComments(Long commentId);
    Comment save(String comment, String userEmail, Long parentId);
}
