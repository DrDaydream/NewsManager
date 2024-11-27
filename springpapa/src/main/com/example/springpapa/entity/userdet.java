package com.example.springpapa.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

//JavaBean必须满足小驼峰规则!
@Data
@TableName("userdet")
public class userdet {
    @TableId(type = IdType.AUTO,value = "userID")
    private Integer userID;
    @TableField(value = "NickName")
    private String nickName;
    @TableField(value = "AvatarUrl")
    private String avatarUrl;
    @TableField(value = "Gender")
    private Integer gender;
    @TableField(value = "Province")
    private String province;
    @TableField(value = "City")
    private String city;
    @TableField(value = "Country")
    private String country;
    @TableField(value = "CreateTime")
    private String createTime;
    @TableField(value = "State")
    private Integer state;
    @TableField(value = "password")
    private String password;

}
