package com.example.springpapa.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springpapa.common.Result;
import com.example.springpapa.entity.reviewdet;
import com.example.springpapa.mapper.reviewdetMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/review")
public class reviewdetController {
    @Resource
    reviewdetMapper reviewdetmapper;
    @PostMapping("/add")
    public Result<?> save(@RequestBody reviewdet review){

        reviewdetmapper.insert(review);
        return Result.success();

    }
    @PutMapping
    public Result<?> update(@RequestBody reviewdet review){
        reviewdetmapper.updateById(review);
        return Result.success();
    }
    @DeleteMapping("/{reviewID}")
    public Result<?> delete(@PathVariable Integer reviewID){
        reviewdetmapper.deleteById(reviewID);
        return Result.success();
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer currentPage,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        //分页查询
        LambdaQueryWrapper<reviewdet> wrapper =  Wrappers.<reviewdet>lambdaQuery();
        //hutool
        if(StrUtil.isNotBlank(search)){
            wrapper.like(reviewdet::getCommenter,search);
        }

        Page<reviewdet> reviewdetPage=reviewdetmapper.selectPage(new Page<>(currentPage,pageSize),wrapper);

        return Result.success(reviewdetPage);
    }


}
