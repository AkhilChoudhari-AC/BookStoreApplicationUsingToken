package com.example.bookstoreapp.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.Verification;
import org.springframework.stereotype.Component;

@Component
public class TokenUtil {
    private static final String TOKEN = "CODE";
    //creating token
    public String createToken(int id) {
        //to set algorithm
        Algorithm algorithm = Algorithm.HMAC256(TOKEN);
        return JWT.create().withClaim("id", id).sign(algorithm);
    }

    public int decodeToken(String token) throws SignatureVerificationException {
        //for verification algorithm
        Verification verification = JWT.require(Algorithm.HMAC256(TOKEN));
        JWTVerifier jwtVerifier = verification.build();
        // to decode token
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        Claim claim = decodedJWT.getClaim("id");
        return claim.asInt();
    }
}

