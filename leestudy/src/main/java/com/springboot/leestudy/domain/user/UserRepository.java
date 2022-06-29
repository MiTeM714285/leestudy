package com.springboot.leestudy.domain.user;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.leestudy.domain.user.Entity.UserCommon;
import com.springboot.leestudy.domain.user.Entity.UserStudent;
import com.springboot.leestudy.domain.user.Entity.UserTeacher;


@Mapper
public interface UserRepository {
	public UserCommon findUserCommonByUsername(String username) throws Exception;
	public int saveUserCommon(UserCommon userCommon) throws Exception;
	public int saveUserStudent(UserStudent userStudent) throws Exception;
	public int saveUserTeacher(UserTeacher userTeacher) throws Exception;
}
