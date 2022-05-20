package smokeTests;

import io.restassured.builder.MultiPartSpecBuilder;
import my.requests.RegisterSuccessRequest;
import my.responses.AuthResponses.RegisterSuccessResponse;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static specification.Specs.defaultRequestSpec;
import static specification.Specs.defaultResponseSpec;

public class UploadTest {

    @Test
    public void registerSuccess2() {
        var response =
            given()
                .spec(defaultRequestSpec.baseUri("http://users.bugred.ru/tasks/rest/addavatar")
                .multiPart(new MultiPartSpecBuilder(new File ("src/test/resources/photo.jpg"))
                        .controlName("avatar")
                        .fileName("test")
                        .build()
                    ))
                .multiPart(new MultiPartSpecBuilder("lunatikov@mail.ru")
                        .controlName("email")
                        .build()
                ).contentType("multipart/form-data")
                .header("XSS", "test123")
            .when()
                .post()
            .then()
                .spec(defaultResponseSpec)
                .statusCode(200);

    }


}
