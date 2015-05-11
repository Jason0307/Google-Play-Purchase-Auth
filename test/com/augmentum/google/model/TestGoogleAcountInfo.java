package com.augmentum.google.model;

import org.junit.Test;

import com.augmentum.google.servlet.CallbackServlet;

public class TestGoogleAcountInfo extends BaseTest<GoogleAccountInfo> {

    @Test
    public void testFindByUnique() {
        GoogleAccountInfo record = GoogleAccountInfo.dao.findByGameUnique("flight");
        System.out.println(record);
        assertNotNull(record);
    }

    @Test
    public void testUUID() {
        new CallbackServlet().getRefreshToken("4/L7hbwfEj529w1sMErJoAafuFVN_0LZbO1319iwd9o58.wqIe99wLNuUVEnp6UAPFm0HsrhW0mAI", GoogleAccountInfo.dao.findByGameUnique("dcm"));
    }

}
