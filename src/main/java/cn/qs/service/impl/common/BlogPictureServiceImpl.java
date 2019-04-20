package cn.qs.service.impl.common;

import cn.qs.bean.common.Blogpicture;
import cn.qs.dao.common.BlogpictureRepository;
import cn.qs.service.common.BlogPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BlogPictureServiceImpl implements BlogPictureService {

	@Autowired
	private BlogpictureRepository blogpictureRepository;

	@Override
	public void insert(Blogpicture blogpicture) {
		 blogpictureRepository.insert(blogpicture);
	}

	@Override
	public String getPicturePathByPictureId(String pictureId) {
		return blogpictureRepository.findOne(pictureId).getPath();
	}

}
