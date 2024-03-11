package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

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
            //System.out.println("postsJson="+postsJson);
            UserPost[] userPosts = new Gson().fromJson(postsJson, new TypeToken<UserPost[]>() {}.getType());
            System.out.println("=============== debug ==================");
            System.out.println(Arrays.toString(userPosts));
            for (UserPost userPost : userPosts) {
                System.out.println("userPost.getPostId="+userPost.toString());
            }
            System.out.println("=============== end debug ==================");
//            UserPosts userPosts = new Gson().fromJson(postsJson, UserPosts.class);
//            System.out.println("userPosts="+userPosts);
            if (userPosts.length > 0) {
                UserPost userPost = userPosts[userPosts.length - 1];
                System.out.println("userPost.getPostId=" + userPost.getPostId());
                return userPost.getPostId();
            }
            //todo: rewrite with GSON library
//            System.out.println(postsJson.substring(postsJson.lastIndexOf("\"id\":") + 5, postsJson.indexOf(",", postsJson.lastIndexOf("\"id\":"))));
//            result = Integer.parseInt(postsJson.substring(postsJson.lastIndexOf("\"id\":") + 5, postsJson.indexOf(",", postsJson.lastIndexOf("\"id\":"))).trim());
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        ApiClient apiClient = new ApiClient();
        System.out.println("start");
        try {
            // Task 1
            String newUser = apiClient.createUser("{\"name\":\"Test1 Test2\",\"username\":\"test1test2\",\"email\":\"test1test2@example.com\"}");
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


class UserPosts {
    public UserPost[] userPosts;

    UserPosts(UserPost[] userPosts) {
        this.userPosts = userPosts;
    }

    public UserPost[] getUserPosts() {
        return userPosts;
    }

    public void setUserPosts(UserPost[] userPosts) {
        this.userPosts = userPosts;
    }
}

/**
 *
 */
class UserPost {

    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;

    public UserPost(int postId, int id, String name, String email, String body){
        this.postId = postId;
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "UserPost{" +
                "id='" + id + '\'' +
                "postId='" + postId + '\'' +
                "name='" + name + '\'' +
                "email='" + email + '\'' +
                //too long to show here
                //"body='" + body + '\'' +
                '}';
    }
}
