package com.nhnacademy.notice_board.servlet;

import com.nhnacademy.notice_board.Controller.*;
import com.nhnacademy.notice_board.Controller.post.*;
import com.nhnacademy.notice_board.Controller.user.*;
import com.nhnacademy.notice_board.util.CookieUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@MultipartConfig(
        maxFileSize         = 1024 * 1024 * 10, // 10 MB
        maxRequestSize      = 1024 * 1024 * 100// 100 MB
)
@Slf4j
@WebServlet(name = "frontServlet", urlPatterns = "*.do")
public class FrontServlet extends HttpServlet {
    private static final String REDIRECT_PREFIX="redirect";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 공통 처리 - 응답 content-type, character encoding 지정.
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        if(!Objects.isNull(CookieUtils.getCookie(req,"locale"))){
            String locale = CookieUtils.getCookie(req,"locale").getValue();
            req.setAttribute("locale",locale);
        }
        try {
            Command command = resolveCommand(req.getServletPath(), req.getMethod());
            log.info(req.getServletPath());
            log.info(req.getMethod());
            String view = command.execute(req, resp);
            if (view.startsWith(REDIRECT_PREFIX)) {
                log.error("redirect-url : {}", view.substring(REDIRECT_PREFIX.length() + 1));
                resp.sendRedirect(view.substring(REDIRECT_PREFIX.length() + 1));
            } else {
                RequestDispatcher rd = req.getRequestDispatcher(view);
                rd.include(req, resp);
            }
        } catch (IllegalStateException ignore) {

        } catch (Exception ex) {
            log.error("Error:{}", ex.getMessage(), ex);


        }
    }
    private Command resolveCommand(String servletPath, String method){
        Command command = null;
        if("/loginForm.do".equals(servletPath) && "GET".equalsIgnoreCase(method)){
            command = new LoginFormjspController();
        }
        if("/login.do".equals(servletPath) && "POST".equalsIgnoreCase(method)){
            command = new LoginController();
        }
        if("/login.do".equals(servletPath) && "GET".equalsIgnoreCase(method)){
            command = new LoginFormController();
        }
        if("/logout.do".equals(servletPath)&&"POST".equalsIgnoreCase(method)){
            command = new LogoutController();
        }
        if("/userlist.do".equals(servletPath)&&"GET".equalsIgnoreCase(method)){
            command = new UserListController();
        }
        if("/postlist.do".equals(servletPath) && "GET".equalsIgnoreCase(method)){
            command = new PostListController();
        }
        if("/register.do".equals(servletPath) && "POST".equalsIgnoreCase(method)){
            command = new PostRegisterController();
        }
        if("/register.do".equals(servletPath) && "GET".equalsIgnoreCase(method)){
            command = new PostRegisterFormController();
        }
        if("/postview.do".equals(servletPath) && "GET".equalsIgnoreCase(method)){
            command = new PostViewController();
        }
        if("/postupdate.do".equals(servletPath)&&"GET".equalsIgnoreCase(method)){
            command = new PostUpdateController();
        }
        if("/postupdate.do".equals(servletPath)&&"POST".equalsIgnoreCase(method)){
            command = new PostUpdateFormController();
        }
        if("/postdelete.do".equals(servletPath)&&"GET".equalsIgnoreCase(method)){
            command = new PostDeleteController();
        }
        if("/userregister.do".equals(servletPath) && "POST".equalsIgnoreCase(method)){
            command = new UserRegisterController();
        }
        if("/userregister.do".equals(servletPath) && "GET".equalsIgnoreCase(method)){
            command = new UserRegisterFormController();
        }
        if("/fileimage.do".equals(servletPath) && "GET".equalsIgnoreCase(method)){
            command = new fileimageController();
        }
        if("/userview.do".equals(servletPath)&&"GET".equalsIgnoreCase(method)){
            command = new UserViewController();
        }
        if("/userupdate.do".equals(servletPath)&&"GET".equalsIgnoreCase(method)){
            command = new UserUpdateController();
        }
        if("/userupdate.do".equals(servletPath)&&"POST".equalsIgnoreCase(method)){
            command = new UserUpdateFormController();
        }
        if("/userdelete.do".equals(servletPath)&&"GET".equalsIgnoreCase(method)){
            command = new UserDeleteController();
        }
        if("/change_lang.do".equals(servletPath)&&"GET".equalsIgnoreCase(method)){
            command = new ChangeLangController();
        }
        return command;
    }

}