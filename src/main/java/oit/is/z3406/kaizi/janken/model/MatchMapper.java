package oit.is.z3406.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MatchMapper {

  @Select("select id, user1, user2, user1Hand, user2Hand from matches")
  ArrayList<Match> selectAllMatch();

  @Select("select id, user1, user2, user1Hand, user2Hand from matches where id = #{id}")
  Match selectById(int id);

}
