package com.huawei.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class LinkVO {

    private Long id;


    private String name;

    private String logo;

    private String description;
    //网站地址
    private String address;


}
