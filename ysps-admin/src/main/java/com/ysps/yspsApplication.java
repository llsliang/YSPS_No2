package com.ysps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author ysps
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class yspsApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(yspsApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                "    \n" +
                " __     __ _____  _____    _____    \n" +
                " \\ \\   / // ____||  __ \\  / ____|   \n" +
                "  \\ \\_/ /| (___  | |__) || (___     \n" +
                "   \\   /  \\___ \\ |  ___/  \\___ \\    \n" +
                "    | |   ____) || |      ____) |   \n" +
                "    |_|  |_____/ |_|     |_____/    \n" +
                "                                    \n" +
                "    ");
    }
}
