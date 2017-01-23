package com.sz21c;

import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JavaCodeTests {

    @Test
    public void test_urlParsing() throws Exception {
        String orgUrl = "https://blog.sz21c.com/123";

        URL url = new URL(orgUrl);
        String host = url.getHost();

        assertNotNull(host);
        assertEquals(host, "blog.sz21c.com");
    }
}
