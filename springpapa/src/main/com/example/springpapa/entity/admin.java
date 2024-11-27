package com.example.springpapa.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("admin")
public class admin {
    @TableId(type= IdType.AUTO,value = "adminid")
    private Integer adminid;
    @TableField("nickName")
    private String nickName;
    @TableField("avatar")
    private String avatar;
    @TableField("password")
    private String password;
    @TableField("State")
    private Integer state;



}
