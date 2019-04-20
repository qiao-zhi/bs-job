package cn.qs.service.common;

import java.util.List;
import java.util.Map;

import cn.qs.bean.common.Message;
import org.springframework.data.domain.Page;

public interface MessageService {
	void insert(Message message);

	List<Message> getMessages(Map condition);

	Message getMessageDetail(String blogId);

	void deleteMessage(String id);

	void updateMessage(Message message);

	Page<Message> getMessageServicePage(Map condition);
}
