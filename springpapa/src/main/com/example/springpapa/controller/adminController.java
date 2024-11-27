package com.example.springpapa.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.springpapa.common.Result;
import com.example.springpapa.entity.admin;
import com.example.springpapa.entity.admin;
import com.example.springpapa.mapper.adminMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController 
@RequestMapping("/admin")
public class adminController {
    @Resource
    adminMapper adminmapper;
    @PostMapping("/Login")
    public Result<?> loginserver(@RequestBody admin user){
        admin res = adminmapper.selectOne(Wrappers.<admin>lambdaQuery().eq(admin::getNickName,user.getNickName()).eq(admin::getPassword,user.getPassword()));
        if(res==null){
            return Result.error("-1","用户名或密码错误！");

        }else{
            return Result.success(res);
        }
    }
    public Result<?> update(@RequestBody admin ad){
        adminmapper.updateById(ad);
        return Result.success();
    }
    
}
