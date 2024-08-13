package utilities;

import entities.RequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class CashWiseToken {

    public static  String GetToken(){

        String endpoint = "https://backend.cashwise.us/api/myaccount/auth/login";
        RequestBody requestBody = new RequestBody();
        requestBody.setEmail("amanbaev62@gmail.com");
        requestBody.setPassword("Amanbaev1997");
        Response response = RestAssured.given().contentType(ContentType.JSON).body(requestBody).post(endpoint);

        return response.jsonPath().getString("jwt_token");
    }


    @Test
    public void GetSingleSeller() {

        String url = Config.getProperty("cashwise") + "/api/myaccount/sellers/" + 4282;
        String token = CashWiseToken.GetToken();
        Response response = RestAssured.given().auth().oauth2(token).get(url);

        String expectedEmail = response.jsonPath().getString("email");
        String expectedId = response.jsonPath().getString("4282");


        Assert.assertTrue(expectedEmail.endsWith(".com"));
    }

    @Test
         public void GetAllSellers (){
            String url = Config.getProperty("cashwise") + "/api/myaccount/sellers";
            String token = CashWiseToken.GetToken();

            Map<String, Object> params = new HashMap<>();
            params.put("isArchived", false);
            params.put("size", 10);
            params.put ("page",1);

            Response response = RestAssured.given().auth().oauth2(token).params(params).get(url);
            int statusCode = response.statusCode();
            Assert.assertEquals(200, statusCode);
//            response.prettyPrint();
            String email = response.jsonPath().getString("responses[0].email");
            Assert.assertFalse(email.isEmpty());
            String email3 = response.jsonPath().getString("responses[2].email");
            Assert.assertFalse(email3.isEmpty());
            String email5 = response.jsonPath().getString("responses[1].email");
            Assert.assertFalse(email5.isEmpty());
        }

        @Test
    public void GetAllSellersLoop(){

        String url = Config.getProperty("cashwise") + "/api/myaccount/sellers/";
        String token = CashWiseToken.GetToken();
        Map<String,Object> allSeller = new HashMap<>();
        allSeller.put("isArchived",false);
        allSeller.put("size",3);
        allSeller.put("page", 1);
        Response response = RestAssured.given().auth().oauth2(token).params(allSeller).get(url);
        int statusCode = response.statusCode();
        Assert.assertEquals(200, statusCode);

            List< String> listOfEmail = response.jsonPath().getList("response.email");
            for(String emails:listOfEmail){
                Assert.assertFalse(emails.isEmpty());
            }




        }









}






