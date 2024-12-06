package com.example.demo.dto;

import lombok.Data;

@Data
public class UscBookMark {
	
	private int u_s_c_No;
	public enum UcSubscribed{
		BOOKMARK,
		NOBOOKMARK;
		@Override
		public String toString() {
			switch(this) {
			case BOOKMARK:
				return "BOOKMARK";
			case NOBOOKMARK:
				return "NOBOOKMARK";
				default :
					return super.toString();
			}
		}
	}
	private UcSubscribed ucSubscribed;
	private int userNo;
	private int comNo;

}