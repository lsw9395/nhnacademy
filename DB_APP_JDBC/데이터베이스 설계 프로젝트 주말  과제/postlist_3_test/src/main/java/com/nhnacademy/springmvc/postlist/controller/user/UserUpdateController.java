package com.nhnacademy.springmvc.postlist.controller.user;

import com.nhnacademy.springmvc.postlist.domain.User;
import com.nhnacademy.springmvc.postlist.domain.UserRegisterRequest;
import com.nhnacademy.springmvc.postlist.exception.NotFoundUserId;
import com.nhnacademy.springmvc.postlist.service.DbUserService;
import com.nhnacademy.springmvc.postlist.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserUpdateController {
    private final DbUserService userService;
    private final String UPLOAD_DIR = "src/main/webapp/resources";
    @GetMapping("/user_update")
    public String view(@RequestParam("id") String id, Model model) throws NotFoundUserId {
        User user = userService.getUser(id);
        if(Objects.isNull(user)){
            throw new NotFoundUserId(id);
        }
        model.addAttribute("user",user);
        model.addAttribute("userRegisterRequest",new UserRegisterRequest());
        return "user/userregister";
    }
    @PostMapping("/user_update")
    public String getUser(@Validated @ModelAttribute UserRegisterRequest userRegisterRequest, BindingResult bindingResult,
                          @RequestParam(value = "file",required = false) MultipartFile file, HttpServletRequest req, Model model) throws IOException {
        if(bindingResult.hasErrors()){
            log.info("binding error");
            model.addAttribute("user",userRegisterRequest);
            return "user/userregister";
            //return "redirect:/user_update?id="+userRegisterRequest.getId();
        }
        String filename = userService.getUser(userRegisterRequest.getId()).getProfileFileName();
        if(!file.isEmpty()){
            String filePath = UPLOAD_DIR + File.separator + file.getOriginalFilename();
            String realPath = req.getServletContext().getRealPath(filePath);
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
        userService.modify(new User(userRegisterRequest.getId(),userRegisterRequest.getPassword(),userRegisterRequest.getName(),filename));
        return "redirect:/user_view?id="+userRegisterRequest.getId();
    }
}
