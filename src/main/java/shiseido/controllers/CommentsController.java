package shiseido.controllers;

import com.fasterxml.jackson.annotation.JsonValue;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import shiseido.mappers.interfaces.CommentContractMapper;
import shiseido.models.Comment;
import shiseido.service_contracts.CommentContract;
import shiseido.services.comments.CommentsService;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CommentsController {

    @Inject
    private final CommentsService commentsService;

    @Inject
    private final CommentContractMapper commentContractMapper;


    private List<CommentContract> GetCommentContracts(List<Comment> comments) {
        ArrayList<CommentContract> commentContracts = new ArrayList<>();
        for (Comment comment: comments) {
            commentContracts.add(commentContractMapper.fromComment(comment));
        }
        return commentContracts;
    }

    public CommentsController(CommentsService commentsService,
                              CommentContractMapper commentsServiceContractMapper) {
        this.commentsService = commentsService;
        this.commentContractMapper = commentsServiceContractMapper;
    }

    @Secured(SecurityRule.IS_ANONYMOUS)
    @JsonValue
    @Get("/api/comments/{parentCommentId}")
    public HttpResponse<List<CommentContract>> get(Long parentCommentId) {
        List<Comment> comments;
        if (parentCommentId == 0) {
            comments = commentsService.getLatestComments();
        } else {
            comments = commentsService.getLatestChildComments(parentCommentId);
        }
        return HttpResponse.ok(GetCommentContracts(comments));
    }

    @Secured(SecurityRule.IS_AUTHENTICATED)
    @JsonValue
    @Post("/api/comments")
    public HttpResponse<CommentContract> post(@Body @Valid CommentContract commentContract, @Nullable Authentication authentication) {

        assert authentication != null;

        Comment comment = commentsService.save(commentContract.getComment(), authentication.getAttributes().get("email").toString(),
                commentContract.getParentId());

        return HttpResponse.created(commentContractMapper.fromComment(comment));
    }
}
