package cn.boysama.repository;

import cn.boysama.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserRepository extends CrudRepository<User, Integer>{
	
	/**
	 * 根据用户ID返回用户的基本信息
	 * @param id
	 * @return
	 */
	User findByUserId(Integer id);
	
	/**
	 * 根据工号返回用户的基本信息
	 * @param userNo
	 * @return
	 */
	User findByUserNo(String userNo);

}
