package shiseido.repositories.interfaces;

import com.sun.istack.NotNull;
import shiseido.models.Comment;
import shiseido.models.User;

import java.util.List;

public interface CommentsRepository {
    List<Comment> getAllLatest(int max);
    List<Comment> getAllLatestChildComments(int max, int commentId);
    Comment getById(int id);
    Comment save(@NotNull String comment, @NotNull User author, Comment parent);
}
