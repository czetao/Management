package czt_ssm_utils;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderUtils {

    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static void enco(String pwd){
        String encode = bCryptPasswordEncoder.encode(pwd);

        String hashpw = BCrypt.hashpw(pwd, BCrypt.gensalt());
        System.out.println(encode);
        System.out.println(hashpw);
        String hashpw2 = BCrypt.hashpw(pwd, BCrypt.gensalt());
        System.out.println(hashpw2);

    }

    public static void main(String[] args) {
        enco("123");
    }


}
