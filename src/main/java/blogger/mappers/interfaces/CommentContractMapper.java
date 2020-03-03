package blogger.mappers.interfaces;

import blogger.models.Comment;
import blogger.service_contracts.CommentContract;

public interface CommentContractMapper {
    CommentContract fromComment(Comment comment);
}
