package com.typicode.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.typicode.models.Comment;
import com.typicode.models.Post;
import com.typicode.models.User;
import org.apache.http.ParseException;

import java.io.IOException;
import java.util.List;

public class ClientSteps {

    private final ClientBase clientBase = ClientBase.getInstance();
    private final String baseUrl = "https://jsonplaceholder.typicode.com/";
    private final ObjectMapper mapper = new ObjectMapper();

    public List<User> getAllUsers() {
        try {
            String usersResponse = clientBase.getResponseForRequest(baseUrl + "users");
            return mapper.readValue(usersResponse, new TypeReference<List<User>>() {
            });
        } catch (IOException e) {
            throw new ParseException("Something went wrong while retrieving all users: " + e.getMessage());
        }
    }

    public List<Post> getAllPosts() {
        try {
            String postsResponse = clientBase.getResponseForRequest(baseUrl + "posts");
            return mapper.readValue(postsResponse, new TypeReference<List<Post>>() {
            });
        } catch (IOException e) {
            throw new ParseException("Something went wrong while retrieving all posts: " + e.getMessage());
        }
    }

    public List<Post> getAllPostsByUser(int userId) {
        try {
            String postsResponse = clientBase.getResponseForRequest(baseUrl + "posts?userId=" + userId);
            return mapper.readValue(postsResponse, new TypeReference<List<Post>>() {
            });
        } catch (IOException e) {
            throw new ParseException("Something went wrong while retrieving all posts: " + e.getMessage());
        }
    }

    public User getUserById(int userId) {
        try {
            String userResponse = clientBase.getResponseForRequest(baseUrl + "users/" + userId);
            return mapper.readValue(userResponse, User.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("Received user with user id: " + userId +
                    " could not be parsed. Original error: " + e.getMessage());
        }
    }

    public List<Comment> getAllComments() {

        try {
            String commentsResponse = clientBase.getResponseForRequest(baseUrl + "comments");
            return mapper.readValue(commentsResponse, new TypeReference<List<Comment>>() {
            });
        } catch (IOException e) {
            throw new ParseException("Something went wrong while retrieving all comments: " + e.getMessage());
        }
    }

    public List<Comment> getCommentsByPost(int postId) {
        try {
            String commentsResponse = clientBase.getResponseForRequest(baseUrl + "posts/" + postId + "/comments");
            return mapper.readValue(commentsResponse, new TypeReference<List<Comment>>() {
            });
        } catch (IOException e) {
            throw new ParseException("Something went wrong while retrieving comments by post id: " + postId + e.getMessage());
        }
    }

    public List<Comment> getCommentsByPostViaComments(int postId) {
        try {
            String commentsResponse = clientBase.getResponseForRequest(baseUrl + "comments?postId=" + postId);
            return mapper.readValue(commentsResponse, new TypeReference<List<Comment>>() {
            });
        } catch (IOException e) {
            throw new ParseException("Something went wrong while retrieving comments by post id: " + postId + e.getMessage());
        }
    }
}
