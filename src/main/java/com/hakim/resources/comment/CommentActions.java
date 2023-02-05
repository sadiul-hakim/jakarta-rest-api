package com.hakim.resources.comment;

import com.hakim.entities.client.Client;
import com.hakim.entities.client.ClientBean;
import com.hakim.entities.post.Comments;
import com.hakim.entities.post.CommentsBean;
import com.hakim.entities.post.EditCommentDTO;
import com.hakim.entities.post.Post;
import com.hakim.entities.post.PostBean;
import com.hakim.resources.CommentActionResult;
import com.hakim.resources.LikeDislikeResult;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

/**
 *
 * @author Hakim
 */
@Path("/comment")
public class CommentActions {

    @Inject
    private PostBean postBean;

    @Inject
    private CommentsBean commentsBean;
    
    @Inject
    private ClientBean clientBean;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CommentActionResult saveComment(Comments comments, @QueryParam("pid") long pid,@QueryParam("uid") long uid) {
        Post post = postBean.getPost(pid);
        Client client=clientBean.getClient(uid);
        
        post.addComment(comments);
                
        comments.setPost(post);
        comments.setCommenter(client.getId());
        comments.setName(client.getFullname());
        
        commentsBean.addComment(comments);

        return new CommentActionResult(true);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/update")
    public CommentActionResult updateComment(EditCommentDTO dto, @QueryParam("cid") long cid) {
        commentsBean.updateComment(dto, cid);

        return new CommentActionResult(true);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comments> getComments(){
        return commentsBean.getAllComments();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{cid}")
    public Comments getComment(@PathParam("cid") long cid){
        return commentsBean.getComment(cid);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/post")
    public List<Comments> getCommentsRelatedToPost(@QueryParam("pid") long pid){
        return commentsBean.getCommentsRelatedToPost(pid);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/like")
    public LikeDislikeResult like(@QueryParam("cid") long cid,@QueryParam("uid") long uid){
        int likes=commentsBean.like(uid, cid);
        
        return new LikeDislikeResult(true,likes);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/dislike")
    public LikeDislikeResult dislike(@QueryParam("cid") long cid,@QueryParam("uid") long uid){
        int dislikes=commentsBean.dislike(uid, cid);
        
        return new LikeDislikeResult(true,dislikes);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/alllikes")
    public LikeDislikeResult allLikes(@QueryParam("cid") long cid){
        int likes=commentsBean.getLikes(cid);
        
        return new LikeDislikeResult(true,likes);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/alldislikes")
    public LikeDislikeResult allDislikes(@QueryParam("cid") long cid){
        int likes=commentsBean.getDislikes(cid);
        
        return new LikeDislikeResult(true,likes);
    }
}
