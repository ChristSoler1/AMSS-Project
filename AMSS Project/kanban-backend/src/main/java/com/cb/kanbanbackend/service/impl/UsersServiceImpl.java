package com.cb.kanbanbackend.service.impl;

import com.cb.kanbanbackend.dto.req.LoginReqDto;
import com.cb.kanbanbackend.dto.res.LoginResDto;
import com.cb.kanbanbackend.entity.UsersEntity;
import com.cb.kanbanbackend.exception.CommonException;
import com.cb.kanbanbackend.exception.NoDataFoundException;
import com.cb.kanbanbackend.repo.UsersRepo;
import com.cb.kanbanbackend.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//Implementiert UserServicesImpl

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersRepo usersRepo;

    @Override
    public void CreateUser(UsersEntity entity) throws Exception {
        if (usersRepo.countByEmail(entity.getEmail()) != 0) {
            throw new CommonException("User exist");
        }
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(entity.getPassword().getBytes());
        StringBuilder hexString = new StringBuilder();

        for (byte b : messageDigest) {
            hexString.append(String.format("%02x", b));
        }
        entity.setPassword(String.valueOf(hexString));
        usersRepo.save(entity);
    }

    @Override
    public LoginResDto LoginUser(LoginReqDto entity) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(entity.getPassword().getBytes());
        StringBuilder hexString = new StringBuilder();

        for (byte b : messageDigest) {
            hexString.append(String.format("%02x", b));
        }
        entity.setPassword(String.valueOf(hexString));
        UsersEntity e = usersRepo.getUsersEntityByPasswordAndEmail(entity.getPassword(), entity.getEmail()).orElse(new UsersEntity());
        System.out.println(e);
        if (null == e.getUserId()) {
            throw new NoDataFoundException("User not found");
        }
        return new LoginResDto(e.getUserId(), e.getUsername(), e.getEmail(), e.getCalenderUrl());
    }

    @Override
    public Integer Update(UsersEntity entity) throws NoSuchAlgorithmException {
        UsersEntity e = usersRepo.findById(entity.getUserId()).orElse(new UsersEntity());

        if (entity.getPassword() != null && entity.getPassword() != "") {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(entity.getPassword().getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : messageDigest) {
                hexString.append(String.format("%02x", b));
            }
            e.setPassword(String.valueOf(hexString));
        }
        if (entity.getPhone() != null && entity.getPhone() != "") {
            e.setPhone(entity.getPhone());
        }

        if (entity.getCalenderUrl() == "") {
            e.setCalenderUrl(null);
        }else{
            e.setCalenderUrl(entity.getCalenderUrl());
        }

        System.out.println(e);
        return usersRepo.save(e).getUserId();
    }

    @Override
    public UsersEntity getUserByUserID(Integer id) {
        return usersRepo.findById(id).orElse(new UsersEntity());
    }
}
