package blogger.repositories.interfaces;

import com.sun.istack.NotNull;
import blogger.models.Comment;
import blogger.models.User;
import io.reactivex.Maybe;

import java.util.List;

public interface CommentsRepository {
    Maybe<List<Comment>> getAllLatest(int max);
    Maybe<List<Comment>> getAllLatestChildComments(int max, Long commentId);
    Maybe<Comment> getById(Long id);
    Maybe<Comment> save(@NotNull String comment, @NotNull User author, Comment parent);
}
