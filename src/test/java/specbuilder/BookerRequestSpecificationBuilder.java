package specbuilder;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BookerRequestSpecificationBuilder {

    public static Properties properties = new Properties();
    public static String baseUri;
    public static String authToken;

    public static void properties(){
        try (InputStream inputStream = new FileInputStream("src/config.properties")) {
            properties.load(inputStream);

            baseUri = properties.getProperty("baseUri");
            authToken = properties.getProperty("authToken");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static RequestSpecification getRequestSpecificationWithAuth() {
        properties();
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", authToken)
                .log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification getRequestSpecificationWithoutAuth() {
        properties();
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .log(LogDetail.ALL)
                .build();
    }
}
