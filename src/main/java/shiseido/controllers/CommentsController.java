package shiseido.controllers;

import com.fasterxml.jackson.annotation.JsonValue;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import shiseido.models.Comment;
import shiseido.services.comments.CommentsService;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@Controller
public class CommentsController {

    @Inject
    private final CommentsService commentsService;

    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @Secured(SecurityRule.IS_ANONYMOUS)
    @JsonValue
    @Get("/api/comments/{parentCommentId}")
    public List<Comment> get(int parentCommentId) {
        if (parentCommentId == 0) {
            return commentsService.getLatestComments();
        }
        return commentsService.getLatestChildComments(parentCommentId);
    }

//    @Secured(SecurityRule.IS_AUTHENTICATED)
//    @JsonValue
//    @Post("/api/comments")
//    public HttpResponse post(@Body @Valid Comment comment) {
//
//    }
}
