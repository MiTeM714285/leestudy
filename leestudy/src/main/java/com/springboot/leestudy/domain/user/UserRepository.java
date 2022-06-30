package com.springboot.leestudy.domain.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.leestudy.domain.user.Entity.UserCommon;
import com.springboot.leestudy.domain.user.Entity.UserStudent;
import com.springboot.leestudy.domain.user.Entity.UserTeacher;
import com.springboot.leestudy.domain.user.Entity.UserTeacherAll;


@Mapper
public interface UserRepository {
	public UserCommon findUserCommonByUsername(String username) throws Exception;
	public UserStudent findUserStudentByUsername(String username) throws Exception;
	public UserTeacher findUserTeacherByUsername(String username) throws Exception;
	public String findPasswordByUsername(String username) throws Exception;
	public int saveUserCommon(UserCommon userCommon) throws Exception;
	public int saveUserStudent(UserStudent userStudent) throws Exception;
	public int saveUserTeacher(UserTeacher userTeacher) throws Exception;
	public int updateUserCommonByUsername(UserCommon userCommon) throws Exception;
	public int updateUserCommonByUsernameWithoutPassword(UserCommon userCommon) throws Exception;
	public int updateUserStudentByUsername(UserStudent userStudent) throws Exception;
	public int updateUserStudentByUsernameWithoutPhonenum(UserStudent userStudent) throws Exception;
	public int updateUserTeacherByUsername(UserTeacher userTeacher) throws Exception;
	public int updateUserTeacherByUsernameWithoutPhonenum(UserTeacher userTeacher) throws Exception;
	public int deleteUserCommonByUsername(String username) throws Exception;
	public int countUserCommonByRole(String role) throws Exception;
	public List<UserTeacherAll> findTeacherInfoBySearch(UserTeacherAll userTeacherAll) throws Exception;
}
