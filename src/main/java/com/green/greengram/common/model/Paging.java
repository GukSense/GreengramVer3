package com.green.greengram.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.greengram.common.GlobalConst;
import lombok.Getter;
import lombok.Setter;

import java.beans.ConstructorProperties;

@Setter
@Getter
public class Paging {
    private int size;
    private int page;
    @JsonIgnore
    private int startIdx;

    @ConstructorProperties({"page", "size"})
    public Paging(Integer size, Integer page) {
        this.page = page == null ? 1 : page;
        this.size = size == null ? 10 : size;
        this.startIdx = (this.page - 1) * this.size;
    }
}
