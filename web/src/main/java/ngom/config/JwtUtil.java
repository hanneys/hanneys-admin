package ngom.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.ServletException;
import java.util.Date;

/**
 * Created by hanaijun on 2018/6/3
 */
public class JwtUtil {

    final static String base64EncodedSecretKey = "base64EncodedSecretKey";//私钥
    final static long TOKEN_EXP = 1000 * 60*30;//过期时间,测试使用五分种

    /**
     *
     * @param userName
     * @return
     */
    public static String getToken(String userName,String id) {
        return Jwts.builder()
                .setId(id)
                .setSubject(userName)
                .claim("roles", "sys")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXP)) /*过期时间*/
                .signWith(SignatureAlgorithm.HS256, base64EncodedSecretKey)
                .compact();
    }




    /**
     * 检查token,只要不正确就会抛出异常
     * @param token
     * @throws ServletException
     */
    public static void checkToken(String token) throws ServletException {
        try {
            final Claims claims = Jwts.parser().setSigningKey(base64EncodedSecretKey).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e1) {
            throw new ServletException("token expired");
        } catch (Exception e) {
            throw new ServletException("other token exception");
        }
    }


    /**
     * jwt解密
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception{
        return Jwts.parser()
                .setSigningKey(base64EncodedSecretKey)
                .parseClaimsJws(jwt).getBody();
    }



}
