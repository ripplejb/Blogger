package blogger.repositories.interfaces;

import com.sun.istack.NotNull;
import blogger.models.Comment;
import blogger.models.User;

import java.util.List;

public interface CommentsRepository {
    List<Comment> getAllLatest(int max);
    List<Comment> getAllLatestChildComments(int max, Long commentId);
    Comment getById(Long id);
    Comment save(@NotNull String comment, @NotNull User author, Comment parent);
}
