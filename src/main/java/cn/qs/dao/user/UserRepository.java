package cn.qs.dao.user;

import cn.qs.bean.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author: qlq
 * @Description
 * @Date: 21:28 2019/4/15
 */
public interface UserRepository extends MongoRepository<User, String> {
    public User findByUsernameAndPassword(String username, String pwd);
    public User findByUsername(String username);
}
