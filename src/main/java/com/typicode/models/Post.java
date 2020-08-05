package com.typicode.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {
    int userId;
    int id;

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Post{" +
                "userId=" + userId +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return userId == post.userId &&
                id == post.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, id);
    }
}
