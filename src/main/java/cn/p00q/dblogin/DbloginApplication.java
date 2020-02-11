package cn.p00q.dblogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author danbai
 */
@MapperScan(basePackages = "cn.p00q.dblogin.mapper")
@SpringBootApplication
public class DbloginApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbloginApplication.class, args);
    }

}
