package com.research.gradesheet.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.research.gradesheet.model.Response;
import org.apache.commons.collections4.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class SheetViewService {

    public String createSheetView()throws IOException{

        String apikey = "88ed3291bb2892562eea32684cf07587";
        String url = "https://sheet.zoho.com/sheet/remotedoc.im?apikey="+apikey;
        String mode = "view";
        String content = "D:/CSE 487/gradesheet/uploadedFiles/grade.xlsx";

        final Logger logger = LoggerFactory.getLogger(this.getClass());

        //Make the request header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        //Make the request body
        MultiValueMap<String,String> req_payload = new LinkedMultiValueMap<>();
        req_payload.add("file",content);
        req_payload.add("mode",mode);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(req_payload,headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,request,String.class);
        String response = responseEntity.getBody();

        String sheetViewUrl = response.substring(response.lastIndexOf("https"));

        return sheetViewUrl;

    }

}
