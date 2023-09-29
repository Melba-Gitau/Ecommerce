package com.mpesa.mpesaservice.Controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mpesa.mpesaservice.DTO.Details;
import com.mpesa.mpesaservice.Model.Mpesa;
import com.mpesa.mpesaservice.Service.MpesaService;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class MpesaApi {
    @Autowired
    MpesaService mpesaService;
    @PostMapping("/stkpush")
    public HashMap<String, Object> map(@RequestBody Details details){
        HashMap<String, Object> map = new HashMap<>();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        String accessToken = getAccessToken();
        MediaType mediaType = MediaType.parse("application/json");
        okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, "{\n" +
                "                \"BusinessShortCode\": 174379,\n" +
                "                \"Password\": \"MTc0Mzc5YmZiMjc5ZjlhYTliZGJjZjE1OGU5N2RkNzFhNDY3Y2QyZTBjODkzMDU5YjEwZjc4ZTZiNzJhZGExZWQyYzkxOTIwMjMwNTIyMDgyMzIz\",\n" +
                "                \"Timestamp\": \"20230522082323\",\n" +
                "                \"TransactionType\": \"CustomerPayBillOnline\",\n" +
                "                \"Amount\":"+details.getAmount()+",\n" +
                "                \"PartyA\": \""+details.getPhone()+"\",\n" +
                "                \"PartyB\": 174379,\n" +
                "                \"PhoneNumber\":\""+details.getPhone()+"\",\n" +
                "                \"CallBackURL\": \"https://quick-donuts-rest.loca.lt/api/xzcstr/url\",\n" +
                "                \"AccountReference\": \"CompanyXLTD\",\n" +
                "                \"TransactionDesc\": \"Payment of X\" \n" +
                "  }");
    System.out.print("{\n" +
            "                \"BusinessShortCode\": 174379,\n" +
            "                \"Password\": \"MTc0Mzc5YmZiMjc5ZjlhYTliZGJjZjE1OGU5N2RkNzFhNDY3Y2QyZTBjODkzMDU5YjEwZjc4ZTZiNzJhZGExZWQyYzkxOTIwMjMwNTIyMDgyMzIz\",\n" +
            "                \"Timestamp\": \"20230522082323\",\n" +
            "                \"TransactionType\": \"CustomerPayBillOnline\",\n" +
            "                \"Amount\":"+details.getAmount()+",\n" +
            "                \"PartyA\": \""+details.getPhone()+"\",\n" +
            "                \"PartyB\": 174379,\n" +
            "                \"PhoneNumber\":\""+details.getPhone()+"\",\n" +
            "                \"CallBackURL\": \"https://quick-donuts-rest.loca.lt/api/xzcstr/url\",\n" +
            "                \"AccountReference\": \"CompanyXLTD\",\n" +
            "                \"TransactionDesc\": \"Payment of X\" \n" +
            "  }");
        Request request = new Request.Builder()
                .url("https://sandbox.safaricom.co.ke/mpesa/stkpush/v1/processrequest")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + accessToken)
                .build();
        //decode response to the stk-request sent
        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            //decoding and saving the response in database
            Gson gson = new Gson();
            JsonObject stk_push = gson.fromJson(responseBody, JsonObject.class);
            Mpesa mpesa = new Mpesa();
            mpesa.setPhone(details.getPhone());
            mpesa.setAmount(details.getAmount());
            mpesa.setUsername(details.getUsername());
            System.out.print(responseBody);
            mpesa.setMerchantId(stk_push.get("MerchantRequestID").getAsString());
            mpesa.setCheckoutId(stk_push.get("CheckoutRequestID").getAsString());
            mpesaService.save(mpesa);
            map.put("Success",true);
            map.put("message","successful");
            return map;
        } catch (IOException e) {
            map.put("success", false);
            map.put("message", "error");
            throw new RuntimeException(e);
        }
    }
    //get access token.
    public String getAccessToken(){
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url("https://sandbox.safaricom.co.ke/oauth/v1/generate?grant_type=client_credentials")
                .method("GET", null)
                .addHeader("Authorization", "Basic cFJZcjZ6anEwaThMMXp6d1FETUxwWkIzeVBDa2hNc2M6UmYyMkJmWm9nMHFRR2xWOQ==")
                .build();
        try{
            Response response = client.newCall(request).execute();
            String response_body = response.body().string();
            Gson gson = new Gson();
            Map accessToken = gson.fromJson(response_body, Map.class);
            System.out.print(accessToken);
            return accessToken.get("access_token").toString();

        }catch(IOException e){
            throw new RuntimeException(e);
        }

    }
    //processes the response received from mpesa stkpush
    @PostMapping("/xzcstr/url")
    public void getURL(@RequestBody String response) {
        System.out.print(response);
        //extracts relevant info from json
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
        JsonObject Body = jsonObject.get("Body").getAsJsonObject();
        JsonObject stkCallback = Body.get("stkCallback").getAsJsonObject();
        String MerchantRequestID = stkCallback.get("MerchantRequestID").getAsString();
        String CheckoutRequestID = stkCallback.get("CheckoutRequestID").getAsString();
        double ResultCode = stkCallback.get("ResultCode").getAsDouble();

        //checks if transaction was successful if successful extracts amount and receipt
        if (ResultCode == 0) {
            JsonObject CallbackMetaData = stkCallback.get("CallbackMetadata").getAsJsonObject();
            JsonArray Item = CallbackMetaData.get("Item").getAsJsonArray();
            JsonObject index = Item.get(0).getAsJsonObject();
            double amount = index.get("Value").getAsDouble();
            JsonObject index1 = Item.get(1).getAsJsonObject();
            String receipt = index1.get("Value").getAsString();
            Optional<Mpesa> transaction_code = mpesaService.findByMerchantIdAndCheckoutId(MerchantRequestID, CheckoutRequestID);
            if (transaction_code.isPresent()) {
                Mpesa mpesa = transaction_code.get();
                mpesa.setTransactionCode(receipt);
                mpesa.setStatus(1);
                mpesaService.save(mpesa);
                //sends confirmation message to customer
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("application/json");
                okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, "{\r\n " +
                        "\"phone\":\""+mpesa.getPhone()+"\",\r\n    " +
                        "\"message\":\"Confirmed, "+mpesa.getUsername()+" has paid to MELBA Ksh."+mpesa.getAmount()+"\",\r\n}");
                Request request = new Request.Builder()
                        .url("localhost:8080/message/sms")
                        .method("POST", body)
                        .addHeader("Content-Type", "application/json")
                        .build();

                try {
                    Response response_url = client.newCall(request).execute();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
//if transaction failed
        } else {
            Optional<Mpesa> transaction_code = mpesaService.findByMerchantIdAndCheckoutId(MerchantRequestID, CheckoutRequestID);
            if (transaction_code.isPresent()) {
                Mpesa mpesa = transaction_code.get();
                mpesa.setStatus(2);
                mpesaService.save(mpesa);

            }
        }
    }

}
