package oit.is.z3406.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

  @Select("select id, userName from users")
  ArrayList<User> selectAllUser();

  @Select("select id, userName from users where id = #{id}")
  User selectById(int id);

}
