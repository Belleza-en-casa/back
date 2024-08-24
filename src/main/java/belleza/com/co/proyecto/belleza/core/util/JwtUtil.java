package belleza.com.co.proyecto.belleza.core.util;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;


@Component
public class JwtUtil {
    private static final Dotenv dotenv = Dotenv.load();
    private static final String SECRET_KEY = dotenv.get("SECRET_KEY");
    private static Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);

    public String create(String username, Map<String, Object> additionalClaims) {
        com.auth0.jwt.JWTCreator.Builder builder = JWT.create()
                .withSubject(username)
                .withIssuer("Belleza-En-Casa")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(15)));

        // Agregar claims adicionales
        if (additionalClaims != null) {
            for (Map.Entry<String, Object> entry : additionalClaims.entrySet()) {
                builder.withClaim(entry.getKey(), entry.getValue().toString());
            }
        }

        return builder.sign(ALGORITHM);
    }

    public boolean isValid(String jwt) {
        try {
            JWT.require(ALGORITHM)
                    .build()
                    .verify(jwt);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public String getUsername(String jwt) {
        return JWT.require(ALGORITHM)
                .build()
                .verify(jwt)
                .getSubject();
    }
}