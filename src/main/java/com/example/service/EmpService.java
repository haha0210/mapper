package com.example.service;

import com.example.pojo.Emp;
import com.example.pojo.PageBean;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface EmpService {

    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin,LocalDate end);

    void delete(List ids);

    void insert(Emp emp);

    Emp selectById(Integer id);

    void update(Emp emp);

    Emp login(Emp emp);
}
