package com.typicode.test;

import com.typicode.models.Comment;
import com.typicode.models.Post;
import com.typicode.models.User;
import io.qameta.allure.Description;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.validator.routines.EmailValidator;

public class DefaultUserTests extends BaseTest {

    @Test
    @Description("Verify Delphine's posts have correct emails in comments by fetching all entries")
    public void verifyPostsHaveCorrectEmailsInCommentsAllEntries() {
        User user = steps.getUserByUserName(defaultUser);
        List<Post> posts = steps.getPostsByUser(user);
        List<Comment> commentsToUsersPosts = new ArrayList<>();

        for (Post post : posts) {
            List<Comment> comments = steps.getCommentsByPost(post);
            commentsToUsersPosts.addAll(comments);
        }

        for (Comment comment : commentsToUsersPosts) {
            Assert.assertTrue("Email is not valid: " + comment.getEmail(),
                    EmailValidator.getInstance().isValid(comment.getEmail()));
        }
    }

    @Test
    @Description("Verify Delphine's posts have correct emails in comments by fetching via posts")
    public void verifyPostsHaveCorrectEmailsInCommentsViaPosts() {
        User user = steps.getUserByUserName(defaultUser);
        List<Post> posts = steps.getPostsByUser(user);
        List<Integer> postsIds = posts.stream().map(Post::getId).collect(Collectors.toList());
        List<Comment> comments = new ArrayList<>();
        for (Integer postId : postsIds) {
            comments.addAll(steps.getCommentsByPost(postId));
        }
        for (Comment comment : comments) {
            Assert.assertTrue("Email is not valid: " + comment.getEmail(),
                    EmailValidator.getInstance().isValid(comment.getEmail()));
        }
    }

    @Test
    @Description("Verify Delphine's posts have correct emails in comments by fetching via comments")
    public void verifyPostsHaveCorrectEmailsInCommentsViaComments() {
        User user = steps.getUserByUserName(defaultUser);
        List<Post> posts = steps.getPostsByUser(user);
        List<Integer> postsIds = posts.stream().map(Post::getId).collect(Collectors.toList());
        List<Comment> comments = new ArrayList<>();
        for (Integer postId : postsIds) {
            comments.addAll(steps.getCommentsByPostViaComments(postId));
        }
        for (Comment comment : comments) {
            Assert.assertTrue("Email is not valid: " + comment.getEmail(),
                    EmailValidator.getInstance().isValid(comment.getEmail()));
        }

    }

    @Test
    @Description("Verify that posts are the same from different sources")
    public void comparePostsByUserAndDifferentFetching() {
        User user = steps.getUserByUserName(defaultUser);
        List<Post> firstBatch = steps.getPostsByUser(user);
        List<Post> secondBatch = steps.getPostsByUser(user.getId());

        Assert.assertEquals("Posts by user are not the same", firstBatch, secondBatch);
    }

    @Test
    @Description("Verify that comments are the same from different sources")
    public void compareCommentsByUserAndDifferentFetching() {
        User user = steps.getUserByUserName(defaultUser);
        List<Post> posts = steps.getPostsByUser(user);
        List<Integer> postsIds = posts.stream().map(Post::getId).collect(Collectors.toList());
        List<Comment> firstBatch = new ArrayList<>();
        for (Integer postId : postsIds) {
            firstBatch.addAll(steps.getCommentsByPostViaComments(postId));
        }

        List<Comment> secondBatch = new ArrayList<>();
        for (Integer postId : postsIds) {
            secondBatch.addAll(steps.getCommentsByPost(postId));
        }

        Assert.assertEquals("Comments by user are not the same", firstBatch, secondBatch);
    }

}
