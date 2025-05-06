package com.ccsw.tutorial_borrow.borrow.model;

import com.ccsw.tutorial_borrow.common.pagination.PageableRequest;

public class BorrowSearchDto {

    private PageableRequest pageable;

    public PageableRequest getPageable() {
        return pageable;
    }

    public void setPageable(PageableRequest pageable) {
        this.pageable = pageable;
    }
}
