package com.wonders.fzb.platform.services;

/**
 * ip业务接口
 * 
 * @author scalffold
 * 
 */
public interface IpService {
	public int[] getIPInt(String ip);

	public boolean checkIPFormat(String ip);

	/**
	 * 检查IP地址范围字符串格式
	 * 
	 * @param range
	 * @return
	 */
	public boolean checkIPRangeFormat(String range);

	/**
	 * 判断IP地址是否在IP范围内
	 * 
	 * @param ip
	 *            IP地址，字符串形式，X.X.X.X
	 * @param range
	 *            IP地址范围，X.X.X.X, 用*做通配符，用;分隔
	 * @return true or false
	 */
	public boolean IPInRange(String ip, String range);
}
