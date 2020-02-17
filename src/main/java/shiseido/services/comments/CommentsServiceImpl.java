package shiseido.services.comments;

import io.micronaut.context.annotation.Value;
import shiseido.models.Comment;
import shiseido.models.User;
import shiseido.repositories.interfaces.CommentsRepository;
import shiseido.services.users.UsersService;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Named;
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
    public List<Comment> getLatestComments() {
        return commentsRepository.getAllLatest(postsQueryLimits);
    }

    @Override
    public List<Comment> getLatestChildComments(Long commentId) {
        return commentsRepository.getAllLatestChildComments(commentsQueryLimits, commentId);
    }

    @Override
    public Comment save(String comment, String userEmail, Long parentId) {

        User user = usersService.findByEmail(userEmail);

        if (user == null)
            return null;

        Comment parent = null;

            if (parentId > 0)
                parent = commentsRepository.getById(parentId);

        return commentsRepository.save(comment, user, parent);
    }
}
