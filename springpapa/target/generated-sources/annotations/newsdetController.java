package com.example.springpapa.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springpapa.common.Result;
import com.example.springpapa.entity.newsdet;
import com.example.springpapa.mapper.newsdetMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/news")
public class newsdetController {
    @Resource
    newsdetMapper newsdetmapper;
    @PostMapping("/add")
    public Result<?> save(@RequestBody newsdet news){

        newsdetmapper.insert(news);
        return Result.success();

    }
    @PutMapping
    public Result<?> update(@RequestBody newsdet news){
        newsdetmapper.updateById(news);
        return Result.success();
    }
    @DeleteMapping("/{newsID}")
    public Result<?> delete(@PathVariable Integer newsID){
        newsdetmapper.deleteById(newsID);
        return Result.success();
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer currentPage,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        //分页查询
        LambdaQueryWrapper<newsdet> wrapper =  Wrappers.<newsdet>lambdaQuery();
        //hutool
        if(StrUtil.isNotBlank(search)){
            wrapper.like(newsdet::getAuthorName,search);
        }

        Page<newsdet> newsdetPage=newsdetmapper.selectPage(new Page<>(currentPage,pageSize),wrapper);

        return Result.success(newsdetPage);
    }
    @GetMapping("/pass")
    public Result<?> shenhe(@RequestParam(defaultValue = "1") Integer currentPage,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") Integer search){
        search=1;
        //分页查询
        LambdaQueryWrapper<newsdet> wrapper =  Wrappers.<newsdet>lambdaQuery();
        //hutool
            wrapper.like(newsdet::getState,search);

        Page<newsdet> newsdetPage=newsdetmapper.selectPage(new Page<>(currentPage,pageSize),wrapper);

        return Result.success(newsdetPage);
    }




}
