package com.wonders.fzb.platform.services;

import java.util.List;

import com.wonders.fzb.platform.beans.UuRelate;

public interface UuRelateService {

	/**
	 * 根据平台账号获得该用户 所有对象
	 * @param plantFormId 平台
	 * @return
	 */
	public List<UuRelate> getByPlantFormId(String plantFormId);

	/**
	 * 保存
	 * @param uuRelate 用户用户关联表
	 * @return
	 */
	public String saveOrUpdate(UuRelate uuRelate);

	/**
	 * 删除
	 * @param uuId 主键ID
	 * @return
	 */
	public String delete(String uuId);

}
