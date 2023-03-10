package controllers.rest;

import lombok.AllArgsConstructor;
import model.request.Login;
import model.request.User;
import model.response.ApiResponse;
import model.response.OperationStatus;
import model.response.UserRest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import service.impl.UserServiceImpl;
import shared.dto.UserDto;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final ModelMapper modelMapper;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ApiResponse<UserRest> signUp(@RequestBody User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userService.signUp(userDto);
    }

    @PostMapping(path = "/login", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ApiResponse<UserRest> login(@RequestBody Login login) {
        return userService.login(login.getContact(), login.getPassword());
    }

    @GetMapping(path = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ApiResponse<UserRest> getUserByUserId(@PathVariable String userId) {
        return userService.getUserByUserId(userId);
    }

    @PutMapping(path = "/{userId}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ApiResponse<UserRest> update(@PathVariable String userId, @RequestBody User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userService.updateUser(userId, userDto);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ApiResponse<List<UserRest>> getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "limit", defaultValue = "5") int limit) {
        return userService.getUsers(page, limit);
    }

    @DeleteMapping(path = "{userId}/delete", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ApiResponse<OperationStatus> deleteUser(@PathVariable String userId) {
        return userService.deleteUser(userId);
    }

}
