package com.typicode.test;

import com.typicode.models.User;
import io.qameta.allure.Description;
import org.junit.Assert;
import org.junit.Test;

public class NegativeTests extends BaseTest {

    @Test
    @Description("Verify that not existing post does not have comments")
    public void checkNotExistingPostComments() {

        int incorrectPostId = 100000001;
        Assert.assertTrue(steps.getCommentsByPost(incorrectPostId).isEmpty());
        Assert.assertTrue(steps.getCommentsByPostViaComments(incorrectPostId).isEmpty());
    }

    @Test
    @Description("Verify not existing user")
    public void getNotExistingUser() {
        User user = steps.getUserById(100000001);
        Assert.assertEquals(user.getId(), 0);
        Assert.assertNull(user.getUserName());
    }

}
