/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.grci;

import java.util.Base64;

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {

        System.out.println(new App().getGreeting());
        String param = "key=3fb0ba30855bcc1ff205f3221596a8f906a9f2b9,secret=3e170fb7f21ea52ac4bf6536a929cb35," +
                "context_type=demand,context_id=9772";
        System.out.println(new String(Base64.getEncoder().encode(param.getBytes())));

        System.out.println(new String(Base64.getEncoder().encode("3fb0ba30855bcc1ff205f3221596a8f906a9f2b9".getBytes())));
        System.out.println(new String(Base64.getEncoder().encode("3e170fb7f21ea52ac4bf6536a929cb35".getBytes())));
    }
}
