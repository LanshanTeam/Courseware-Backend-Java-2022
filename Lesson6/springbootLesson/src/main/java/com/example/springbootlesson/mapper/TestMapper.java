package com.example.springbootlesson.mapper;


import com.example.springbootlesson.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TestMapper {

    public List<User> selectAll();

    public User selectById(Integer id);

    public void insert(User user);

    public void deleteByUserId(@Param("id") Integer uId);

    public List<User> selectByGenderAndHomeAddr(@Param("gender") String gender, @Param("homeAddr") String homeAddr);

    public List<User> selectByUserNameAndGender(String username, String gender);

    public void deleteByIds(int[] ids);
}
