package shiseido.services.comments;

import shiseido.configurations.QueryLimitsConfiguration;
import shiseido.models.Comment;
import shiseido.models.User;
import shiseido.repositories.interfaces.CommentsRepository;
import shiseido.services.users.UsersService;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

public class CommentsServiceImpl implements CommentsService {

    @Inject
    private final UsersService usersService;
    @Inject
    private final CommentsRepository commentsRepository;

    private final QueryLimitsConfiguration postsQueryLimitsConfiguration;
    private final QueryLimitsConfiguration commentsQueryLimitsConfiguration;


    public CommentsServiceImpl(UsersService usersService,
                               CommentsRepository commentsRepository,
                               @Nullable @Named("posts") QueryLimitsConfiguration postsQueryLimitsConfiguration,
                               @Nullable @Named("comments") QueryLimitsConfiguration commentsQueryLimitsConfiguration) {
        this.usersService = usersService;
        this.commentsRepository = commentsRepository;
        this.postsQueryLimitsConfiguration = postsQueryLimitsConfiguration;
        this.commentsQueryLimitsConfiguration =commentsQueryLimitsConfiguration;
    }


    @Override
    public List<Comment> getLatestComments() {
        assert postsQueryLimitsConfiguration != null;
        return commentsRepository.getAllLatest(postsQueryLimitsConfiguration.getMax());
    }

    @Override
    public List<Comment> getLatestChildComments(int commentId) {
        assert commentsQueryLimitsConfiguration != null;
        return commentsRepository.getAllLatestChildComments(commentsQueryLimitsConfiguration.getMax(), commentId);
    }

    @Override
    public Comment save(String comment, String userEmail, int parentId) {

        User user;

        user = usersService.findByEmail(userEmail);

        if (user == null)
            return null;

        Comment parent = null;

            if (parentId > 0)
                parent = commentsRepository.getById(parentId);

        return commentsRepository.save(comment, user, parent);
    }
}
