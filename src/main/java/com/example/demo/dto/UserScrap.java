package com.example.demo.dto;

import lombok.Data;

@Data
public class UserScrap {
	
	private int scrapNo;
	private enum UScraped{
		SCRAPE,
		NOSCRAPE;
		@Override
		public String toString() {
			switch(this) {
			case SCRAPE:
				return "Y";
			case NOSCRAPE:
				return "N";
				default :
					return super.toString();
			}
		}
	}
	private UScraped uScraped;
	private int userNo;
	private int postNo;

}
