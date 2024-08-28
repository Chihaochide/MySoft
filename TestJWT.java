
import com.heima.utils.common.BCrypt;
import com.heima.utils.common.JwtUtils;
import com.heima.utils.common.Payload;
import com.heima.utils.common.RsaUtils;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestJWT {

    private static final String publicKeyPath = "E:\\MyProjectWorkspace\\rsa-key\\rsa-key.pub";
    private static final String privateKeyPath = "E:\\MyProjectWorkspace\\rsa-key\\rsa-key";

    // 生成公私钥
    @Test
    public void testGenericRsa() throws Exception{
        /**
         * 参数一：公钥路径
         * 参数二：私钥路径
         * 参数三：盐（建议随机生成）
         * 参数四：文件长度（字节）
         */
         RsaUtils.generateKey(publicKeyPath,privateKeyPath,"HelloWorld",2048);

    }

    // 读取公钥
    @Test
    public void getPublicKey() throws Exception{
        PublicKey publicKey = RsaUtils.getPublicKey(publicKeyPath);
        System.out.println(publicKey);
        PrivateKey privateKey = RsaUtils.getPrivateKey(privateKeyPath);
        System.out.println(privateKey);
    }

    // 生成Token
    @Test
    public void creatToken() throws Exception{
        Student st = new Student("Ax","man",22);

        PublicKey publicKey = RsaUtils.getPublicKey(publicKeyPath);
        PrivateKey privateKey = RsaUtils.getPrivateKey(privateKeyPath);

        String token = JwtUtils.generateTokenExpireInMinutes(st, privateKey, 5);
        Payload<Student> info = JwtUtils.getInfoFromToken(token, publicKey, Student.class);

        System.out.println(info.getInfo().toString());

    }

    @Test
    public void demo() throws Exception{
        String pwd = "123123";
        String salt = BCrypt.gensalt();
        System.out.println("salt = " + salt);

        pwd = BCrypt.hashpw(pwd,salt);
        System.out.println("pwd = " + pwd);

        System.out.println(BCrypt.checkpw("xuanhe0119", pwd));

    }

    @Test
    public void testTest(){
        List<String> l = new ArrayList<>();
        Collections.addAll(l,"a","b","c");
        System.out.println("String.format(\"%s\",l) = " + String.format("%s", l));
        System.out.println("l = " + l);
    }

}
