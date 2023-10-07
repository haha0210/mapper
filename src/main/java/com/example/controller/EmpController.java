package com.example.controller;

import com.example.pojo.Emp;
import com.example.pojo.PageBean;
import com.example.pojo.Result;
import com.example.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@Slf4j
public class EmpController {
    @Autowired
    private EmpService empService;

    @GetMapping("/emps")
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("分页查询，参数：{},{},{},{},{},{}",page,pageSize,name,gender,begin,end);
        PageBean pageBean = empService.page(page,pageSize,name,gender,begin,end);
        return Result.success(pageBean);
    }

    @DeleteMapping("/emps/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除员工 ids:{}",ids);
        empService.delete(ids);
        return Result.success();
    }

    @PostMapping("/emps")
    public Result insert(@RequestBody Emp emp){
        log.info("新员工,emp: {}",emp);
        empService.insert(emp);
        return Result.success();
    }

    @GetMapping("/emps/{id}")
    public Result selectById(@PathVariable Integer id){
        log.info("根据id粗查询信息：{}",id);
        Emp emp = empService.selectById(id);
        return Result.success(emp);
    }
    @PutMapping("/emps")
    public Result update(@RequestBody Emp emp){
        log.info("修改的员工信息：{}",emp);
        empService.update(emp);
        return Result.success();
    }

}
