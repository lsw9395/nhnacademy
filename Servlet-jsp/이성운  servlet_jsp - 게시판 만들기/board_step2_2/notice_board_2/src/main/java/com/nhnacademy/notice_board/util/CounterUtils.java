package com.nhnacademy.notice_board.util;

import javax.servlet.ServletContext;
import java.util.Optional;

public final class CounterUtils {
    private CounterUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static void increaseCounter(ServletContext servletContext){
        Long counter = Optional.ofNullable((Long)servletContext.getAttribute("counter")).orElse(0l);
        counter = counter+1;
        servletContext.setAttribute("counter",counter);
    }
    public static void loginIncreaseCounter(ServletContext servletContext){
        Long counter = Optional.ofNullable((Long)servletContext.getAttribute("login_counter")).orElse(0l);
        counter = counter+1;
        servletContext.setAttribute("login_counter",counter);
    }
    public static void loginDecreaseCounter(ServletContext servletContext){
        Long counter = Optional.ofNullable((Long)servletContext.getAttribute("login_counter")).orElse(0l);
        counter = counter-1;
        servletContext.setAttribute("login_counter",counter);
    }

}
