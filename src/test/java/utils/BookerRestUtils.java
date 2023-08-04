package utils;

import static io.restassured.RestAssured.given;
import specbuilder.BookerRequestSpecificationBuilder;
import io.restassured.response.Response;
public class BookerRestUtils {
    public static Response getResponse(String endpoint, String id) {
        return given(BookerRequestSpecificationBuilder.getRequestSpecificationWithoutAuth())
                .pathParam("id", id)
                .get(endpoint + "/{id}")
                .then()
                .log()
                .all()
                .extract()
                .response();
    }

    public static Response postResponse(String endpoint, Object payload) {
        return given(BookerRequestSpecificationBuilder.getRequestSpecificationWithoutAuth())
                .body(payload)
                .post(endpoint)
                .then()
                .log()
                .all()
                .extract()
                .response();
    }

    public static Response putResponse(String endpoint, Object payload, String id) {
        return given(BookerRequestSpecificationBuilder.getRequestSpecificationWithAuth())
                .pathParam("id", id)
                .body(payload)
                .put(endpoint + "/{id}")
                .then()
                .log()
                .all()
                .extract()
                .response();
    }

    public static Response patchResponse(String endpoint, Object payload, String id) {
        return given(BookerRequestSpecificationBuilder.getRequestSpecificationWithAuth())
                .pathParam("id", id)
                .body(payload)
                .patch(endpoint + "/{id}")
                .then()
                .log()
                .all()
                .extract()
                .response();
    }

    public static Response deleteResponse(String endpoint, String id) {
        return given(BookerRequestSpecificationBuilder.getRequestSpecificationWithAuth())
                .pathParam("id", id)
                .delete(endpoint + "/{id}")
                .then()
                .log()
                .all()
                .extract()
                .response();
    }

}
