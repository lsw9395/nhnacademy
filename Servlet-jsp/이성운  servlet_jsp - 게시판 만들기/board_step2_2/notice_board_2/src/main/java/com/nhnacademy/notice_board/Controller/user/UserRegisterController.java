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
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserRegisterController implements Command {
    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String UPLOAD_DIR = "/src/main/upload";
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userList");
        List<String> userdata = new ArrayList<>();
        User user = new User();
        for(Part part : req.getParts()){
            String contentDisposition = part.getHeader(CONTENT_DISPOSITION);

            if (contentDisposition.contains("filename=")) {
                String fileName = extractFileName(contentDisposition);
                log.error("fileName::::{}",fileName);
                user.setProfileFileName(fileName);
                if (part.getSize() > 0) {
                    log.error(UPLOAD_DIR + File.separator + fileName);
                    String filePath = UPLOAD_DIR + File.separator + fileName;
                    String realPath = req.getServletContext().getRealPath(filePath);
                    realPath = realPath.replace("target/notice_board-1.0-SNAPSHOT/","");//맥용
                    //realPath = realPath.replace("target\\notice_board-1.0-SNAPSHOT\\","");//윈도우용
                    part.write(realPath);
                    part.delete();
                }
            } else {
                String formValue = req.getParameter(part.getName());
                log.error("{}={}", part.getName(),formValue);
                if(part.getName().contains("filename=")){}
                else {
                    userdata.add(formValue);
                }
            }
        }
        for(int i = 0;i< userdata.size();i++){
            if(i == 0){
                user.setId(userdata.get(i));
            }else if(i==1){
                user.setPassword(userdata.get(i));
            } else {
                user.setName(userdata.get(i));
            }
        }
        userRepository.add(user);
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
