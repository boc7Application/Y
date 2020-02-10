package cn.boysama.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="user")
@Data
public class User {
	
	//主键，自增
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private Integer userId;
	
	//用户4位工号
	@Column(name="user_no")
	private String userNo;
	
	//用户姓名
	@Column(name="user_name")
	private String userName;
	
	//用户部门
	@Column(name="user_department")
	private String userDepartment;
	
	//用户团队
	@Column(name="user_group")
	private String userGroup;
	
	//用户性别，如男，女
	@Column(name="user_gender")
	private String userGender;
	
	//用户手机号
	@Column(name="user_phoneNum")
	private String userPhoneNum;
}
