package com.hakim.entities.client;

import com.hakim.entities.post.Comments;
import com.hakim.entities.post.Post;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hakim
 */
@Entity
public class Client implements Serializable {

    
    @Id
    @SequenceGenerator(name = "id_generator",sequenceName = "id_sequence",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "id_generator")
    private Long id;

    @NotNull
    @Size(max = 40)
    private String fullname;

    @NotNull
    @Size(max = 40)
    private String email;

    @NotNull
    @Size(max = 250)
    private String password;

    @NotNull
    @Size(max = 10)
    private String gender;
    
    @NotNull
    @Size(max=25)
    private String country;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date joinedAt=new Date();
    
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "client")
    private List<Post> posts=new ArrayList<>();

    public Client() {
    }
     
    

    public Client(String fullname, String email, String password, String gender, String country) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.country = country;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(Date joinedAt) {
        this.joinedAt = joinedAt;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
    
    public void addPost(Post post){
        posts.add(post);
    }
    
   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hakim.entities.User[ id=" + id + " ]";
    }

}
