package pers.robin.revolver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"pers.robin.revolver.dao"})
public class RevolverApplication {

    public static void main(String[] args) {
        SpringApplication.run(RevolverApplication.class, args);
    }
}
