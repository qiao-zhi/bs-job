package cn.qs.dao.common;

import cn.qs.bean.common.Message;
import cn.qs.bean.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author: qlq
 * @Description
 * @Date: 21:44 2019/4/17
 */
public interface MessageRepository extends MongoRepository<Message, String> {
}
