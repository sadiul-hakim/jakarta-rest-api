package com.hakim.entities.post;

import com.hakim.entities.client.Client;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hakim
 */
@Entity
public class Post implements Serializable{
    @Id
    @SequenceGenerator(name = "id_generator",sequenceName = "id_sequence",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "id_generator")
    private Long id;
    
    @NotNull
    @Size(max=100)
    private String title;
    
    @NotNull
    @Size(max=450)
    private String description;
    
    private List<Long> likes=new ArrayList<>();
    private List<Long> dislikes=new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "post")
    private List<Comments> comments=new ArrayList<>();

    
    @ManyToOne
    private Client client;
    
    public Post() {
    }

    public Post(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

   
    public void addComment(Comments comment){
        comments.add(comment);
    }
    
    
    
}
