package com.example.springpapa.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@TableName("newsdet")
public class newsdet  {
    @TableId(type = IdType.AUTO,value = "newsID")
    private Integer newsID;
    @TableField("newsTitle")
    private String newsTitle;
    @TableField("newsType")
    private Integer newsType;
    @TableField("newsContent")
    private String newsContent;
    @TableField("CoverImagefilea")
    private String coverImagefilea;
    @TableField("ReadCount")
    private Integer readCount;
    @TableField("AuthorName")
    private String authorName;
    @TableField("CreatorID")
    private Integer creatorID;
    @TableField("CreateTime")
    private String createTime;
    @TableField("State")
    private Integer state;
    @TableField("CoverImagefileb")
    private String coverImagefileb;
    @TableField("CoverImagefilec")
    private String coverImagefilec;


}
