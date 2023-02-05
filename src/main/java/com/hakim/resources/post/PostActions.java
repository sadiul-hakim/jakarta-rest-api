package com.hakim.resources.post;

import com.hakim.entities.client.Client;
import com.hakim.entities.client.ClientBean;
import com.hakim.entities.post.EditPostDTO;
import com.hakim.entities.post.Post;
import com.hakim.entities.post.PostBean;
import com.hakim.resources.LikeDislikeResult;
import com.hakim.resources.PostActionResult;
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
@Path("/post")
public class PostActions {
    @Inject
    private PostBean postBean;
    
    @Inject
    private ClientBean clientBean;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PostActionResult savePost(Post post,@QueryParam("uid") long uid){
        Client client = clientBean.getClient(uid);
        client.addPost(post);
        post.setClient(client);
        postBean.addPost(post);
        
        return new PostActionResult(true);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/update")
    public PostActionResult updatePost(EditPostDTO dto,@QueryParam("pid") long pid){
       postBean.updatePost(dto, pid);
        
        return new PostActionResult(true);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Post> getPosts(){
        return postBean.getAllPosts();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user")
    public List<Post> getPostsRelatedToClient(@QueryParam("uid") long uid){
        return postBean.getPostRelatedToUser(uid);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{pid}")
    public Post getPost(@PathParam("pid") long pid){
        return postBean.getPost(pid);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/like")
    public LikeDislikeResult like(@QueryParam("pid") long pid,@QueryParam("uid") long uid){
        int likes=postBean.like(uid, pid);
        
        return new LikeDislikeResult(true,likes);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/dislike")
    public LikeDislikeResult dislike(@QueryParam("pid") long pid,@QueryParam("uid") long uid){
        int dislikes=postBean.dislike(uid, pid);
        
        return new LikeDislikeResult(true,dislikes);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/alllikes")
    public LikeDislikeResult allLikes(@QueryParam("pid") long pid){
        int likes=postBean.getLikes(pid);
        
        return new LikeDislikeResult(true,likes);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/alldislikes")
    public LikeDislikeResult allDislikes(@QueryParam("pid") long pid){
        int likes=postBean.getDislikes(pid);
        
        return new LikeDislikeResult(true,likes);
    }
}
