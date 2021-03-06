package com.typicode.steps;

import com.typicode.client.ClientSteps;
import com.typicode.models.Comment;
import com.typicode.models.Post;
import com.typicode.models.User;
import io.qameta.allure.Step;

import java.util.List;
import java.util.stream.Collectors;

public class Steps {

    private final ClientSteps clientSteps = new ClientSteps();

    @Step("Get user by username {userName}")
    public User getUserByUserName(final String userName) {
        List<User> users = clientSteps.getAllUsers();
        int userId = users.stream().filter(user -> user.getUserName()
                .equals(userName)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User with userName '"
                        + userName + "' was not found")).getId();

        return clientSteps.getUserById(userId);
    }

    @Step("Get user by id {id}")
    public User getUserById(int id) {
        return clientSteps.getUserById(id);
    }

    @Step("Get posts by user {user.userName}")
    public List<Post> getPostsByUser(User user) {
        List<Post> posts = clientSteps.getAllPosts();
        return posts.stream().filter(post -> post.getUserId() == user.getId())
                .collect(Collectors.toList());
    }

    @Step("Get posts by user id {id}")
    public List<Post> getPostsByUser(int id) {
        return clientSteps.getAllPostsByUser(id);
    }

    @Step("Get comments for post {post.id}")
    public List<Comment> getCommentsByPost(Post post) {
        List<Comment> comments = clientSteps.getAllComments();
        return comments.stream().filter(comment -> comment.getPostId() == post.getId())
                .collect(Collectors.toList());
    }

    @Step("Get comments for post id {id}")
    public List<Comment> getCommentsByPost(int id) {
        return clientSteps.getCommentsByPost(id);
    }

    @Step("Get comments for post id {id}")
    public List<Comment> getCommentsByPostViaComments(int id) {
        return clientSteps.getCommentsByPostViaComments(id);
    }
}
