package cn.boysama.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.boysama.entity.Vpn;

@Service
public interface VpnRepository extends CrudRepository<Vpn, Integer>{
	
	/**
	 * 根据日期，返回列表
	 * @param date
	 * @return
	 */
	List<Vpn> findByDate(String date);
	
	/**
	 * 根据日期和时间和ip，返回列表得到id
	 * @param date
	 * @param time
	 * @param ip
	 * @return
	 */
	List<Vpn> findByDateAndTimeAndIp(String date, String time, String ip);
	
	/**
	 * 更新现有reserve
	 * @param reserveInfo
	 * @param id
	 * @return
	 */
	@Modifying
	@Transactional(readOnly=false)
	@Query(value="update Vpn r set r.reserveInfo=?1 where r.id=?2")
	Integer updateReserveInfoById(String reserveInfo, Integer id);
}
