package com.snhu.sslserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.util.HexFormat; // This lets me convert the hash to a hex string.

@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}

}
//FIXME: Add route to enable check sum return of static data example:  String data = "Hello World Check Sum!";
@RestController
class ServerController{    
    @RequestMapping("/hash")
    public String myHash() throws NoSuchAlgorithmException{
    	String data = "Hello Ronny Valtonen!";
    	String algorithm = "SHA-256";
    	String hashHex;
    	
    	try {
    		// Create a SHA-256 Instance
    		MessageDigest md = MessageDigest.getInstance(algorithm);
        	byte[] hashBytes = md.digest(data.getBytes(StandardCharsets.UTF_8));
        	hashHex = HexFormat.of().formatHex(hashBytes);
    	} catch (NoSuchAlgorithmException e) {
    		return "<html><body>" + "<h2> Error Hashing </h2>" + "<p> Error creating checksum </p>" + "<p> Exception: " + e.getMessage() + "</p>" + "</body></html>";
    	}
    	
        return "<html><body>" + "<p>data:" + data + "</p>" + "<p>Name of Cipher Algorithm Used : " + algorithm + " Checksum Value: " + hashHex + "</p>" + "</body></html>";
 
}
}