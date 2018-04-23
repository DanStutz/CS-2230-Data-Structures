package hw3;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Browser {
		private static	final Pattern urlPat = Pattern.compile("new (?<url>[a-zA-Z0-9./~]+[a-zA-Z0-9/]+)");
		private static 	final Pattern deletePat = Pattern.compile("close (?<num>[0-9]+)");
		private static	final Pattern dispPat = Pattern.compile("display (?<num>[0-9]+)");
		private static	final Pattern movePat = Pattern.compile("move (?<from>[0-9]+) (?<to>[0-9]+)");
		private static	final Pattern closePat = Pattern.compile("close (?<num>[0-9]+)");
                private static final Pattern replacePat = Pattern.compile("navigate (?<num>[0-9]+) (?<url>[a-zA-Z0-9./~]+[a-zA-Z0-9/]+)");

		public static void main(String[] args) throws IOException, URISyntaxException{
			List tabs = new LinkedList();

			while (true){
					System.out.println("new <url> / close <num> / display <num> / move <from> <to> / navigate <num> <url> / count / exit");
					BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
					String cmd = in.readLine();
					cmd = cmd.trim();

					Matcher urlMat = urlPat.matcher(cmd);
					Matcher deleteMat = deletePat.matcher(cmd);
					Matcher dispMat = dispPat.matcher(cmd);
					Matcher swapMat = movePat.matcher(cmd);
					Matcher closeMat = closePat.matcher(cmd);
                                        Matcher replaceMat = replacePat.matcher(cmd);
					if (urlMat.matches()) {
							tabs.add(new Tab(urlMat.group("url")));
					} else if (deleteMat.matches()) {
							tabs.remove(Integer.parseInt(deleteMat.group("num")));
					} else if (dispMat.matches()) {
							Desktop.getDesktop().browse(new URI(((Tab)tabs.get(Integer.parseInt(dispMat.group("num")))).url));
					} else if (swapMat.matches()) {
							int from = Integer.parseInt(swapMat.group("from"));
							int to = Integer.parseInt(swapMat.group("to"));
							tabs.move(from, to);
					} else if (replaceMat.matches()){
                                                        tabs.replace(Integer.parseInt(replaceMat.group("num")), new Tab(replaceMat.group("url")));
                                        } else if(cmd.equals("count")) {
												int tabcount = tabs.size();
												if (tabcount == 1) {
                                                        System.out.println("You currently have 1 tab open.");
												} else {
                                                        System.out.println("You currently have "+tabs.size()+" tabs open.");
												}
					} else if (cmd.equals("exit")){
                                            break;
                                        }else{
							System.out.println("UNRECOGNIZED COMMAND");
					}

					Object[] tabsArr = tabs.toArray();
					int i = 0;
					for (Object o : tabsArr) {
							Tab t = (Tab) o;
							System.out.print("|"+i+" "+t.title+" |");
							System.out.print(" ");
							i++;
					}
					System.out.println();
			}
		}

}
