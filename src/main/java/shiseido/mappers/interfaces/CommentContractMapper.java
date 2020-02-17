package shiseido.mappers.interfaces;

import shiseido.models.Comment;
import shiseido.models.User;
import shiseido.service_contracts.CommentContract;

public interface CommentContractMapper {
    CommentContract fromComment(Comment comment);
}
