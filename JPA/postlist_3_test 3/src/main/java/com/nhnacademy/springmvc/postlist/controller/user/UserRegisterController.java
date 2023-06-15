package com.nhnacademy.springmvc.postlist.controller.user;

import com.nhnacademy.springmvc.postlist.domain.User;
import com.nhnacademy.springmvc.postlist.domain.UserRegisterRequest;
import com.nhnacademy.springmvc.postlist.exception.UserAlreadyExists;
import com.nhnacademy.springmvc.postlist.service.DbUserService;
import com.nhnacademy.springmvc.postlist.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserRegisterController {
    private final DbUserService userService;
    private final String UPLOAD_DIR = "src/main/webapp/resources";

    @GetMapping("/user_register")
    public String registerForm(Model model){
        log.info("message:{}", model.getAttribute("message"));
        model.addAttribute("userRegisterRequest",new UserRegisterRequest());
        return "user/userregister";
    }

    @PostMapping("/user_register")
    public String getUser(@Validated @ModelAttribute UserRegisterRequest userRegisterRequest, BindingResult bindingResult,
                          @RequestParam(value = "file",required = false) MultipartFile file,HttpServletRequest req) throws IOException, UserAlreadyExists {
        if(bindingResult.hasErrors()){
            log.info("binding error");
            return "user/userregister";
        }
        User user = userService.getUser(userRegisterRequest.getId());
        if(Objects.nonNull(user)){
            throw new UserAlreadyExists(userRegisterRequest.getId());
        }
        String filename = null;
        if(!file.isEmpty()){
            String filePath = UPLOAD_DIR + File.separator + file.getOriginalFilename();
            String realPath = req.getServletContext().getRealPath(filePath);
            log.info(realPath);
            realPath = realPath.replace("target/postlist-1.0-SNAPSHOT/","");//맥용
            //realPath = realPath.replace("target\\postlist-1.0-SNAPSHOT\\","");//윈도우용
            log.info(realPath);
            if(realPath.contains("/src/main/webapp/src/main/webapp"))
            {
                realPath =realPath.replace("/src/main/webapp/src/main/webapp","/src/main/webapp");
            }
            file.transferTo(new File(realPath));
            filename=file.getOriginalFilename();
        }
        userService.add(new User(userRegisterRequest.getId(),userRegisterRequest.getPassword(),userRegisterRequest.getName(),filename));
        return "redirect:/";
    }
}
