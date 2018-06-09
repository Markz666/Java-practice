package com.zmx.flipkart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.util.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

// Username: rajaram@theecsinc.com
// Password: Theecs!nc

@RestController
public class TestFetchShipment {
    @RequestMapping("/")
    public String index() {
        return "Greetings from Mark!";
    }
    
    private static final ObjectMapper MAPPER = new ObjectMapper();
    
    public static JsonNode convertToJsonNode(String data) throws Exception {
		try {
			return MAPPER.readTree(data);
		} catch (IOException e) {
			throw new Exception("Failed to convert to JsonNode");
		}
	}
    
    private HttpHeaders createHttpHeaders(String client_id, String client_secret) {
    	String authInfo = client_id + ":" + client_secret;
    	String encodedAuth = Base64.getEncoder().encodeToString(authInfo.getBytes());
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	headers.add("Authorization", "Basic " + encodedAuth);
    	return headers;
    }
    
    @RequestMapping(value = "/getToken")
    public String getToken() {
    	String url = "https://api.flipkart.net/oauth-service/oauth/token?grant_type=client_credentials&scope=Seller_Api";
    	RestTemplate restTemplate = new RestTemplate();
    	HttpHeaders headers = createHttpHeaders("11a75377360271863713b96954133820a9bb9", "1b8ec68d90d5d997e83a4d3902c46819b");
    	System.out.println(headers);
    	HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
    	ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
    	String token = "";
    	try {
    		JsonNode myJsonNode = convertToJsonNode(response.getBody());
    		token += String.valueOf(myJsonNode.get("access_token"));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return token.replace("\"", "");
    }
    
    @RequestMapping(value = "/auth")
    public String getOauth() {
    	String uri = "https://api.flipkart.net/oauth-service/oauth/authorize?client_id=36a17224a4b418012931aa583b37540048a9&redirect_uri=http://www.google.com/&response_type=code&scope=Seller_Api&state=1234";
    	RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        System.out.println(result);
    	return result;
    }
    
    private HttpHeaders createBearerHeaders(String token) {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	headers.add("Authorization", "Bearer " + token);
    	return headers;
    }
    
    @RequestMapping(value = "/fetchShipment", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public String fetchShipment() {
    	String uri = "https://api.flipkart.net/sellers/v3/shipments/filter/";
    	RestTemplate restTemplate = new RestTemplate();
    	HttpHeaders headers = createBearerHeaders(getToken());
    	System.out.println(headers);
    	HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
    	ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
        String result = response.getBody();
//        System.out.println(result);
    	return result;
    }
}

