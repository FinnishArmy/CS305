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
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

}

@RestController
class ServerController{    
    @RequestMapping("/hash")
    public String myHash() throws NoSuchAlgorithmException{
    	String data = "Hello Ronny Valtonen!";
    	String algorithm = "SHA-256";
    	
    	// Create a SHA-256 instance, generate the hash for the string and convert to hex string
    	MessageDigest md = MessageDigest.getInstance(algorithm);
    	byte[] hashBytes = md.digest(data.getBytes(StandardCharsets.UTF_8));
    	String hashHex = HexFormat.of().formatHex(hashBytes);
    	
        return "<html><body>" + "<p>data:" + data + "</p>" + "<p>Name of Cipher Algorithm Used : " + algorithm + "Checksum Value:" + hashHex + "</p>" + "</body></html>";
    }
}
