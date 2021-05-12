package com.example.demo.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.example.demo.Model.TestModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Admin
 */
@Api(tags = "首页模块")
@RestController
@RequestMapping("/api")
public class TestController {

    @SneakyThrows
    @GetMapping("/test")
    @ApiOperation("测试")
    public void test(HttpServletResponse response) {
        List<TestModel> testVoList = new ArrayList<>();
        testVoList.add(new TestModel("张三"));
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename="
                + URLEncoder.encode("test.xlsx", "utf-8"));
        //将数据写入到excel中并且写入到响应流中
        ServletOutputStream outputStream = response.getOutputStream();
        //获取工作薄写对象
        ExcelWriterBuilder excelWriter = EasyExcel.write(outputStream, TestModel.class);
        //具体工作表对象 不指定具体的序号默认为第一张
        ExcelWriterSheetBuilder sheet = excelWriter.sheet();
        //写入数据 (工作簿对象指定的是流则写入到流)
        sheet.doWrite(testVoList);
    }

}
