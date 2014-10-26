package com.carsFinder.twitter;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import model.Photo;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;
import org.scribe.utils.RequestUrlHelper;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;


public class Flickr {
//	public static void main(String[] args) {
//		ArrayList<String> list = getPhoto("Toyota RAV4");
//		System.out.println();
//	}
	
	public ArrayList<String> getPhoto(String keyword) {
//		String apiKey = "38a56943810d166e2af72749ab5e09a3";
//	    String apiSecret = "22ba01f7d995fda5";
//	    OAuthService service = new ServiceBuilder().provider(FlickrApi.class).apiKey(apiKey).apiSecret(apiSecret).build();
//	    Token requestToken = new Token("72157640567401683-aff905a406a7dcc7", "e7e3d74117ee2b94");
//	    Verifier verifier = new Verifier("161-015-344");
//	    Token accessToken = service.getAccessToken(requestToken, verifier);
	    String requestUrl = RequestUrlHelper.getFlickrRequestUrl(keyword);
	    OAuthRequest request = new OAuthRequest(Verb.GET, requestUrl);
//	    service.signRequest(accessToken, request);
	    Response response = request.send();
	    ArrayList<String> photoUrlList = new ArrayList<String>();
		try {
		    DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
			domFactory.setNamespaceAware(true); 		// never forget this!
			DocumentBuilder builder = domFactory.newDocumentBuilder();
			Document doc = builder.parse(new ByteArrayInputStream(response.getBody().getBytes("UTF-8")));
			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();
			XPathExpression expr = xpath.compile("//photos/photo");
			Object result = expr.evaluate(doc, XPathConstants.NODESET);
			NodeList nodes = (NodeList) result;
			for (int i = 0; i < nodes.getLength(); i++) {
				NamedNodeMap map = nodes.item(i).getAttributes();
				if(map!=null) {
					photoUrlList.add(RequestUrlHelper.getPhotoUrl(createPhoto(map)));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return photoUrlList;
	}
	
	public static Photo createPhoto(NamedNodeMap map) {
		Photo photo = new Photo();
		photo.setFarm(Integer.parseInt(map.getNamedItem("farm").getNodeValue()));
		photo.setId(Long.parseLong(map.getNamedItem("id").getNodeValue()));
		photo.setIsfamily(Boolean.parseBoolean(map.getNamedItem("isfamily").getNodeValue()));
		photo.setIsfriend(Boolean.parseBoolean(map.getNamedItem("isfriend").getNodeValue()));
		photo.setIspublic(Boolean.parseBoolean(map.getNamedItem("ispublic").getNodeValue()));
		photo.setOwner(map.getNamedItem("owner").getNodeValue());
		photo.setSecret(map.getNamedItem("secret").getNodeValue());
		photo.setServer(Integer.parseInt(map.getNamedItem("server").getNodeValue()));
		photo.setTitle(map.getNamedItem("title").getNodeValue());
		photo.setViews(Integer.parseInt(map.getNamedItem("views").getNodeValue()));
		return photo;
	}
}
