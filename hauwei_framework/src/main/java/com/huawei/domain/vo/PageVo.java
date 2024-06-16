package com.huawei.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor

public class PageVo {
    private List rows;
    private Long total;
}
