package com.example.springpapa.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class reviewdet {
    @TableId(type = IdType.AUTO,value = "ReviewID")
    private Integer reviewID;
    @TableField("NewsID")
    private Integer newsID;
    @TableField("parentReviewID")
    private Integer parentreviewID;
    @TableField("ReviewContent")
    private String reviewContent;
    @TableField("CreateTime")
    private String createTime;
    @TableField("State")
    private Integer state;
    @TableField("commenter")
    private Integer commenter;
    @TableField("zan")
    private Integer zan;
    @TableField("cai")
    private Integer cai;
}
