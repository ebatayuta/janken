package oit.is.z3406.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MatchInfoMapper {

  @Select("select id, user1, user2, user1Hand, isActive from matchinfo")
  ArrayList<MatchInfo> selectAllMatchInfo();

  @Select("select id, user1, user2, user1Hand, isActive from matchinfo where id = #{id}")
  MatchInfo selectById(int id);

  @Select("select id, user1, user2, user1Hand, isActive from matchinfo where isActive = TRUE")
  ArrayList<MatchInfo> selectActiveMatchInfo();

  @Select("select id, user1, user2, user1Hand, isActive from matchinfo where isActive = TRUE and (user1 = #{id} or user2 = #{id})")
  ArrayList<MatchInfo> selectMatchingMatchInfo(int id);

  @Insert("insert into matchinfo (user1,user2,user1Hand,isActive) VALUES (#{user1},#{user2},#{user1Hand},#{isActive});")
  void insertMatchInfo(MatchInfo match);

  @Update("update matchinfo set isActive = #{isActive} WHERE ID = #{id}")
  void updateById(MatchInfo matchInfo);

}
