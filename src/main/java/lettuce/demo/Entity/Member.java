package lettuce.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.util.*;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 40)
    @NotEmpty
    private String name;
    @Column(length = 40, unique = true)
    @NotEmpty
    private String nickname;
    @Column(length = 60, unique = true)
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String password;

    @Transient
    private String passwordConfirm;

    private Boolean Verified;
    @Column(length = 40)
    @Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$", message = "번호를 다시 확인해주세요.")
    @NotEmpty
    private String phone;

    private String authNum;

    private String github;

    private String instagram;

    @Lob
    private byte[] image;

    private String imageType;

    @Column(length = 100)
    private String location;

    @Column(name = "is_admin")
    private Boolean isAdmin = false;

    @Column(name = "is_enable")
    private Boolean isEnable;

    public Boolean getEnable() {
        return isEnable;
    }

    public void setEnable(Boolean enable) {
        isEnable = enable;
    }

    public Boolean isAdmin() {
        return isAdmin;
    }

    @OneToMany(mappedBy = "reporter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Declaration> reportedDeclarations = new ArrayList<>();

    @OneToMany(mappedBy = "reported", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Declaration> receivedDeclarations = new ArrayList<>();

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mail> senderMail = new ArrayList<>();

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mail> receiverMail = new ArrayList<>();

    public List<Mail> getSenderMail() {
        return senderMail;
    }

    public void setSenderMail(List<Mail> senderMail) {
        this.senderMail = senderMail;
    }

    public List<Mail> getReceiverMail() {
        return receiverMail;
    }

    public void setReceiverMail(List<Mail> receiverMail) {
        this.receiverMail = receiverMail;
    }

    @JsonIgnore
    public List<Declaration> getReportedDeclarations() {
        return reportedDeclarations;
    }
    @JsonIgnore
    public List<Declaration> getReceivedDeclarations() {
        return receivedDeclarations;
    }


    public void setReportedDeclarations(List<Declaration> reportedDeclarations) {
        this.reportedDeclarations = reportedDeclarations;
    }

    public void setReceivedDeclarations(List<Declaration> receivedDeclarations) {
        this.receivedDeclarations = receivedDeclarations;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getGithub() {
         return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public Boolean getVerified() {
        return Verified;
    }

    public void setVerified(Boolean verified) {
        Verified = verified;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }



    public String getAuthNum() {
        return authNum;
    }

    public void setAuthNum(String authNum) {
        this.authNum = authNum;
    }


    public void addPost(Post post) {
        posts.add(post);
        post.setMember(this);
    }
    public void removePost(Post post) {
        posts.remove(post);
        post.setMember(null);
    }
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reply> replies = new ArrayList<>();

    public void addReply(Reply reply) {
        replies.add(reply);
        reply.setMember(this);
    }

    public void removeReply(Reply reply) {
        replies.remove(reply);
        reply.setMember(null);
    }

    @OneToMany(mappedBy = "member")
    private List<Comment> comments;

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
}
