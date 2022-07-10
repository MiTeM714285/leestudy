package com.springboot.leestudy.domain.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.leestudy.domain.user.Entity.UserCommon;
import com.springboot.leestudy.domain.user.Entity.UserStudent;
import com.springboot.leestudy.domain.user.Entity.UserStudentAll;
import com.springboot.leestudy.domain.user.Entity.UserTeacher;
import com.springboot.leestudy.domain.user.Entity.UserTeacherAll;


@Mapper
public interface UserRepository {
	public UserCommon findUserCommonByUsername(String username) throws Exception;
	public UserStudent findUserStudentByPhonenum(String student_phonenum) throws Exception;
	public UserStudent findUserStudentByUsername(String username) throws Exception;
	public UserTeacher findUserTeacherByPhonenum(String teacher_phonenum) throws Exception;
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
	public int countUserStudentIsUrgent() throws Exception;
	public int countUserCommonByUsername(String username);
	public List<UserTeacherAll> findTeacherInfoBySearch(UserTeacherAll userTeacherAll) throws Exception;
	public List<UserStudentAll> findStudentInfoBySearch(UserStudentAll userStudentAll) throws Exception;
	public UserTeacherAll findTeacherInfoByDetail(String username) throws Exception;
	public UserStudentAll findStudentInfoByDetail(String username) throws Exception;
	public UserTeacherAll findTeacherInfoWriteReview(String username) throws Exception;
	public UserStudentAll findStudentInfoWriteReview(String username) throws Exception;
	public int updateProfileImg(UserCommon userCommon);
}
