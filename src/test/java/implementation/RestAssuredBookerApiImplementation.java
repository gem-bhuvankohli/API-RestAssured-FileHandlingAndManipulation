package implementation;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import logger.Log;
import org.json.JSONObject;
import org.junit.Assert;
import utils.BookerRestUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class RestAssuredBookerApiImplementation {
    static String bookingID;

    //  POST REQUEST
    public static void post() {
        try {
//          Fetching payload from data folder
            FileReader fileReader = new FileReader("src/test/resources/data/bookingDetails.json");
            int i;
            StringBuilder payload = new StringBuilder();
            while ((i = fileReader.read()) != -1)
                payload.append((char) i);
            fileReader.close();
            Log.info("**********************POST REQUEST**********************");
            Response res = BookerRestUtils
                    .postResponse("/booking", payload.toString());

            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
            Date date = new Date();

//          Writing data in postReq folder
            File postRes = new File("src/test/resources/responseData/postReq/postReq-" + formatter.format(date) + ".json");
            FileWriter fileWriter = new FileWriter(postRes);
            fileWriter.write(res.asPrettyString());
            fileWriter.close();
//          Fetching Booking ID
            JsonPath jsonPath = new JsonPath(res.asString());
            bookingID = jsonPath.getString("bookingid");

            int statusCode = res.getStatusCode();
            Assert.assertEquals(200, statusCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  PATCH REQUEST
    public static void patch() {
        try {
//      PATCH Payload
            JSONObject payload = new JSONObject();
            payload.put("firstname", "changedName");
//      Authenticating by successfully updating the payload using PATCH method of RestAssured
            Log.info("**********************PATCH REQUEST**********************");
            Response res = BookerRestUtils
                    .patchResponse("/booking", payload.toString(), bookingID);
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
            Date date = new Date();
//          Writing data in patchReq folder
            File patchRes = new File("src/test/resources/responseData/patchReq/patchReq-" + formatter.format(date) + ".json");
            FileWriter fileWriter = new FileWriter(patchRes);
            fileWriter.write(res.asPrettyString());
            fileWriter.close();
            int statusCode = res.getStatusCode();
            Assert.assertEquals(200, statusCode);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    //PUT REQUEST
    public static void put() {
        try {
//      PUT Payload
            JSONObject payload = new JSONObject();
            payload.put("firstname", "changedName");
            payload.put("lastname", "changedLastName");
            payload.put("totalprice", 222);
            payload.put("depositpaid", true);
            JSONObject innerList = new JSONObject();
            innerList.put("checkin", "2018-01-01");
            innerList.put("checkout", "2019-01-01");
            payload.put("bookingdates", innerList);
            payload.put("additionalneeds", "Breakfast");

//      Authenticating by successfully updating the payload using PUT method of RestAssured
            Log.info("Authentication Token : " + new String(Base64.getEncoder().encode(payload.toString().getBytes())));
            Log.info("**********************PUT REQUEST**********************");
            Response res = BookerRestUtils.putResponse("/booking", payload.toString(), bookingID);
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
            Date date = new Date();
//          Writing data in putReq folder
            File putRes = new File("src/test/resources/responseData/putReq/putReq-" + formatter.format(date) + ".json");
            FileWriter fileWriter = new FileWriter(putRes);
            fileWriter.write(res.asPrettyString());
            fileWriter.close();


            int statusCode = res.getStatusCode();
            Assert.assertEquals(200, statusCode);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    //  DELETE REQUEST
    public static void delete() {
        try {
//      Authenticating by successfully updating the payload using DELETE method of RestAssured
            Log.info("**********************DELETE REQUEST**********************");
            Response res = BookerRestUtils.deleteResponse("/booking", bookingID);
            int statusCode = res.getStatusCode();
            Assert.assertEquals(201, statusCode);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    //  VALIDATING DELETE USING GET REQUEST
    public static void validateDelete() {
        try {
            Log.info("****************VALIDATING DELETE USING GET REQUEST****************");
            Response res = BookerRestUtils
                    .getResponse("/booking", bookingID);
            int statusCode = res.getStatusCode();
/*     Note*:
       If status code shows 404 it means the requested data is not
       found hence, delete operation was successful.
 */
            Assert.assertEquals(404, statusCode);
            Log.info("REQUESTS VALIDATED SUCCESSFULLY!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}