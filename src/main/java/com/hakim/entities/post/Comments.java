package com.hakim.entities.post;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Hakim
 */
@Entity
public class Comments {

    @Id
    @SequenceGenerator(name = "id_generator", sequenceName = "id_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    private Long id;

    @NotNull
    @Size(max = 250)
    private String comment;

    private List<Long> likes=new ArrayList<>();
    private List<Long> dislikes=new ArrayList<>();
    
    @ManyToOne
    private Post post;
    
    @NotNull
    private long commenter;
    
    @NotNull
    @Column(name = "COMMENTER_NAME")
    @Size(max=40)
    private String name;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date time=new Date();

    public Comments() {
    }

    public Comments( String comment) {
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getCommenter() {
        return commenter;
    }

    public void setCommenter(long commenter) {
        this.commenter = commenter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Long> getLikes() {
        return likes;
    }

    public void setLikes(List<Long> likes) {
        this.likes = likes;
    }

    public List<Long> getDislikes() {
        return dislikes;
    }

    public void setDislikes(List<Long> dislikes) {
        this.dislikes = dislikes;
    }

    

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    
    

}
