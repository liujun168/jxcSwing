package com.swing.jxc.demo.swing;

import com.swing.jxc.demo.swing.gui.LoginSwingExample;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SwingApplication {

    public static void main(String[] args) {
//        SpringApplication.run(SwingApplication.class, args);
        LoginSwingExample login = new LoginSwingExample();
        login.createView();
    }

}
