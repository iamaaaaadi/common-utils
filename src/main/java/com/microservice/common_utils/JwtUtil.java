package com.microservice.common_utils;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public  class JwtUtil {

    public static final String SECRET = "357638792F423F4428472B4B6250655368566D597133743677397A2443264629";

    // Validates the token and retrieves claims if valid
    public static  Claims validateTokenAndGetClaims(String token) {
    	
    	String cleanedToken = cleanToken(token);
    	
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(cleanedToken)
                    .getBody();
            
            // Check if the token is expired
            if (isTokenExpired(claims)) {
                throw new RuntimeException("Token has expired");
            }

            return claims;

        } catch (Exception e) {
            // Handle invalid token or parsing errors here
            throw new RuntimeException("Invalid token", e);
        }
    }

    // Extracts user ID from the JWT token
    public static  Long validateTokenAndGetUserId(String token) {
        Claims claims = validateTokenAndGetClaims(token);
        return Long.parseLong(claims.getSubject());
    }

    // Returns the signing key
    private static Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
   // Checks if the token is expired
    private static Boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }
    
    private static String cleanToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7); // Remove "Bearer " (7 characters)
        }
        return token; // Return the token as is if "Bearer " is not present
    }

}
