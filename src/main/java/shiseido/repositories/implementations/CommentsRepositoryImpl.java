package shiseido.repositories.implementations;

import shiseido.models.Comment;
import shiseido.models.User;
import shiseido.repositories.interfaces.CommentsRepository;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

@Singleton
public class CommentsRepositoryImpl implements CommentsRepository {

    @PersistenceContext
    public final EntityManager entityManager;

    public CommentsRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Comment> getAllLatest(int max) {
        TypedQuery<Comment> query = entityManager.createQuery(
                "select c " +
                        "from Comment c " +
                        "where c.parent is null " +
                        "order by c.created_on",
                Comment.class
        ).setMaxResults(max);
        return query.getResultList();
    }

    @Override
    public List<Comment> getAllLatestChildComments(int max, int commentId) {
        TypedQuery<Comment> query = entityManager.createQuery(
                "select c " +
                        "from Comment c " +
                        "where c.parent.id = :commentId " +
                        "order by c.created_on",
                Comment.class
        )
                .setParameter("commentId", commentId)
                .setMaxResults(max);
        return query.getResultList();
    }

    @Override
    public Comment getById(int id) {
        return entityManager.find(Comment.class, id);
    }

    @Override
    public Comment save(String comment, User author, Comment parent) {
        Comment newComment = new Comment();
        newComment.setComment(comment);
        newComment.setAuthor(author);
        newComment.setParent(parent);
        newComment.setCreated_on(new Date());
        newComment.setLast_update_on(new Date());
        entityManager.persist(newComment);
        return newComment;
    }
}
