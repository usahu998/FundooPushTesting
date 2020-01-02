package com.bridgelabz.fundoopush;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class UserRegistration {

    private String tokenValue;



    @Before
    public void getToken() throws ParseException {
        Response response = RestAssured.given().
                contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"email\":\"usahu998@gmail.com\",\"password\":\"123456\"}")
                .when()
                .post("https://fundoopush-backend-dev.bridgelabz.com/login");
        ResponseBody body = response.getBody();
        JSONObject object = (JSONObject) new JSONParser().parse(body.print());
        tokenValue= (String)object.get("token");
    }

    public static  String Usertoken="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7Il9pZCI6IjVlMDk4MmM1NGQyMjY3MDAzMjUzMGYwZCJ9LCJpYXQiOjE1Nzc5NDQzOTQsImV4cCI6MTU3ODAzMDc5NH0.nNIZt593VIerf8NazT6UsNwLRhmVMkAkFRayl_c0AOo";
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
                .header("token",tokenValue)
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
                .header("token",Usertoken)
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
                .header("token",tokenValue)
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
                .header("token", tokenValue)
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
    public void givenHashTagEditPOSTApi_WhenTokenAndHashTagBodyIscorrect_ShouldReturnStatusCode() {
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
    public void givenHashTagEditPOSTApi_WhenTokenAndHashTagBodyIncorrect_ShouldReturnStatusCode() {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7Il9pZCI6IjVlMDk4MmM1NGQyMjY3MDAz")
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
    public void givenHashTagEditPOSTApi_WhenTokenAndHashTagBodyIscorrectButAPI_Incorrect_ShouldReturnStatusCode() {
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

    @Test
    public void givenHashTagEditGETApi_WhenTokenAndHashTagNameIscorrect_ShouldReturnStatusCode() {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7Il9pZCI6IjVlMDk4MmM1NGQyMjY3MDAzMjUzMGYwZCJ9LCJpYXQiOjE1Nzc3Njc5MDcsImV4cCI6MTU3Nzg1NDMwN30.GFOMDD4313lBLFWqINcdhsMvktiyLxDrIs2y5rsuaoQ")
                .pathParam("hashtagname", "#bridgelabz")
                .when()
                .get("https://fundoopush-backend-dev.bridgelabz.com/redirects/hashtag/{hashtagname}");
        int statusCode = response.getStatusCode();
        ResponseBody body = response.getBody();
        System.out.println(body.prettyPrint());
        System.out.println("Redirect successfull..." + statusCode);
        Assert.assertEquals(200, statusCode);
    }

    @Test
    public void givenHashTagEditGETApi_WhenTokenAndHashTagNameIscorrectButAPI_Incorrect_ShouldReturnStatusCode() {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7Il9pZCI6IjVlMDk4MmM1NGQyMjY3MDAzMjUzMGYwZCJ9LCJpYXQiOjE1Nzc3Njc5MDcsImV4cCI6MTU3Nzg1NDMwN30.GFOMDD4313lBLFWqINcdhsMvktiyLxDrIs2y5rsuaoQ")
                .pathParam("hashtagname", "#Mumbai")
                .when()
                .get("https://fundoopush-backend-dev.bridgelabz.com/redir/hashtag/{hashtagname}");
        int statusCode = response.getStatusCode();
        ResponseBody body = response.getBody();
        System.out.println(body.prettyPrint());
        System.out.println("User not found" + statusCode);
        Assert.assertEquals(404, statusCode);
    }

    @Test
    public void givenHashTagSearchPOSTApi_WhenTokenAndHashTagbodyIscorrect_ShouldReturnStatusCode() throws ParseException {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("token",tokenValue)
                .body("{\"hashtag\":\"#bridgelabz\"}")
                .when()
                .post("https://fundoopush-backend-dev.bridgelabz.com/search/hashtag");
        int statusCode = response.getStatusCode();
        ResponseBody body = response.getBody();
        System.out.println(body.prettyPrint());
        System.out.println("Redirect successfull..." + statusCode);
        // Assert.assertEquals(200, statusCode);

        JSONObject object = (JSONObject) new JSONParser().parse(body.prettyPrint());
        boolean status = (boolean) object.get("status");
        String message = (String) object.get("message");

        Assert.assertEquals(200, statusCode);
        Assert.assertEquals("Successfully searched data", message);
        Assert.assertTrue(status);
    }

    @Test
    public void givenHashTagSearchPOSTApi_WhenHashTagbodyIsCorrectAndTokenIncorrect_ShouldReturnStatusCode() {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("token", "iOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7Il9pZCI6IjVlMDk4MmM1NGQyMjY3MDAzMjUzMGYwZCJ9LCJpYXQiOjE1Nzc3Njc5MDcsImV4cCI6MTU3Nzg1NDMwN30.GFOMDD4313lBLFWqINcdhsMvktiyLxDrIs2y5rsuaoQ")
                .body("{\"hashtag\":\"#bridgelabz\"}")
                .when()
                .post("https://fundoopush-backend-dev.bridgelabz.com/search/hashtag");
        int statusCode = response.getStatusCode();
        ResponseBody body = response.getBody();
        System.out.println(body.prettyPrint());
        System.out.println("Redirect successfull..." + statusCode);
        Assert.assertEquals(401, statusCode);
    }

    @Test
    public void givenTokenAndJobData_WhenCorrect_ShouldPostJob() throws ParseException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("redirect_id", "5d41270b0d205f00a7687cbd");
        jsonObject.put("years_of_experience", 2);
        jsonObject.put("salary", 3.6);
        jsonObject.put("location", "Pune");
        jsonObject.put("company_profile", "Automation");
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("token", tokenValue)
                .body(jsonObject.toJSONString())
                .when()
                .post("https://fundoopush-backend-dev.bridgelabz.com/jobs");
        int status = response.getStatusCode();
        String string = response.asString();
        System.out.println(string);
        MatcherAssert.assertThat(status, Matchers.equalTo(HttpStatus.SC_OK));
        ResponseBody body = response.getBody();
    }


    @Test
    public void givenJobIdAndHashtagName_WhenCorrect_ShouldAddHashtagForJob() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("job_id", "5e0d88aa3b17ce008e85dc26");
        jsonObject.put("hashtag", "#Bridgelabz #Mumbai");
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("token", tokenValue)
                .body(jsonObject.toJSONString())
                .when()
                .post("https://fundoopush-backend-dev.bridgelabz.com/jobs/hashtag/add");
        int status = response.getStatusCode();
        String string = response.asString();
        System.out.println(string);
        MatcherAssert.assertThat(status, Matchers.equalTo(HttpStatus.SC_OK));
    }

    @Test
    public void givenJobIdAndHashtagId_WhenCorrect_ShouldRemoveTheHashtagFromExistingJob() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("job_id", "5e0d96353b17ce008e85dc65");
        jsonObject.put("hashtag_id", "5d41270b0d205f00a7687cc0");
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("token", tokenValue)
                .body(jsonObject.toJSONString())
                .when()
                .post("https://fundoopush-backend-dev.bridgelabz.com/jobs/hashtag/remove");
        int status = response.getStatusCode();
        String string = response.asString();
        System.out.println(string);
        MatcherAssert.assertThat(status, Matchers.equalTo(HttpStatus.SC_OK));
    }



}
