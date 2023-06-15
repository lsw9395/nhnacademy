package com.nhnacademy.notice_board.Controller.user;

import com.nhnacademy.notice_board.Controller.Command;
import com.nhnacademy.notice_board.objects.User;
import com.nhnacademy.notice_board.objects.UserRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class UserUpdateFormController implements Command {
    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String UPLOAD_DIR = "/src/main/upload";
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userList");
        String fileN = null;
        for(Part part : req.getParts()){
            String contentDisposition = part.getHeader(CONTENT_DISPOSITION);

            if (contentDisposition.contains("filename=")) {
                String fileName = extractFileName(contentDisposition);
                log.error("fileName::::{}",fileName);
                fileN = fileName;
                if (part.getSize() > 0) {
                    log.error(UPLOAD_DIR + File.separator + fileName);
                    String filePath = UPLOAD_DIR + File.separator + fileName;
                    String realPath = req.getServletContext().getRealPath(filePath);
                    realPath = realPath.replace("target/notice_board-1.0-SNAPSHOT/","");// 맥용
                    //realPath = realPath.replace("target\\notice_board-1.0-SNAPSHOT\\",""); //윈도우용
                    part.write(realPath);
                    part.delete();
                }
            } else {
                String formValue = req.getParameter(part.getName());
                log.error("{}={}", part.getName(),formValue);
            }
        }

        String id = req.getParameter("id2");
        log.error("id:{}",req.getParameter("id2"));
        if(Objects.isNull(id)){
            throw new RuntimeException("아이디를 입력해주세요.");
        }

        String password = req.getParameter("password");
        log.error("password:{}",req.getParameter("password"));
        if(Objects.isNull(password)){
            throw  new RuntimeException("비밀번호를 입력해주세요.");
        }
        String name = req.getParameter("name");
        if(Objects.isNull(name)){
            throw  new RuntimeException("이름을 입력해주세요.");
        }
        User user = userRepository.getUser(id);
        if(Objects.isNull(user)){
            throw new RuntimeException("해당 유저가 없습니다.");
        }
        HttpSession session = req.getSession(true);
        if(session.getAttribute("id")==null){
            return "redirect:/loginForm.do";
        }
        User newUser = new User(id,password,name,fileN);
        userRepository.modify(newUser);
        return "redirect:/userlist.do";
    }

    private String extractFileName(String contentDisposition) {
        log.error("contentDisposition:{}",contentDisposition);
        for (String token : contentDisposition.split(";")) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=")+2, token.length()-1);
            }
        }
        return null;
    }
}