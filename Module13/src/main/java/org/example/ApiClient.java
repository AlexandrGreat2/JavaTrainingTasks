package org.example;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.FileWriter;
import java.io.IOException;

public class ApiClient {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    public String createUser(String jsonBody) throws IOException {
        return executeRequest(HttpPost.METHOD_NAME, "/users", jsonBody);
    }

    public String updateUser(int userId, String jsonBody) throws IOException {
        return executeRequest(HttpPut.METHOD_NAME, "/users/" + userId, jsonBody);
    }

    public int deleteUser(int userId) throws IOException {
        HttpResponse response = executeHttpRequest(HttpDelete.METHOD_NAME, "/users/" + userId, null);
        return response.getStatusLine().getStatusCode();
    }

    public String getAllUsers() throws IOException {
        return executeRequest(HttpGet.METHOD_NAME, "/users", null);
    }

    public String getUserById(int userId) throws IOException {
        return executeRequest(HttpGet.METHOD_NAME, "/users/" + userId, null);
    }

    public String getUserByUsername(String username) throws IOException {
        return executeRequest(HttpGet.METHOD_NAME, "/users?username=" + username, null);
    }

    public String getAllCommentsForLastPostOfUser(int userId) throws IOException {
        String posts = executeRequest(HttpGet.METHOD_NAME, "/users/" + userId + "/posts", null);
        int lastPostId = extractLastPostId(posts);
        return executeRequest(HttpGet.METHOD_NAME, "/posts/" + lastPostId + "/comments", null);
    }

    public String getAllOpenTodosForUser(int userId) throws IOException {
        return executeRequest(HttpGet.METHOD_NAME, "/users/" + userId + "/todos?completed=false", null);
    }

    public void writeCommentsToFile(int userId, int postId, String comments) throws IOException {
        String fileName = "user-" + userId + "-post-" + postId + "-comments.json";
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(comments);
        }
    }

    private String executeRequest(String method, String path, String jsonBody) throws IOException {
        HttpResponse response = executeHttpRequest(method, path, jsonBody);

        int statusCode = response.getStatusLine().getStatusCode();
        String responseBody = EntityUtils.toString(response.getEntity());

        if (statusCode >= 200 && statusCode < 300) {
            return responseBody;
        } else {
            throw new RuntimeException("Request failed with status code: " + statusCode + "\nResponse: " + responseBody);
        }
    }

    private HttpResponse executeHttpRequest(String method, String path, String jsonBody) throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpRequestBase request;

        switch (method) {
            case HttpPost.METHOD_NAME:
                HttpPost postRequest = new HttpPost(BASE_URL + path);
                if (jsonBody != null) {
                    postRequest.setEntity(new StringEntity(jsonBody));
                    postRequest.setHeader("Content-type", "application/json");
                }
                request = postRequest;
                break;

            case HttpPut.METHOD_NAME:
                HttpPut putRequest = new HttpPut(BASE_URL + path);
                if (jsonBody != null) {
                    putRequest.setEntity(new StringEntity(jsonBody));
                    putRequest.setHeader("Content-type", "application/json");
                }
                request = putRequest;
                break;

            case HttpDelete.METHOD_NAME:
                request = new HttpDelete(BASE_URL + path);
                break;

            case HttpGet.METHOD_NAME:
            default:
                request = new HttpGet(BASE_URL + path);
                break;
        }

        return httpClient.execute(request);
    }

    public int extractLastPostId(String postsJson) {
        int result = -1;
        try {
            //todo: rewrite with GSON library
            System.out.println(postsJson.substring(postsJson.lastIndexOf("\"id\":") + 5, postsJson.indexOf(",", postsJson.lastIndexOf("\"id\":"))));
            result = Integer.parseInt(postsJson.substring(postsJson.lastIndexOf("\"id\":") + 5, postsJson.indexOf(",", postsJson.lastIndexOf("\"id\":"))).trim());
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        ApiClient apiClient = new ApiClient();
        System.out.println("start");
        try {
            // Task 1
            String newUser = apiClient.createUser("{\"name\":\"John Doe\",\"username\":\"johndoe\",\"email\":\"johndoe@example.com\"}");
            System.out.println("Created user: " + newUser);

            String updatedUser = apiClient.updateUser(1, "{\"name\":\"Updated Name\",\"username\":\"updatedusername\",\"email\":\"updatedemail@example.com\"}");
            System.out.println("Updated user: " + updatedUser);

            int deleteStatusCode = apiClient.deleteUser(1);
            System.out.println("Delete status code: " + deleteStatusCode);

            String allUsers = apiClient.getAllUsers();
            System.out.println("All users: " + allUsers);

            String userById = apiClient.getUserById(2);
            System.out.println("User by ID: " + userById);

            String userByUsername = apiClient.getUserByUsername("Bret");
            System.out.println("User by username: " + userByUsername);

            // Task 2
            String commentsForLastPost = apiClient.getAllCommentsForLastPostOfUser(1);
            System.out.println("Comments for last post: " + commentsForLastPost);

            apiClient.writeCommentsToFile(1, apiClient.extractLastPostId(commentsForLastPost), commentsForLastPost);
            System.out.println("Comments written to file.");

            // Task 3
            String openTodosForUser = apiClient.getAllOpenTodosForUser(1);
            System.out.println("Open todos for user: " + openTodosForUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
