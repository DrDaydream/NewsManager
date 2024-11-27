package com.example.springpapa.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springpapa.common.Result;
import com.example.springpapa.entity.userdet;
import com.example.springpapa.mapper.userdetMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/user")
public class userdetController {
    @Resource
    userdetMapper userdetmapper;
    @PostMapping("/add")
    public Result<?> save(@RequestBody userdet user) throws IOException {
        if(user.getPassword()==null){
            user.setPassword("123456");
        }
        if(user.getAvatarUrl()==null){

        }
        if(user.getCountry()==null){
            user.setCountry("中国");
        }
        if(user.getState()==null){
            user.setState(1);
        }
        userdetmapper.insert(user);
        return Result.success();

    }
    @PutMapping
    public Result<?> update(@RequestBody userdet user){
      userdetmapper.updateById(user);
        return Result.success();
    }
    @DeleteMapping("/{userID}")
    public Result<?> delete(@PathVariable Integer userID){
        userdetmapper.deleteById(userID);
        return Result.success();
    }


    @GetMapping("/{userID}")
    public Result<?> getbyID(@PathVariable Integer userID){
        return Result.success(userdetmapper.selectById(userID));
    }
    @GetMapping("/{nickName}")
    public Result<?> getbyName(@PathVariable String nickName){
        return Result.success(userdetmapper.selectById(nickName));
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer currentPage,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
      //分页查询
        LambdaQueryWrapper<userdet> wrapper =  Wrappers.<userdet>lambdaQuery();
        //hutool
        if(StrUtil.isNotBlank(search)){
            wrapper.like(userdet::getNickName,search);
        }

        Page<userdet> userdetPage=userdetmapper.selectPage(new Page<>(currentPage,pageSize),wrapper);

        return Result.success(userdetPage);
    }
    @PostMapping("/Login")
    public Result<?> loginserver(@RequestBody userdet user){
       userdet res = userdetmapper.selectOne(Wrappers.<userdet>lambdaQuery().eq(userdet::getNickName,user.getNickName()).eq(userdet::getPassword,user.getPassword()));
       if(res==null){
           return Result.error("-1","用户名或密码错误！");

       }else{
           return Result.success(res);
       }
    }
    @PostMapping("/Register")
    public Result<?> register(@RequestBody userdet user){
        userdet res = userdetmapper.selectOne(Wrappers.<userdet>lambdaQuery().eq(userdet::getNickName,user.getNickName()));
        if(res!=null){
            return Result.error("-1","用户名重复！");

        }else{
            if(user.getPassword()==null&&user.getPassword().length()<=6){
                user.setPassword("123456");
            }

            userdetmapper.insert(user);
            return Result.success();
        }
    }

}
