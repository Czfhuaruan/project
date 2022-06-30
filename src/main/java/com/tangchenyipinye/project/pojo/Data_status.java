package com.tangchenyipinye.project.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("wwh")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Data_status {
    private int id;
    private String wwhstatus;
}
