package com.example.booksystem.core.vo;

import lombok.Data;

@Data
public class SplitPageDTO {

    private Integer currentPage = 1;

    private Integer pageSize = 10;

    private Integer total = 0;

    private Boolean asc = false;
}
