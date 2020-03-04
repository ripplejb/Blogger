package blogger.services.comments;

import blogger.models.Comment;
import io.reactivex.Maybe;

import java.util.List;

public interface CommentsService {
    Maybe<List<Comment>> getLatestComments();
    Maybe<List<Comment>> getLatestChildComments(Long commentId);
    Maybe<Comment> save(String comment, String userEmail, Long parentId);
}
