package controllers.web;

import lombok.AllArgsConstructor;
import model.request.Login;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import service.impl.UserServiceImpl;

@Controller
@AllArgsConstructor
public class LoginController {

    private UserServiceImpl userService;

    @GetMapping("/login")
    public String index(Model model) {
        Login login = new Login();
        model.addAttribute("loginObj", login);
        return "login";
    }

    @PostMapping("/login")
    public String loginForm(@ModelAttribute("loginObj") Login login) {

        var apiResponse = userService.login(login.getContact(), login.getPassword());
        if (apiResponse.isSuccess()) {
            var userRest = apiResponse.getData();
            return "redirect:/homepage/" + userRest.getUserId();
        } else {
            return "login";
        }
    }

}
