package com.mpesa.Mpesa.Controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mpesa.Mpesa.dto.Message;
import com.mpesa.Mpesa.dto.Payment;
import com.mpesa.Mpesa.dto.Sms;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @PostMapping("/init")
    public Map initStkpush(@RequestBody Payment payment){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Map map =new HashMap();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, "amount="+payment.getAmount()+"&msisdn="+payment.getPhone());
        Request request = new Request.Builder()
                .url("https://tinypesa.com/api/v1/express/initialize")
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Apikey", "BANjj89qrU1")
                .build();
        try {
            Response response = client.newCall(request).execute();
            map.put("Success",true);
            map.put("message",response.body().string());

        } catch (IOException e) {
            map.put("Success",false);
            map.put("error","failed to initiate");
            throw new RuntimeException(e);
        }
        return map;

    }
    @PostMapping("/sms")
    public Map sendSms(@RequestBody Sms sms){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Map map =new HashMap();
        MediaType mediaType = MediaType.parse("application/json");
        okhttp3.RequestBody body =  okhttp3.RequestBody.create(mediaType, " {\n" +
                "                       \"api_key\":\"5cfcaadcf19e0\",\n" +
                "                        \"username\":\"justus\",\n" +
                "                        \"sender_id\":\"PAYLIFE\",\n" +
                "                        \"message\":\""+sms.getBody()+"\",\n" +
                "                        \"phone\":\""+sms.getPhone()+"\"\n" +
                "   }");
        Request request = new Request.Builder()
                .url("http://bulksms.mobitechtechnologies.com/api/sendsms")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            Gson gson = new Gson();
            gson.fromJson(response.body().string(),new TypeToken<Map<String,Object>>(){}.getType());
            map.put("Success",true);
            map.put("message",response.body().string());
        } catch (IOException e) {
            map.put("Success",false);
            map.put("error","failed to initiate");
            throw new RuntimeException(e);
        }
        return map;
    }
    @PostMapping("stkpush")
    public HashMap<String,Object> map(@RequestBody  Payment payment){
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        HashMap<String, Object> map = new HashMap<>();
        MediaType mediaType = MediaType.parse("application/json");
        String requestbody="{\n" +
                "                \"BusinessShortCode\": 174379,\n" +
                "                \"Password\": \"MTc0Mzc5YmZiMjc5ZjlhYTliZGJjZjE1OGU5N2RkNzFhNDY3Y2QyZTBjODkzMDU5YjEwZjc4ZTZiNzJhZGExZWQyYzkxOTIwMjMwNDI0MTI0OTU3\",\n" +
                "                \"Timestamp\": \"20230424124957\",\n" +
                "                \"TransactionType\": \"CustomerPayBillOnline\",\n" +
                "                \"Amount\": 1,\n" +
                "                \"PartyA\": "+payment.getPhone()+",\n" +
                "                \"PartyB\": 174379,\n" +
                "                \"PhoneNumber\": "+payment.getPhone()+",\n" +
                "                \"CallBackURL\": \"https://mydomain.com/path\",\n" +
                "                \"AccountReference\": \"CompanyXLTD\",\n" +
                "                \"TransactionDesc\": \"Payment of X\"\n" +
                "  }";
        okhttp3.RequestBody  body = okhttp3.RequestBody.create(mediaType,requestbody );
        System.out.print(requestbody);
        Request request = new Request.Builder()
                .url("https://sandbox.safaricom.co.ke/mpesa/stkpush/v1/processrequest")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer BsxELXMWrynFpZLaIYHGm4sSz7Gn")
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseBody=response.body().string();
            map.put("success",true);
            map.put("message",responseBody);
            return map;
        } catch (IOException e) {
            map.put("success",false);
            map.put("message","error");

            throw new RuntimeException(e);

        }
    }
}

