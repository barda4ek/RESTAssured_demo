package smokeTests;

import my.requests.*;
import my.responses.AuthResponses.*;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static specification.Specs.*;

public class AuthTests {

    @Test
    public void registerSuccess() {
        var requestBody = new RegisterSuccessRequest();
        var response =
                given()
                    .spec(defaultRequestSpec)
                    .basePath("/register")
                    .body(requestBody)
                .when()
                    .post()
                .then()
                    .spec(defaultResponseSpec)
                    .statusCode(200)
                    .extract().as(RegisterSuccessResponse.class);
            assertThat(response.getToken(), equalTo("QpwL5tke4Pnpja7X4"));
    }

    @Test
    public void registerUnsuccess() {
        var requestBody = new RegisterSuccessRequest();
        requestBody.setPassword(null);
        var response =
                given()
                    .spec(defaultRequestSpec)
                    .basePath("/register")
                    .body(requestBody)
                .when()
                    .post()
                .then()
                    .spec(defaultResponseSpec)
                    .statusCode(400)
                    .extract().as(RegisterUnsuccessResponse.class);
        assertThat(response.getError(), equalTo("Missing password"));
    }

    @Test
    public void loginSuccess() {
        var requestBody = new LoginSuccessRequest();
        var response =
                given()
                    .spec(defaultRequestSpec)
                    .basePath("/login")
                    .body(requestBody)
                .when()
                    .post()
                .then()
                    .spec(defaultResponseSpec)
                    .statusCode(200)
                    .extract().as(LoginSuccessResponse.class);
        assertThat(response.getToken(), equalTo("QpwL5tke4Pnpja7X4"));
    }

    @Test
    public void loginUnsuccess() {
        var requestBody = new LoginSuccessRequest();
        requestBody.setPassword(null);
        var response =
                given()
                    .spec(defaultRequestSpec)
                    .basePath("/login")
                    .body(requestBody)
                .when()
                    .post()
                .then()
                    .spec(defaultResponseSpec)
                    .statusCode(400)
                    .extract().as(LoginUnsuccessResponse.class);
        assertThat(response.getError(), equalTo("Missing password"));
    }


}
