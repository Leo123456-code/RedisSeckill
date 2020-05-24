package com.phone.book;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * ClassName: PhoneBookApplication
 * Description: TODO
 * Author: Leo
 * Date: 2020/5/23-14:49
 * email 1437665365@qq.com
 */
@SpringBootApplication
@MapperScan(basePackages = "com.phone.book.dao")
@EnableScheduling
public class PhoneBookApplication {

    public static void main(String[] args) {

        SpringApplication.run(PhoneBookApplication.class, args);
    }
}
