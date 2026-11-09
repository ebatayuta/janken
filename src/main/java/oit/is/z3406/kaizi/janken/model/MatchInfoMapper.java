package oit.is.z3406.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MatchInfoMapper {

  @Select("select id, user1, user2, user1Hand, isActive from matchinfo")
  ArrayList<MatchInfo> selectAllMatchInfo();

  @Select("select id, user1, user2, user1Hand, isActive from matchinfo where id = #{id}")
  MatchInfo selectById(int id);

  @Select("select id, user1, user2, user1Hand, isActive from matchinfo where isActive = TRUE")
  ArrayList<MatchInfo> selectActiveMatchInfo();

  @Insert("insert into matchinfo (user1,user2,user1Hand,isActive) VALUES (#{user1},#{user2},#{user1Hand},#{isActive});")
  void insertMatchInfo(MatchInfo match);

}
