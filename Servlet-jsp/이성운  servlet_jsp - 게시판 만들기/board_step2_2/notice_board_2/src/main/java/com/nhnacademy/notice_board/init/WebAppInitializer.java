package com.nhnacademy.notice_board.init;

import com.nhnacademy.notice_board.objects.*;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

public class WebAppInitializer implements ServletContainerInitializer {

    //private UserRepository userRepository = new JsonUserRepositoty();
    //private PostRepository postRepository = new JsonPostRepository();
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        UserRepository userRepository = new JsonUserRepositoty();
        PostRepository postRepository = new JsonPostRepository();
        //User admin = new User("admin","12345","Administrator",null);
        //User test = new User("test","12345","test","images1.jpeg");
        //User test2 = new User("test2","12345","test2","images2.jpeg");
        //userRepository.add(admin);
        //userRepository.add(test);
        //userRepository.add(test2);
        servletContext.setAttribute("userList",userRepository);
        servletContext.setAttribute("postList",postRepository);
        servletContext.setInitParameter("counterFilterName","counter.dat");
        servletContext.setInitParameter("login_counter","0");
    }
}
