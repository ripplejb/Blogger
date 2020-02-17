package shiseido.service_contracts;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class CommentContract {
    private Long commentId;
    private String comment;
    private String author;
    private Long parentId;

    public CommentContract() {
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
