package specification;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specs {
    public static RequestSpecification defaultRequestSpec = defaultRequestSpec();
    public static ResponseSpecification defaultResponseSpec = defaultResponseSpec();

    public static RequestSpecification defaultRequestSpec() {

        return new RequestSpecBuilder()
                .setBaseUri("https://reqres.in/api")
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    public static ResponseSpecification defaultResponseSpec() {

            return new ResponseSpecBuilder()
                    .log(LogDetail.ALL)
                    .build();
        }



}
