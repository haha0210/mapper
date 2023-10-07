package com.example.mapper;

import com.example.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

//    @Select("select count(*) from emp ")
    Long count();
    //返回数据列表
//    @Select("select * from emp limit #{start},#{pageSize}")
    List<Emp> page(Integer start, Integer pageSize, String name, Short gender, LocalDate begin,LocalDate end);

    //@delete
    void delete(List ids);

    @Insert("insert into emp(username,name,gender,image,job,dept_id,create_time,update_time)" +
            "values(#{username},#{name},#{gender},#{image},#{job},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    @Select("select * from emp where id = #{id}")
    Emp selectById(Integer id);
    //    @Update()
    void update(Emp emp);

    //根据用户名和密码查询员工
    @Select("select * from emp where username = #{username} and password =#{password}")
    Emp getByUsernameAndPassword(Emp emp);

    //根据部门id删除该部下的员工数据
    @Delete("delete from emp where dept_id = #{deptId}")
    void deleteByDeptId(Integer deptId);
}
