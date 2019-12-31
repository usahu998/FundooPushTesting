package com.bridgelabz.fundoopush;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

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
        Assert.assertEquals(201, statusCode);       // response.then().body("id", Matchers.any(Integer.class));
    }
/*
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7Il9pZCI6IjVlMDk4MmM1NGQyMjY3MDAzMjUzMGYwZCJ9LCJpYXQiOjE1Nzc2OTg0MzIsImV4cCI6MTU3Nzc4NDgzMn0.vgRpy-SxVrMi9-C1aRGcXL2KPvPAVRZSkxn8zVPVvfs
*/

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
        Assert.assertEquals(200, statusCode);       // response.then().body("id", Matchers.any(Integer.class));
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
        Assert.assertEquals(500, statusCode);       // response.then().body("id", Matchers.any(Integer.class));
    }
}
