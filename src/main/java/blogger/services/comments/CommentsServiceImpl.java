package blogger.services.comments;

import io.micronaut.context.annotation.Value;
import blogger.models.Comment;
import blogger.models.User;
import blogger.repositories.interfaces.CommentsRepository;
import blogger.services.users.UsersService;
import io.micronaut.http.HttpResponse;
import io.reactivex.Maybe;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class CommentsServiceImpl implements CommentsService {

    @Inject
    private final UsersService usersService;
    @Inject
    private final CommentsRepository commentsRepository;

    @Value("${query_limits.posts.max}")
    private int postsQueryLimits;
    @Value("${query_limits.comments.max}")
    private int commentsQueryLimits;


    public CommentsServiceImpl(UsersService usersService,
                               CommentsRepository commentsRepository) {
        this.usersService = usersService;
        this.commentsRepository = commentsRepository;
    }

    @Override
    public List<Comment> getLatestComments(Long commentId) {
        if (commentId == 0) {
            return commentsRepository.getAllLatest(postsQueryLimits);
        } else {
            return commentsRepository.getAllLatestChildComments(commentsQueryLimits, commentId);
        }
    }

    @Override
    public Comment save(String comment, String userEmail, Long parentId) {

        User user = usersService.findByEmail(userEmail);

        if (parentId > 0) {
            Comment parent = commentsRepository.getById(parentId);
            return commentsRepository.save(comment, user, parent);
        }
        return commentsRepository.save(comment, user, null);

    }
}
