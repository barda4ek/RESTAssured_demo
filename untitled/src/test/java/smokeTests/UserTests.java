package smokeTests;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import my.apitests.CRUD;
import my.pojos.UserPojo;
import my.requests.UserRequest;
import my.responses.PatchUserResponse;
import my.responses.PutUserResponse;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;
import static specification.Specs.*;

public class UserTests implements CRUD {


    @Step("Создание юзера")
    @Test
        public void create() {
        UserRequest testBody = new UserRequest();
            given()
                .spec(defaultRequestSpec)
                .basePath("/users/")
                .body(testBody)
            .when()
                .post()
            .then()
                .spec(defaultResponseSpec)
                .statusCode(201);
}

    @Test
        public void get() {
        final int  ID = 2;
            var response = given()
                .spec(defaultRequestSpec)
                .basePath("/users/" + ID)
            .when()
                .get()
            .then()
                .spec(defaultResponseSpec)
                .statusCode(200)
                .extract().as(UserPojo.class);
            assertThat(response.getData().getFirstName(), equalTo("Janet"));
            assertThat(response.getData().getLastName(), equalTo("Weaver"));
            assertThat(response.getData().getEmail(), equalTo("janet.weaver@reqres.in"));
            assertThat(response.getData().getId(), equalTo(2));
    }

    @Test
    public void delete() {
        final int  ID = 1;
            given()
                .spec(defaultRequestSpec)
                .basePath("/users/" + ID)
            .when()
                .delete()
            .then()
                .spec(defaultResponseSpec)
                .statusCode(204);
    }

    @Test
    public void put() {
        var testBody = new UserRequest();
                testBody.setName("Johny");
                testBody.setJob("QA2");
        final int  ID = 2;
            var putUser = given()
                .spec(defaultRequestSpec)
                .basePath("/users/" + ID)
                .body(new Gson().toJson(testBody))
            .when()
                .put()
            .then()
                .spec(defaultResponseSpec)
                .statusCode(200)
                .extract().as(PutUserResponse.class);
        assertThat(putUser.getName(), equalTo("Johny"));
        assertThat(putUser.getJob(), equalTo("QA2"));
        //assertThat(putUser.getUpdatedAt(), equalTo("janet.weaver@reqres.in"));
    }

    @Test
    public void patch() {
        var testBody = new UserRequest();
            testBody.setName("Johny");
            testBody.setJob("QA2");
        final int  ID = 2;
            var response =
            given()
                .spec(defaultRequestSpec)
                .basePath("/users/" + ID)
                .body(new Gson().toJson(testBody))
            .when()
                .patch()
            .then()
                .spec(defaultResponseSpec)
                .statusCode(200)
                .extract().as(PatchUserResponse.class);
        assertThat(response.getName(), equalTo("Johny"));
        assertThat(response.getJob(), equalTo("QA2"));
    }

    @Test
    public void userNotFound() {
        final int  ID = 23;
        var response =
            given()
                .spec(defaultRequestSpec)
                .basePath("/users/" + ID)
            .when()
                .get()
            .then()
                .spec(defaultResponseSpec)
                .statusCode(404);
    }

}
