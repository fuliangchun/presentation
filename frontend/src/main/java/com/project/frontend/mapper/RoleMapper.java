package com.project.frontend.mapper;

import com.project.frontend.domain.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface RoleMapper {

	@Select("SELECT r.id,r.role_name,r.note FROM t_user u,t_role r,t_user_role ur WHERE u.id=ur.user_id AND r.id=ur.role_id AND u.user_name=#{username}")
	@Results({
	@Result(id=true, column="id", property="id"),
	@Result(column="role_name", property="rolename"),
	@Result(column="note", property="note")
	})
    Role findByUserName(String username);

}
