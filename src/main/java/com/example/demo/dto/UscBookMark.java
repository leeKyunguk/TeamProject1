package com.example.demo.dto;

import lombok.Data;

@Data
public class UscBookMark {
	
	private int u_s_c_No;
	private enum UcSubscribed{
		BOOKMARK,
		NOBOOKMARK;
		@Override
		public String toString() {
			switch(this) {
			case BOOKMARK:
				return "Y";
			case NOBOOKMARK:
				return "N";
				default :
					return super.toString();
			}
		}
	}
	private UcSubscribed ucSubscribed;
	private int userNo;
	private int comNo;

}