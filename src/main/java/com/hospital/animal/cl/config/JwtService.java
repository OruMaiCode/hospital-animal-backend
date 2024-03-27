package com.hospital.animal.cl.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecretKeyAlgorithm;
import io.jsonwebtoken.security.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import static javax.crypto.Cipher.SECRET_KEY;

@Service
public class JwtService {

public static final String SECRET_KEY="oYGQJ4peKXEBCipPMlYOFepZxL2MC7f1gKGwxHt0V09FVrvEgVtMYJdIGs8GmSlITCJRGYS6AHYOvOwNbFVnuj8XCXWqYJLXSIfAvCrAiD7g4+b0qxJNJGN76zH/6YnxmCdU1nXXhQNmFJedvDbg/LXTD0+FY8C/fBflazkYtZKV8qtkVuflHJJgIAownfdTu2MFuw7W3w37oJnYbW3teYKOd6WcWtT01kRoc5ZY3aJt4eXfp9EcicDymzbaAoIUGbIEQwzitTSd2/aDwVd616WB7t3S5FtdAOIy2gYataLzdcbKD4MPDMOQG/FFUEIAexm8LN5OgQoF/P64omgr0bXz1L6MZcKuNrsQtQrsyNJZl5szMjC/Eh8XjhBKewpWGh1VzXp4SEcn1hpQOt2Tzhe+r1yEv3q1Mn94PrhW5F8bzGBaqtDs+SS8K+yYhQzcnfPDZpEqFB5sAUVxmq833o3g9LeWMC2WCJBWmWipCQoIBOGOk9MwILX7a+sYcnY+EL6HMXpKkHQz/VqAH9tiZZsKwN5ZFczg+lIiGLfCIBJNCGbT/1FnI99aS2WbZ6ldOhNieNX2FAtRrlT5W7C5rtOs/KUCNclqyvLsFnDgQl7a809n0HVKRuD4a/h4rjt6NLtaZrnI07gChvdpbyrD8hLx1pCI042gHi1drQaWiuyfZl/fp2vxqiWsO5/jAl9JFCE2lHrJdh2lUekBoyfGfGMuGfjwWWx43HOhsKEHmoKqlyEeYTeZLoAFX0Ykblw4HW0dwwZEU7uvBB2ZOEl2lgMP3uLoEzR6vSEJ8PJHTRTmvcOHQ6uslllUebE4CzOS3vLjxJKkWTwGmVCWkbvzCehEx2+g1ZG14pP60eAZzI2RNXcZs9VD/+0ETcGNA46bToQfpVT++aTgbdFoc4sznfX7jNFgusIKdEA+utdRfJVtzYtEzj0fbtw6skIu9dRFjoSTMZHNi8DX+MMQjhgi+XSNJEfOEvSkHV+fvs/CbJWF7aJiTALjaIP++kr8E5450a4TJHcjt0i3xWcXle7tHD3EOtukzPQAw8+jPRTvDfKvh0VssKiyzPtZw0tRbZeU5hIUky0zd7SUy4r/E64RNjz9wXUPoB9VyIHIucdDGsLjLyrG08FR8fyM/3NmfUOvWyMYWHyOQVCwPu5gksYyYLpQ8A1TluLEFIJA8qEo6nopAinrSNuhX3g+2rGPMhe7+0CHjGw07w8UahSPIjJ3nFHkUk3ruzxPD3ErvuODggCA9lQqSADB0qZS5730Arx5zMf7m6SufARhn3h+8iXf/lHg4aBudWnCkEyM/UEVyulWP184GHwPAhDIKOCXRYYsNIqSpCWgCqNJkYTJFZv1jQ==";

    public String extractUsername(String token){
        return extractClaim(token,Claims::getSubject);
    }
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){
        return Jwts.builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+(1000*60*24)))
                .signWith(getSignInKey(),Jwts.SIG.HS256).compact();
    }
    public boolean isTokenValid(String token,UserDetails userDetails){
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
    private boolean isTokenExpired(String token){
         return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }
    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims= extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token){
          return Jwts
                .parser()
                .verifyWith(this.getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }
    private SecretKey getSignInKey(){
        return new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
    }

}
