package hw3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
public class Tab {
		
		String url;
		String title;

		public Tab(String url) {
				if (!url.startsWith("http")) {
						this.url = "https://" + url;
				} else {
						this.url = url;
				}
				try {
						URLConnection conn = new URL(this.url).openConnection();
						conn.setReadTimeout(4000);
						conn.setConnectTimeout(4000);
						String all = new BufferedReader(new InputStreamReader(conn.getInputStream())).lines().collect(Collectors.joining());
						Pattern title = Pattern.compile("<title>([^<]+)<[/]title>");
						Matcher m = title.matcher(all);
						if (m.find()) {
								// just use the first characters
								String t = m.group(1);
								if (t.length() > 15) {
										t = t.substring(0, 15) + "...";
								}
								this.title = t;
						} else {
								this.title = this.url;
						}
				} catch (Exception ex) {
						System.err.println(ex);
						this.title = this.url;
				}
		} // just use the first characters
		
}
