package com.research.gradesheet.service;

import com.research.gradesheet.model.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class SheetViewService {

    public String createSheetView(){

        final String apikey = "88ed3291bb2892562eea32684cf07587";
        final String url = "https://sheet.zoho.com/sheet/remotedoc.im?apikey="+apikey;
        String mode = "view";
        String content = "D:/CSE 487/gradesheet/uploadedFiles/grade.xlsx";

        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        Map map = new HashMap<String,String>();
        map.put("Content-Type", "application/json");
        headers.setAll(map);
        Map req_payload = new HashMap();
        req_payload.put(content,mode);

        HttpEntity<?> request = new HttpEntity<>(req_payload,headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Response> responseEntity = restTemplate.postForEntity(url,request,Response.class);
        Response response = responseEntity.getBody();

        return response.getURL();
    }

}
