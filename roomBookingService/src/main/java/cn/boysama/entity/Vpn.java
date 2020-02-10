package cn.boysama.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="vpn")
@Data
public class Vpn {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	//日期 如0205
	@Column(name="date")
	private String date;
	
	//时间段 如0800-1200
	@Column(name="time")
	private String time;
	
	//预定信息 如张三
	@Column(name="reserveInfo")
	private String reserveInfo;
	
	//ip 如1.1.1.1
	@Column(name="ip")
	private String ip;
}
