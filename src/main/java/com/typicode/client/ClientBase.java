package com.typicode.client;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class ClientBase {
    private static final Logger logger = LogManager.getLogger(ClientBase.class);
    private final HttpClient client = HttpClients.createDefault();

    private static ClientBase clientBase;

    private ClientBase() {
    }

    public static ClientBase getInstance() {
        if (Objects.isNull(clientBase)) {
            clientBase = new ClientBase();
        }
        return clientBase;
    }

    public static class HttpResponseReader {
        private final HttpResponse response;
        private String payload;

        HttpResponseReader(HttpResponse response) {
            this.response = response;
        }

        public String getPayload() throws IOException {
            if (Objects.isNull(payload)) {
                payload = EntityUtils.toString(response.getEntity());
            }
            return payload;
        }
    }

    private void logRequest(HttpRequestBase request) {
        logger.info("Request: {}", request.toString());
        logger.info("Headers: {}", Arrays.toString(request.getAllHeaders()));
    }

    private HttpResponseReader execute(HttpRequestBase request) throws IOException {
        HttpResponse response = client.execute(request);
        HttpResponseReader reader = new HttpResponseReader(response);
        String payload = reader.getPayload();

        logger.info("Response: {}{}",
                System.lineSeparator(),
                payload);

        return reader;
    }

    public HttpResponseReader get(HttpGet httpGet) throws IOException {
        logRequest(httpGet);
        return execute(httpGet);
    }

    public String getResponseForRequest(String request) throws IOException {
        HttpGet getRequest = new HttpGet(request);
        return clientBase.get(getRequest).getPayload();
    }

}
