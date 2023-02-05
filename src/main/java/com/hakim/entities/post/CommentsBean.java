package com.hakim.entities.post;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Hakim
 */
@RequestScoped
@Transactional
public class CommentsBean {
    @PersistenceContext
    private EntityManager entityManager;
    
    private final Logger logger=LoggerFactory.getLogger(CommentsBean.class);
    
    public boolean addComment(Comments comments){
        boolean isAdded=false;
        try{
            entityManager.persist(comments);
            isAdded=true;
            logger.info("Comment added");
        }catch(Exception ex){
            logger.error("Could not add Comment.Error : "+ex.getMessage());
            ex.printStackTrace();
        }
        return isAdded;
    }
    
    public boolean updateComment(EditCommentDTO dto,long cid){
        boolean isUpdated=false;
        try{
            Comments comment = entityManager.find(Comments.class, cid);
            comment.setComment(dto.getComment());
            
            isUpdated=true;
            
            logger.info("Comment Updated");
        }catch(Exception ex){
            logger.error("Could not update Comment");
            ex.printStackTrace();
        }
        
        return isUpdated;
    }
    
    public boolean deleteComment(long cid){
        boolean isDeleted=false;
        try{
            Comments comment = entityManager.find(Comments.class, cid);
            entityManager.remove(comment);
            
            isDeleted=true;
            logger.warn("Comment deleted");
        }catch(Exception ex){
            logger.error("Could not delete comment");
            ex.printStackTrace();
        }
        
        return isDeleted;
    }
    
    public Comments getComment(long cid){
        Comments comments=null;
        
        try{
            comments=entityManager.find(Comments.class, cid);
        }catch(Exception ex){
            logger.error("Could not get Comment");
            ex.printStackTrace();
        }
        
        return comments;
    }
    
    public List<Comments> getAllComments(){
        List<Comments> comments=null;
        
        try{
            comments=entityManager.createQuery("select c from Comments c").getResultList();
        }catch(Exception ex){
            logger.error("Could not get comments");
            ex.printStackTrace();
        }
        
        return comments;
    }
    
    public List<Comments> getCommentsRelatedToPost(long pid){
        List<Comments> comments=null;
        try{
            comments=entityManager.createQuery("select c from Comments c where c.post.id=:id")
                    .setParameter("id", pid)
                    .getResultList();
        }catch(Exception ex){
            logger.error("Could not get Comments");
            ex.printStackTrace();
        }
        
        return comments;
    }
    
    public int like(long uid,long cid){
        Comments comments=getComment(cid);
        List<Long> likes=comments.getLikes();
        if(likes.contains(uid)){
            likes.remove(uid);
        }else{
            likes.add(uid);
        }
        
        if(comments.getDislikes().contains(uid)){
            comments.getDislikes().remove(uid);
        }
        
        return likes.size();
    }
    
    public int dislike(long uid,long cid){
        Comments comments=getComment(cid);
        List<Long> dislikes=comments.getDislikes();
        if(dislikes.contains(uid)){
            dislikes.remove(uid);
        }else{
            dislikes.add(uid);
        }
        
        if(comments.getLikes().contains(uid)){
            comments.getLikes().remove(uid);
        }
        
        return dislikes.size();
    }
    
    
    public int getLikes(long cid){
        return getComment(cid).getLikes().size();
    }
    
    public int getDislikes(long cid){
        return getComment(cid).getDislikes().size();
    }
}
