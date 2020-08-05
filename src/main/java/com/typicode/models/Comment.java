package com.typicode.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment {
    int postId;
    String email;

    public int getPostId() {
        return postId;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return postId == comment.postId &&
                Objects.equals(email, comment.email);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "postId=" + postId +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, email);
    }
}
