package com.project.frontend.mapper;


import com.project.frontend.domain.User;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserMapper {

	@Select("SELECT id,user_name,pwd,avaliable,note FROM t_user WHERE user_name=#{username}")

	@Results({
	@Result(id=true, column="id", property="id"),
	@Result(column="user_name", property="username"),
	@Result(column="avaliable", property="avaliable"),
	@Result(column="pwd", property="pwd"),
	@Result(column="note", property="note"),
	@Result(column="user_name", property="roles",
	many=@Many(select="com.project.frontend.mapper.RoleMapper.findByUserName"))
	})
	User findByUsername(String username);

}
