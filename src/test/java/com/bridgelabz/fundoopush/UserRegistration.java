package com.bridgelabz.fundoopush;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class UserRegistration {

    @Test
    public void RegistrationSuccessful() {
        RestAssured.baseURI = "https://fundoopush-backend-dev.bridgelabz.com/registration";
        RequestSpecification request = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", "bejeyap238@wmail2.net");
        requestParams.put("password", "34567");
        request.body(requestParams.toJSONString());
        Response response = request.post("https://fundoopush-backend-dev.bridgelabz.com/registration");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(201, statusCode);
    }

    @Test
    public void givenFundooPush_UserRegistration_IfEmailId_and_pwd_isEmpty_thenshouldreturn_StatusCode() {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"email\":\"\",\"password\":\"\"}")
                .when()
                .post("https://fundoopush-backend-dev.bridgelabz.com/registration");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(400, statusCode);
    }

    @Test
    public void givenFundooPush_UserRegistration_IfEmailIsExiest_and_pwdisEmpty_thenshouldreturn_StatusCode() {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"email\":\"potidir176@mtsg.me\",\"password\":\"\"}")
                .when()
                .post("https://fundoopush-backend-dev.bridgelabz.com/registration");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(400, statusCode);
    }

    @Test
    public void givenFundooPush_UserRegistration_IfEmailIsExist_and_pwdisExiest_thenshouldreturn_StatusCode() {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"email\":\"\",\"password\":\"abcd1234\"}")
                .when()
                .post("https://fundoopush-backend-dev.bridgelabz.com/registration");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(400, statusCode);
    }

    @Test
    public void givenFundooPush_UserRegistration_ShouldReturnEmailAlreadyExist() {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"email\":\"sudhakar.naidu@bridgelabz.com\",\"password\":\"abcd1234\"}")
                .when()
                .post("https://fundoopush-backend-dev.bridgelabz.com/registration");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(409, statusCode);       // response.then().body("id", Matchers.any(Integer.class));
    }

    @Test
    public void givenFundooPush_UserRegistration_ShouldReturnSuccesasfullye() {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"email\":\"taraj29833@mtsg.me\",\"password\":\"abcd1234\"}")
                .when()
                .post("https://fundoopush-backend-dev.bridgelabz.com/registration");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(201, statusCode);
    }

    @Test
    public void givenEmailAndPassword_WhenCorrect_ShouldReturnSuccessCode() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "usahu998@gmail.com");
        jsonObject.put("password", "123456");
        Response response = given()
                .body(jsonObject.toJSONString())
                .when()
                .contentType(ContentType.JSON)
                .post("https://fundoopush-backend-dev.bridgelabz.com/login");
        int code = response.statusCode();
        ResponseBody body = response.getBody();
        System.out.println("status code:" + code);
        Assert.assertEquals(200, code); //successful
        body.prettyPrint();
    }

    @Test
    public void givenLogin_WhenEmailCorrectAndPasswordEmpty_ShouldReturnSuccessCode() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "usahu998@gmail.com");
        jsonObject.put("password", "");
        Response response = given()
                .body(jsonObject.toJSONString())
                .when()
                .contentType(ContentType.JSON)
                .post("https://fundoopush-backend-dev.bridgelabz.com/login");
        int code = response.statusCode();
        ResponseBody body = response.getBody();
        System.out.println("status code:" + code);
        Assert.assertEquals(400, code);
        body.prettyPrint();
    }

    @Test
    public void givenLogin_WhenBothEmailAndPasswordIsEmpty_ShouldReturnSuccessCode() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "");
        jsonObject.put("password", "");
        Response response = given()
                .body(jsonObject.toJSONString())
                .when()
                .contentType(ContentType.JSON)
                .post("https://fundoopush-backend-dev.bridgelabz.com/login");
        int code = response.statusCode();
        ResponseBody body = response.getBody();
        System.out.println("status code:" + code);
        Assert.assertEquals(400, code);
        body.prettyPrint();
    }

    @Test
    public void givenLogoutApi_WhenTokenIsCorrect_ShouldReturnSuccessCode() {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7Il9pZCI6IjVlMDk4MmM1NGQyMjY3MDAzMjUzMGYwZCJ9LCJpYXQiOjE1Nzc2OTg0MzIsImV4cCI6MTU3Nzc4NDgzMn0.vgRpy-SxVrMi9-C1aRGcXL2KPvPAVRZSkxn8zVPVvfs")
                .when()
                .post("https://fundoopush-backend-dev.bridgelabz.com/logout");
        int statusCode = response.getStatusCode();
        System.out.println("Logged out successfully from the system : ");
        Assert.assertEquals(200, statusCode);
    }

    @Test
    public void givenLogoutApi_WhenTokenIsIncorrect_ShouldReturnStatusCode() {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("token", "DgzMn0.vgRpy-SxVrMi9-C1aRGcXL2KPvPAVRZSkxn8zVPVvfs")
                .when()
                .post("https://fundoopush-backend-dev.bridgelabz.com/logout");
        int statusCode = response.getStatusCode();
        System.out.println("Logout failed..." + statusCode);
        Assert.assertEquals(500, statusCode);
    }

    @Test
    public void givenRedirectPOSTApi_whenAllCredentialAreCorrect_shouldReturnCode() {
        File testUploadFile = new File("/home/admin1/IdeaProjects/FundooPush/src/test/resources/ganeshJi");
        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .header("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7Il9pZCI6IjVlMDk4MmM1NGQyMjY3MDAzMjUzMGYwZCJ9LCJpYXQiOjE1Nzc3Njc5MDcsImV4cCI6MTU3Nzg1NDMwN30.GFOMDD4313lBLFWqINcdhsMvktiyLxDrIs2y5rsuaoQ")
                .multiPart("image", testUploadFile)
                .formParam("title", "ganesh")
                .formParam("description", "ganeshji")
                .formParam("redirect_link", "www.google.com")
                .formParam("is_published", false)
                .formParam("archive", false)
                .formParam("youtube_flag", false)
                .formParam("youtube_url", "https://www.youtube.com/watch?v=QKKMxboJMcw")
                .formParam("video_link", "https://www.youtube.com/watch?v=QKKMxboJMcw")
                .when()
                .post("https://fundoopush-backend-dev.bridgelabz.com/redirects");
        int statusCode = response.getStatusCode();
        ResponseBody body = response.getBody();
        System.out.println(body.prettyPrint());
        System.out.println("Redirect Successfull..." + statusCode);
        Assert.assertEquals(201, statusCode);
    }

    @Test
    public void givenRedirectPUTApi_whenAllCredentialAreCorrect_shouldReturnCode() {
        File testUploadFile = new File("/home/admin1/IdeaProjects/FundooPush/src/test/resources/ganeshJi");
        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .header("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7Il9pZCI6IjVlMDk4MmM1NGQyMjY3MDAzMjUzMGYwZCJ9LCJpYXQiOjE1Nzc3Njc5MDcsImV4cCI6MTU3Nzg1NDMwN30.GFOMDD4313lBLFWqINcdhsMvktiyLxDrIs2y5rsuaoQ")
                .multiPart("image", testUploadFile)
                .formParam("_id", "5e0ad80a4d2267003253101b")
                .formParam("title", "god ganesha")
                .formParam("description", "new images of ganesha")
                .formParam("redirect_link", "www.facebook.com")
                .formParam("isDeleted", false)
                .formParam("is_published", false)
                .formParam("archive", false)
                .formParam("youtube_flag", false)
                .formParam("youtube_url", "https://www.youtube.com/watch?v=QKKMxboJMcw")
                .formParam("video_link", "https://www.youtube.com/watch?v=QKKMxboJMcw")
                .when()
                .put("https://fundoopush-backend-dev.bridgelabz.com/redirects");
        int statusCode = response.getStatusCode();
        ResponseBody body = response.getBody();
        System.out.println(body.prettyPrint());
        System.out.println("Redirect Successfull..." + statusCode);
        Assert.assertEquals(200, statusCode);
    }

    @Test
    public void givenRedirectGETApi_WhenTokenIsCorrect_ShouldReturnStatusCode() {
        Response response = given()
                .accept(ContentType.JSON)
                .header("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7Il9pZCI6IjVlMDk4MmM1NGQyMjY3MDAzMjUzMGYwZCJ9LCJpYXQiOjE1Nzc3Njc5MDcsImV4cCI6MTU3Nzg1NDMwN30.GFOMDD4313lBLFWqINcdhsMvktiyLxDrIs2y5rsuaoQ")
                .when()
                .get("https://fundoopush-backend-dev.bridgelabz.com/redirects");
        int statusCode = response.getStatusCode();
        ResponseBody body = response.getBody();
        System.out.println(body.prettyPrint());
        System.out.println("Redirect successfull..." + statusCode);
        Assert.assertEquals(200, statusCode);
    }

    @Test
    public void givenRedirectGETApi_WhenTokenIsInCorrect_ShouldReturnStatusCode() {
        Response response = given()
                .accept(ContentType.JSON)
                .header("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7Il9pZCI6IjVQyMjY3MDAzMjUzMGYwZCJ9LCJpYXQiOjE1Nzc3Njc5MDcsImV4cCI6MTU3Nzg1NDMwN30.GFOMDD4313lBLFWqINcdhsMvktiyLxDrIs2y5rsuaoQ")
                .when()
                .get("https://fundoopush-backend-dev.bridgelabz.com/redirects");
        int statusCode = response.getStatusCode();
        ResponseBody body = response.getBody();
        System.out.println(body.prettyPrint());
        System.out.println("Redirect successfull..." + statusCode);
        Assert.assertEquals(401, statusCode);
    }

    @Test
    public void givenRedirectGETApi_WhenTokenIsAPILinkisIncorrect_ShouldReturnStatusCode() {
        Response response = given()
                .accept(ContentType.JSON)
                .header("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7Il9pZCI6IjVlMDk4MmM1NGQyMjY3MDAzMjUzMGYwZCJ9LCJpYXQiOjE1Nzc3Njc5MDcsImV4cCI6MTU3Nzg1NDMwN30.GFOMDD4313lBLFWqINcdhsMvktiyLxDrIs2y5rsuaoQ")
                .when()
                .get("https://fundoopush-backend-dev.bridgelabz.com/redirects1");
        int statusCode = response.getStatusCode();
        ResponseBody body = response.getBody();
        System.out.println(body.prettyPrint());
        System.out.println("Redirect successfull..." + statusCode);
        Assert.assertEquals(404, statusCode);
    }


    @Test
    public void givenRedirectDELETEApi_WhenTokenAndIDAPIcorrect_ShouldReturnStatusCode() {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7Il9pZCI6IjVlMDk4MmM1NGQyMjY3MDAzMjUzMGYwZCJ9LCJpYXQiOjE1Nzc3Njc5MDcsImV4cCI6MTU3Nzg1NDMwN30.GFOMDD4313lBLFWqINcdhsMvktiyLxDrIs2y5rsuaoQ")
                .body("{\"_id\":\"5e0ad80a4d2267003253101b\"}")
                .when()
                .post("https://fundoopush-backend-dev.bridgelabz.com/redirects/delete");
                int statusCode = response.getStatusCode();
                ResponseBody body = response.getBody();
                System.out.println(body.prettyPrint());
                 System.out.println("Redirect successfull..." + statusCode);
                Assert.assertEquals(200, statusCode);
    }

    @Test
    public void givenHashTagEditApi_WhenTokenAndHashTagBodyIscorrect_ShouldReturnStatusCode() {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7Il9pZCI6IjVlMDk4MmM1NGQyMjY3MDAzMjUzMGYwZCJ9LCJpYXQiOjE1Nzc3Njc5MDcsImV4cCI6MTU3Nzg1NDMwN30.GFOMDD4313lBLFWqINcdhsMvktiyLxDrIs2y5rsuaoQ")
                .body("{\"redirect_id\":\"5e0ad80a4d2267003253101b\",\"hashtag\":\"#bridgelabz #solutions #mumbai #bangalore #fundoopush\"}")
                .when()
                .post("https://fundoopush-backend-dev.bridgelabz.com/hashtag/edit");
        int statusCode = response.getStatusCode();
        ResponseBody body = response.getBody();
        System.out.println(body.prettyPrint());
        System.out.println("Redirect successfull..." + statusCode);
        Assert.assertEquals(200, statusCode);
    }

    @Test
    public void givenHashTagEditApi_WhenTokenAndHashTagBodyIncorrect_ShouldReturnStatusCode() {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("token", "eyJiOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7Il9pZCI6IjVlMDk4MmM1NGQyMjY3MDAzMjUzMGYwZCJ9LCJpYXQiOjE1Nzc3Njc5MDcsImV4cCI6MTU3Nzg1NDMwN30.GFOMDD4313lBLFWqINcdhsMvktiyLxDrIs2y5rsuaoQ")
                .body("{\"redirect_id\":\"5ed80a4d2267003253101b\",\"hashtag\":\"#bridgelabz #solutions #mumbai #bangalore #fundoopush\"}")
                .when()
                .post("https://fundoopush-backend-dev.bridgelabz.com/hashtag/edit");
        int statusCode = response.getStatusCode();
        ResponseBody body = response.getBody();
        System.out.println(body.prettyPrint());
        System.out.println("Redirect successfull..." + statusCode);
        Assert.assertEquals(401, statusCode);
    }

    @Test
    public void givenHashTagEditApi_WhenTokenAndHashTagBodyIscorrectButAPI_Incorrect_ShouldReturnStatusCode() {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7Il9pZCI6IjVlMDk4MmM1NGQyMjY3MDAzMjUzMGYwZCJ9LCJpYXQiOjE1Nzc3Njc5MDcsImV4cCI6MTU3Nzg1NDMwN30.GFOMDD4313lBLFWqINcdhsMvktiyLxDrIs2y5rsuaoQ")
                .body("{\"redirect_id\":\"5e0ad80a4d2267003253101b\",\"hashtag\":\"#bridgelabz #solutions #mumbai #bangalore #fundoopush\"}")
                .when()
                .post("https://fundoopush-backend-dev.bridgelabz.com/hashtag/edit123");
        int statusCode = response.getStatusCode();
        ResponseBody body = response.getBody();
        System.out.println(body.prettyPrint());
        System.out.println("Redirect successfull..." + statusCode);
        Assert.assertEquals(404, statusCode);
    }
}
