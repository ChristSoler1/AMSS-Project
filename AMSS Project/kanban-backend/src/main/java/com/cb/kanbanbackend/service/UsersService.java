package com.cb.kanbanbackend.service;

import com.cb.kanbanbackend.dto.req.LoginReqDto;
import com.cb.kanbanbackend.dto.res.LoginResDto;
import com.cb.kanbanbackend.entity.UsersEntity;

import java.security.NoSuchAlgorithmException;

//User Interface
public interface UsersService {
    void CreateUser(UsersEntity entity) throws Exception;

    LoginResDto LoginUser(LoginReqDto entity) throws Exception;

    Integer Update(UsersEntity entity) throws NoSuchAlgorithmException;

    UsersEntity getUserByUserID(Integer id);
}
