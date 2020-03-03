package blogger.mappers.implementations;

import blogger.mappers.interfaces.CommentContractMapper;
import blogger.models.Comment;
import blogger.service_contracts.CommentContract;

import javax.inject.Singleton;

@Singleton
public class CommentContractMapperImpl implements CommentContractMapper {

    @Override
    public CommentContract fromComment(Comment comment) {
        CommentContract commentContract = new CommentContract();
        commentContract.setComment(comment.getComment());
        commentContract.setCommentId(comment.getId());
        if (comment.getParent() != null)
            commentContract.setParentId(comment.getParent().getId());
        else
            commentContract.setParentId(0L);
        commentContract.setAuthor(comment.getAuthor().getName());
        return commentContract;
    }
}
