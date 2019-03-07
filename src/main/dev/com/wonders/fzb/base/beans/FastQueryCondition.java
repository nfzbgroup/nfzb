package com.wonders.fzb.base.beans;

import com.wonders.fzb.base.consts.FastQuery.Condition;
import com.wonders.fzb.base.consts.FastQuery.Symbol;
/**
 * 快速查询条件
 * @author ZSW
 */
public class FastQueryCondition {
		private Symbol symbol;
		private Object[] value;
		private Condition condition;
		
		public Symbol getSymbol() {
			return symbol;
		}
		public void setSymbol(Symbol symbol) {
			this.symbol = symbol;
		}
		public Object[] getValue() {
			return value;
		}
		public void setValue(Object ...value) {
			this.value = value;
		}
		public Condition getCondition() {
			return condition;
		}
		public void setCondition(Condition condition) {
			this.condition = condition;
		}
		public FastQueryCondition(Symbol symbol,Object ...value){
			this(symbol,Condition.AND,value);
		}
		public FastQueryCondition(Symbol symbol,Condition condition,Object ...value){
			this.symbol = symbol;
			this.condition = condition;
			this.value = value;
		}
}
