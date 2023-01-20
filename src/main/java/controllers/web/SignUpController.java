package controllers.web;

import lombok.AllArgsConstructor;
import model.request.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import service.impl.UserServiceImpl;
import shared.dto.UserDto;

@AllArgsConstructor
@Controller
public class SignUpController {
    private UserServiceImpl userService;
    private ModelMapper modelMapper;

    @GetMapping("/signup")
    public String signUp(Model model) {
        User user = new User();
        model.addAttribute("userObj", user);
        return "signup";
    }

    @PostMapping("/signup")
    public String register(@ModelAttribute("userObj") User user) {
        String dob = user.getDay() + "-" + user.getMonth() + "-" + user.getYear();
        UserDto userDto = modelMapper.map(user, UserDto.class);
        userDto.setDob(dob);
        var apiResponse = userService.signUp(userDto);
        System.out.println(apiResponse.getMessage());
        if (apiResponse.isSuccess()) {
            return "redirect:/login";
        } else {
            var userRest = apiResponse.getData();
            return "/signup";
        }
    }
}

