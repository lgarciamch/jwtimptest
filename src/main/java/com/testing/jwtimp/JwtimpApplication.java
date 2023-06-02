package com.testing.jwtimp;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.crypto.SecretKey;
import java.security.Key;

import static io.jsonwebtoken.security.Keys.secretKeyFor;

@SpringBootApplication(scanBasePackages = "com.testing.jwtimp")
public class JwtimpApplication {

	public static void main(String[] args) {
		SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		String secretString = Encoders.BASE64.encode(key.getEncoded());
		System.out.println("Secret key: " + secretString);
		SpringApplication.run(JwtimpApplication.class, args);
	}

}
