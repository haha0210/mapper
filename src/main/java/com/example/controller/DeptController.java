package com.example.controller;

import com.example.pojo.Dept;
import com.example.pojo.Result;
import com.example.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j   //定义日志记录对象
@RestController
public class DeptController {

    @Autowired  //自动创建实例化对象，并加入容器中
    private DeptService deptService;

//    @RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping("/depts")  //限定请求方式为GET
    public  Result list(){
//        System.out.println("查询全部部门数据");
        log.info("查询全部部门数据");

        //调用service 查询部门
       List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }

    @DeleteMapping("/depts/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除部门:{}",id);
        deptService.delete(id);
        return Result.success();
    }

    @PostMapping("/depts")
    public Result insert(@RequestBody Dept dept){
        log.info("新增部门：{}",dept);
        deptService.insert(dept);
        return Result.success();
    }

    @GetMapping("/depts/{id}")
    public Result selectById(@PathVariable Integer id){
        log.info("通过id查看: {}",id);
        Dept dept = deptService.selectById(id);
        return Result.success(dept);
    }

    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept){
        log.info("修改部门：{}",dept);
        deptService.update(dept);
        return Result.success();
    }
}
