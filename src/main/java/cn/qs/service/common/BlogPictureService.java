package cn.qs.service.common;


import cn.qs.bean.common.Blogpicture;

public interface BlogPictureService {
	void insert(Blogpicture blogpicture);

	String getPicturePathByPictureId(String pictureId);
}
