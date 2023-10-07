package com.example.service;

import com.example.pojo.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeptService {

    //查询
    List<Dept> list();

    void delete(Integer id);

    void insert(Dept dept);

    Dept selectById(Integer id);
    void update(Dept dept);
}
