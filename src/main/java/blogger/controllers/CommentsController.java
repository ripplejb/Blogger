package blogger.controllers;

import com.fasterxml.jackson.annotation.JsonValue;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import blogger.mappers.interfaces.CommentContractMapper;
import blogger.models.Comment;
import blogger.service_contracts.CommentContract;
import blogger.services.comments.CommentsService;
import io.reactivex.Maybe;

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
    public HttpResponse<Maybe<List<CommentContract>>> get(Long parentCommentId) {
        if (parentCommentId == 0) {
            return HttpResponse.ok(commentsService
                    .getLatestComments()
                    .map(this::GetCommentContracts)
                    .onErrorComplete());
        } else {
            return HttpResponse.ok(commentsService
                    .getLatestChildComments(parentCommentId)
                    .map(this::GetCommentContracts)
                    .onErrorComplete());
        }
    }

    @Secured(SecurityRule.IS_AUTHENTICATED)
    @JsonValue
    @Post("/api/comments")
    public HttpResponse<Maybe<CommentContract>> post(@Body @Valid CommentContract commentContract, @Nullable Authentication authentication) {

        assert authentication != null;

        return  HttpResponse.created(commentsService.save(commentContract.getComment(), authentication.getAttributes().get("email").toString(),
                commentContract.getParentId()).map(commentContractMapper::fromComment));

    }
}
