package com.wonders.fzb.base.consts;

public class FastQuery {
	
	/**
	 * 查询条件
	 * 例如 或者、并且
	 */
	public static enum Condition{
		/**
		 * 或者
		 */
		OR("OR"),
		
		/**
		 * 并且
		 */
		AND("AND");
		
		private Condition(String value){
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
	 * 查询符号
	 * 例如 Like(%)  Eq(=) NotEq(<>)
	 * <p>更多详情请看详细枚举中的元素注释</p>
	 */
	public static enum Symbol{
		/**
		 * LIKE
		 * <p><b>eg. LIKE '%VALUE%'</b></p>
		 */
		LIKE("LIKE"),
		
		/**
		 * NOT LIKE
		 * <p><b>eg. NOT LIKE '%VALUE%'</b></p>
		 */
		N_LIKE("NOT LIKE"),
		
		/**
		 * LIKE FIRST
		 * <p><b>eg. LIKE '%VALUE';</b></p>
		 */
		LIKE_F("LIKE"),
		
		/**
		 * LIKE LAST
		 * <p><b>eg. LIKE 'VALUE%'</b></p>
		 */
		LIKE_L("LIKE"),
		
		/**
		 * NOT LIKE FIRST
		 * <p><b>eg. NOT LIKE '%VALUE';</b></p>
		 */
		N_LIKE_F("NOT LIKE"),
		
		/**
		 * NOT LIKE LAST
		 * <p><b>eg. NOT LIKE 'VALUE%'</b></p>
		 */
		N_LIKE_L("NOT LIKE"),
		
		/**
		 * IS
		 * <p><b>eg. IS 'VALUE'</b></p>
		 */
		IS("IS"),
		
		/**
		 * IS NOT
		 * <p><b>eg. IS NOT 'VALUE'</b></p>
		 */
		IS_NOT("IS NOT"),
		
		/**
		 * IS NULL
		 * <p><b>eg. IS NULL</b></p>
		 */
		IS_NULL("IS NULL"),
		
		/**
		 * IS NOT NULL
		 * <p><b>eg. IS NOT NULL</b></p>
		 */
		IS_NOT_NULL("IS NOT NULL"),
		
		/**
		 * Equals
		 * <p><b>eg. = 'VALUE'</b></p>
		 */
		EQ("="),
		
		/**
		 * NOT Equals
		 * <p><b>eg. <> 'VALUE'</b></p>
		 */
		N_EQ("<>"),
		
		/**
		 * LESS THAN
		 * <p><b>eg. < 'VALUE'</b></p>
		 */
		LT("<"),
		
		/**
		 * LESS Equals
		 * <p><b>eg. <= 'VALUE'</b></p>
		 */
		LE("<"),
		
		/**
		 * Greater THAN
		 * <p><b>eg. > 'VALUE'</b></p>
		 */
		GT(">"),
		
		/**
		 * Greater Equals
		 * <p><b>eg. >= 'VALUE'</b></p>
		 */
		GE(">"),
		
		/**
		 * IN
		 * <p><b>eg. in (4,5,6)</b></p>
		 */
		IN("IN"),
		
		/**
		 * NOT IN
		 * <p><b>eg.  not in (1,2,3)</b></p>
		 */
		NOT_IN("NOT IN"),
		
		/**
		 * BETWEEN
		 * 暂未实现
		 */
		BETWEEN("BETWEEN"),
		/**
		 * EXISTS
		 * 暂未实现
		 */
		EXISTS("EXISTS");
		
		private Symbol(String value){
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
