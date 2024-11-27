package com.example.springpapa.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("usnew")
public class usnew {
    @TableField(value = "userid")
    private Integer userid;
    @TableField(value = "newsid")
    private Integer newsid;
    @TableField(value = "operate")
    private Integer operate;

}
