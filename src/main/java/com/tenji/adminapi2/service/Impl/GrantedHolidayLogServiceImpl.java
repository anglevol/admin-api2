package com.tenji.adminapi2.service.Impl;

import com.tenji.adminapi2.dto.BasicQueryResult;
import com.tenji.adminapi2.dto.GrantedHolidayLogQueryForm;
import com.tenji.adminapi2.dto.GrantedHolidayLogVo;
import com.tenji.adminapi2.mapper.GrantedHolidayLogMapper;
import com.tenji.adminapi2.service.GrantedHolidayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrantedHolidayLogServiceImpl implements GrantedHolidayLogService {

    @Autowired
    GrantedHolidayLogMapper grantedHolidayLogMapper;

    /**
     * フォームから社員ID、ページ、表示件数を取得して、相応ページの付与・消化履歴を画面に渡します
     * @param queryForm
     * @return
     */
    @Override
    public BasicQueryResult getByEmployeeId(GrantedHolidayLogQueryForm queryForm) {
        int pageSize = queryForm.getPageSize();
        int currentPage = queryForm.getPageNum();
        long employeeId = queryForm.getEmployeeId();

        List <GrantedHolidayLogVo> holidayLogVoList = grantedHolidayLogMapper.selectByEmployeeId(employeeId, (currentPage-1)*pageSize, pageSize);
        int total = grantedHolidayLogMapper.selectTotolsByEmployeeId(employeeId);

        BasicQueryResult queryResult = new BasicQueryResult();
        queryResult.setResult(holidayLogVoList);
        queryResult.setPageSize(pageSize);
        queryResult.setPageNum(currentPage);
        queryResult.setTotalSize(total);

        return queryResult;
    }
}
