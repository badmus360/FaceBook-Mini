package service;

import entity.UserEntity;
import model.response.ApiResponse;
import model.response.OperationStatus;
import model.response.UserRest;

import java.util.List;

public interface UserService {
    ApiResponse<UserRest> signUp(UserDto user);
    ApiResponse<UserRest> login(String contact, String password);
    UserEntity getUserByContact(String contact);
    UserEntity getUserById(String id);
    ApiResponse<UserRest> getUserByUserId(String userId);
    ApiResponse<UserRest> updateUser(String userId, UserDto user);
    ApiResponse<List<UserRest>> getUsers(int page, int limit);
    ApiResponse<OperationStatus> deleteUser(String userId);
}