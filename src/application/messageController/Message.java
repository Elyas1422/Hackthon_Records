package application.messageController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.awt.*;

public class Message {
	public static String getMessage(String CompetitionName, String ParticipantName) {
		ArrayList<String> list = new ArrayList<>();
		try(Scanner input = new Scanner(new File("./data/Email Body template.txt"))) {
			while(input.hasNext()) {
				list.add(input.nextLine());
			}
	
		} catch (FileNotFoundException e) {
			System.out.println("The input file does not exist");
		}
		list.set(0, list.get(0).replace("[Student name/Team name]", ParticipantName));
		list.set(2, list.get(2).replace("[Competition name]", CompetitionName));
		
		String result = ""; 
		for(int i = 0; i < list.size(); i++) {
			result += list.get(i) + "\n";
		}
		return result;
	}
	public static String getSubject(Integer rank,String CompetitionName) {
		String placeString ="th";
		String rankStr= rank.toString();
		if (rankStr.charAt(rankStr.length()-1)=='1') {
			placeString ="st";
		}
		else if (rankStr.charAt(rankStr.length()-1)=='2') {
			placeString ="nd";
		}
		else if (rankStr.charAt(rankStr.length()-1)=='3') {
			placeString ="rd";
		}
		
		String subject= "Congratulation on achieving [their ranking] place in [the competition name]";
		subject=subject.replace("[their ranking]", rankStr+placeString);
		subject=subject.replace("[the competition name]", CompetitionName);
		return subject;
	}
	public static void mailto(List<String> recipients, String subject,
	        String body) throws IOException, URISyntaxException {
	    String uriStr = String.format("mailto:%s?subject=%s&body=%s",
	            join(",", recipients), // use semicolon ";" for Outlook!
	            urlEncode(subject),
	            urlEncode(body));
	    
	    Desktop.getDesktop().browse(new URI(uriStr));
	}

	private static final String urlEncode(String str) {
	    try {
	        return URLEncoder.encode(str, "UTF-8").replace("+", "%20");
	    } catch (UnsupportedEncodingException e) {
	        throw new RuntimeException(e);
	    }
	}

	public static final String join(String sep, Iterable<?> objs) {
	    StringBuilder sb = new StringBuilder();
	    for(Object obj : objs) {
	        if (sb.length() > 0) sb.append(sep);
	        sb.append(obj);
	    }
	    return sb.toString();
	}

	public static void main(String[] args) throws IOException, URISyntaxException {
	    mailto(Arrays.asList("john@example.com", "jane@example.com"), "Hello!",
	            "This is\nan automatically sent email!\n");
	}
}
