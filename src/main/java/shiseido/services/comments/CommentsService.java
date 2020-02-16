package shiseido.services.comments;

import shiseido.models.Comment;

import java.util.List;

public interface CommentsService {
    List<Comment> getLatestComments();
    List<Comment> getLatestChildComments(int commentId);
    Comment save(String comment, String userEmail, int parentId);
}
