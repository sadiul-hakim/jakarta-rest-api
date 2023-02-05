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
public class PostBean {
    @PersistenceContext
    private EntityManager entityManager;
    
    private Logger logger=LoggerFactory.getLogger(PostBean.class);
    
    public boolean addPost(Post post){
        boolean isAdded=false;
        try{
            entityManager.persist(post);
            isAdded=true;
            logger.info("Post added");
        }catch(Exception ex){
            logger.error("Could not add post.Error : "+ex.getMessage());
            ex.printStackTrace();
        }
        return isAdded;
    }
    
    public boolean updatePost(EditPostDTO dto,long pid){
        boolean isUpdated=false;
        try{
            Post post = entityManager.find(Post.class, pid);
            post.setTitle(dto.getTitle());
            post.setDescription(dto.getDescription());
            isUpdated=true;
            
            logger.info("Post Updated");
        }catch(Exception ex){
            logger.error("Could not update post");
            ex.printStackTrace();
        }
        
        return isUpdated;
    }
    
    public boolean deletePost(long pid){
        boolean isDeleted=false;
        try{
            Post post=entityManager.find(Post.class, pid);
            entityManager.remove(post);
            
            isDeleted=true;
            logger.warn("Post deleted");
        }catch(Exception ex){
            logger.error("Could not delete post");
            ex.printStackTrace();
        }
        
        return isDeleted;
    }
    
    public Post getPost(long pid){
        Post post=null;
        try{
            post=entityManager.find(Post.class, pid);
        }catch(Exception ex){
            logger.error("Could not get post");
            ex.printStackTrace();
        }
        
        return post;
    }
    
    public List<Post> getAllPosts(){
        List<Post> posts=null;
        try{
            posts=entityManager.createQuery("select p from Post p").getResultList();
        }catch(Exception ex){
            logger.error("Could not get posts");
            ex.printStackTrace();
        }
        
        return posts;
    }
    
    public List<Post> getPostRelatedToUser(long uid){
        List<Post> posts=null;
        try{
            posts=entityManager.createQuery("select p from Post p where p.client.id=:id")
                    .setParameter("id", uid)
                    .getResultList();
        }catch(Exception ex){
            logger.error("Could not get post");
            ex.printStackTrace();
        }
        
        return posts;
    }
    
    public int like(long uid,long pid){
        Post post=getPost(pid);
        List<Long> likes=post.getLikes();
        if(likes.contains(uid)){
            likes.remove(uid);
        }else{
            likes.add(uid);
        }
        
        if(post.getDislikes().contains(uid)){
            post.getDislikes().remove(uid);
        }
        
        return likes.size();
    }
    
    public int dislike(long uid,long pid){
        Post post=getPost(pid);
        List<Long> dislikes=post.getDislikes();
        if(dislikes.contains(uid)){
            dislikes.remove(uid);
        }else{
            dislikes.add(uid);
        }
        
        if(post.getLikes().contains(uid)){
            post.getLikes().remove(uid);
        }
        
        return dislikes.size();
    }
    
    
    public int getLikes(long pid){
        return getPost(pid).getLikes().size();
    }
    
    public int getDislikes(long pid){
        return getPost(pid).getDislikes().size();
    }
}
