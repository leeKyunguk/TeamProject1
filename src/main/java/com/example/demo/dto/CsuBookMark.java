package com.example.demo.dto;

import lombok.Data;

@Data
public class CsuBookMark {
	
	private int c_s_u_No;
	private enum CuSubscribed{
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
	private CuSubscribed cuSubscribed;
	private int userNo;
	private int comNo;
}
