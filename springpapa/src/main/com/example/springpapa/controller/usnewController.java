package com.example.springpapa.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springpapa.common.Result;
import com.example.springpapa.entity.newsdet;
import com.example.springpapa.mapper.newsdetMapper;
import com.example.springpapa.mapper.userdetMapper;
import com.example.springpapa.mapper.usnewMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/uop")
public class usnewController {
    @Resource
    usnewMapper usnewmapper;

}
