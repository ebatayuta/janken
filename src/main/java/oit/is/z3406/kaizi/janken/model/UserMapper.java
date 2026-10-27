package oit.is.z3406.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

  @Select("select ID, USERNAME FROM Users")
  ArrayList<User> selectAllUser();

  @Select("select ID, USERNAME from Users WHERE ID = #{id}")
  User selectById(int id);

}
