package com.wonders.fzb.base.consts;

/**
 * 日志常量
 * @author ZSW
 * 2016-06-02
 */
public class LogConst {
	/**
	 * 日志类别
	 * @author ZSW
	 */
	public static enum Category {
		/**
		 * 运行时日志（在运行的过程中产生的轨迹记录）
		 */
		RUNTIME("信息"),
		
		/**
		 * 登录日志（用户登入时的操作日志）
		 */
		LOGIN ("登录"),
		
		/**
		 * 敏感操作日志
		 */
		WARNING("警告"),
		/**
		 * 异常记录（发生异常）
		 */
		ERROR("异常"),
		
		/**
		 * 调试信息
		 */
		DEBUG ("调试");
		
		private Category(String value){
			this.value = value;
		}
		
		private String value;
		
		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}
	}
	
	/**
	 * 日志级别
	 * @author ZSW
	 */
	public static enum Rank {
		/**
		 * 重要记录（一般是手动途径记录）
		 */
		IMPORTANT ("重要"),
		
		/**
		 * 日志的默认级别
		 */
		NORMAL("正常");
		
		private Rank(String value){
			this.value = value;
		}
		
		private String value;
		
		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}
	}
}
