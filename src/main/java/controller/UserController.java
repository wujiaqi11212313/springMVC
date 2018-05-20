package controller;

import dao.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private static List<User> userList;

    public UserController() {
        super();
        userList = new ArrayList();
    }

    private static final Log logger = LogFactory.getLog(UserController.class);

    @RequestMapping(value = "/register")
    public String registerForm() {
        logger.info("register被调用");
        return "registerForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam("loginname") String loginname,
                           @RequestParam("password") String password,
                           @RequestParam("username") String username) {
        logger.info("register POST方法被调用");
        User user = new User();
        user.setLoginname(loginname);
        user.setPassword(password);
        user.setUsername(username);
        userList.add(user);
        return "loginForm";
    }

    @RequestMapping("/login")
    public String login(@RequestParam("loginname") String loginname,
                        @RequestParam("password") String password,
                        Model model) {
        logger.info("登录名:" + loginname + "密码:" + password);
        for (User user : userList) {
            if (user.getLoginname().equals(loginname) && user.getPassword().equals(password)) {
                model.addAttribute("user", user);
                return "welcome";
            }
        }
        return "loginForm";
    }

    /**
     * 上传文件
     */
    @RequestMapping("/up")
    public String up(){
        return "uploadForm";
    }
    @RequestMapping("/upload")
    public String upload(HttpServletRequest request,
                         @RequestParam("description") String description,
                         @RequestParam("file") MultipartFile file) throws IOException {
        System.out.println(description);
        if (!file.isEmpty()) {
            //上传文件路径
            String path = request.getServletContext().getRealPath("/images");
            logger.info("path"+path);
            //上传文件名
            String filename = file.getOriginalFilename();
            logger.info("filename"+filename);
            File filepath = new File(path, filename);
            logger.info("filepath"+filepath);
            //判断路径是否存在,如果不存在就创建一个
            if (!filepath.getParentFile().exists()){
                filepath.getParentFile().mkdirs();
            }
            //将上传文件保存到一个目标文件当中
            file.transferTo(new File(path+File.separator+filename));
            return "success";
        }else {
            return "error";
        }

    }


}





















