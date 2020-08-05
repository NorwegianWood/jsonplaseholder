package com.typicode.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.typicode.models.Comment;
import com.typicode.models.Post;
import com.typicode.models.User;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class ClientSteps {

    private final ClientBase clientBase = ClientBase.getInstance();

    private static final Properties properties = new Properties();
    private final String baseUrl;

    private final ObjectMapper mapper;

    public ClientSteps() {
        try {
            properties.load(new FileInputStream("src/main/resources/base.properties"));
        } catch (IOException e) {
            throw new IllegalArgumentException("Something went wrong while reading properties: " + e.getMessage());
        }

        this.baseUrl = properties.getProperty("baseUrl");
        mapper = new ObjectMapper();
    }

    public List<User> getAllUsers() {
        List<User> users;
        try {
            HttpGet getRequest = new HttpGet(baseUrl + "users");
            String usersResponse = clientBase.get(getRequest).getPayload();
            users = mapper.readValue(usersResponse, new TypeReference<List<User>>() {});
        } catch (IOException e) {
            throw new ParseException("Something went wrong while retrieving all users: " + e.getMessage());
        }
        return users;
    }

    public List<Post> getAllPosts() {

        List<Post> posts;
        try {
            HttpGet getRequest = new HttpGet(baseUrl + "posts");
            String postsResponse = clientBase.get(getRequest).getPayload();
            posts = mapper.readValue(postsResponse, new TypeReference<List<Post>>() {});
        } catch (IOException e) {
            throw new ParseException("Something went wrong while retrieving all posts: " + e.getMessage());
        }

        return posts;
    }

    public List<Post> getAllPostsByUser(int userId) {
        List<Post> posts;
        try {
            HttpGet getRequest = new HttpGet(baseUrl + "posts?userId=" + userId);
            String postsResponse = clientBase.get(getRequest).getPayload();
            posts = mapper.readValue(postsResponse, new TypeReference<List<Post>>() {});
        } catch (IOException e) {
            throw new ParseException("Something went wrong while retrieving all posts: " + e.getMessage());
        }
        return posts;
    }

    public User getUserById(int userId) {
        HttpGet getRequest = new HttpGet(baseUrl + "users/" + userId);
        try {
            String userResponse = clientBase.get(getRequest).getPayload();
            return mapper.readValue(userResponse, User.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("Received user with user id: " + userId +
                    " could not be parsed. Original error: " + e.getMessage());
        }
    }

    public List<Comment> getAllComments()  {

        List<Comment> comments;
        try {
            HttpGet getRequest = new HttpGet(baseUrl + "comments");
            String postsResponse = clientBase.get(getRequest).getPayload();
            comments = mapper.readValue(postsResponse, new TypeReference<List<Comment>>() {});
        } catch (IOException e) {
            throw new ParseException("Something went wrong while retrieving all comments: " + e.getMessage());
        }

        return comments;
    }

    public List<Comment> getCommentsByPost(int postId)  {

        List<Comment> comments;
        try {
            HttpGet getRequest = new HttpGet(baseUrl + "posts/" + postId + "/comments");
            String postsResponse = clientBase.get(getRequest).getPayload();
            comments = mapper.readValue(postsResponse, new TypeReference<List<Comment>>() {});
        } catch (IOException e) {
            throw new ParseException("Something went wrong while retrieving comments by post id: " + postId + e.getMessage());
        }

        return comments;
    }

    public List<Comment> getCommentsByPostViaComments(int postId)  {

        List<Comment> comments;
        try {
            HttpGet getRequest = new HttpGet(baseUrl + "comments?postId=" + postId);
            String postsResponse = clientBase.get(getRequest).getPayload();
            comments = mapper.readValue(postsResponse, new TypeReference<List<Comment>>() {});
        } catch (IOException e) {
            throw new ParseException("Something went wrong while retrieving comments by post id: " + postId + e.getMessage());
        }

        return comments;
    }
}
