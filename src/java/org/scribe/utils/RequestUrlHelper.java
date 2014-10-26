package org.scribe.utils;

import model.Photo;


public class RequestUrlHelper {
	private static String splitKeywords(String input) {
		String[] str = input.split(" ");
		StringBuilder sb = new StringBuilder();
		for(String s : str) {
			sb.append(s);
			sb.append('-');
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
	
	public static String getTwitterRequestUrl(String keyword) {
		return keyword;
	}
	
	public static String getFlickrRequestUrl(String keyword) {
		return ("http://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=38a56943810d166e2af72749ab5e09a3&text=" 
					+ splitKeywords(keyword) + "&sort=relevance&content_type=1&extras=views&per_page=6&page=1");
	}
	
	public static String getPhotoUrl(Photo photo) {
		String url = "http://farm" + photo.getFarm() + ".staticflickr.com/" + photo.getServer() + "/" + photo.getId() + "_" + photo.getSecret() + ".jpg";
		return url;
	}
}
