package cn.qs.service.impl.common;

import java.util.List;
import java.util.Map;

import cn.qs.bean.user.User;
import cn.qs.dao.common.MessageRepository;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.qs.bean.common.Message;
import cn.qs.service.common.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepository messageRepository;

	@Override
	public void insert(Message message) {
		 messageRepository.insert(message);
	}

	@Override
	public List<Message> getMessages(Map condition) {
		List<Message> list = messageRepository.findAll();
		return list;
	}

	@Override
	public Message getMessageDetail(String blogId) {
		return messageRepository.findOne(blogId);
	}

	@Override
	public void deleteMessage(String id) {
		messageRepository.delete(id);
	}

	@Override
	public void updateMessage(Message message) {
		messageRepository.save(message);
	}

	@Override
	public Page<Message> getMessageServicePage(Map condition) {
		//构造请求参数，页号从0开始。
		int pageNum = MapUtils.getInteger(condition,"pageNum",0);
		int pageSize = MapUtils.getInteger(condition,"pageSize",0);
		Pageable pageRequest = new QPageRequest(pageNum,pageSize);

		Page<Message> page = messageRepository.findAll(pageRequest);
		return  page;
	}

}
