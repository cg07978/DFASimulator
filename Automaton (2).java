package newpat;
import java.util.*;
import java.io.*;

public class Automaton {

	ArrayList<String> states = new ArrayList<String>();
	static ArrayList<String> terminals = new ArrayList<String>();
	String[][] transition;
	String start;
	ArrayList<String> accepted = new ArrayList<String>();
	static Scanner scan;
	
	public Automaton(String filename) {
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			String in = br.readLine();
			in = in.substring(1, in.length() - 1);
			scan = new Scanner(in).useDelimiter(",");
			while (scan.hasNext())
				terminals.add(scan.next());
			in = br.readLine();
			in = in.substring(1, in.length() - 1);
			scan = new Scanner(in).useDelimiter(",");
			while (scan.hasNext())
				states.add(scan.next());
			start = br.readLine();
			in = br.readLine();
			in = in.substring(1, in.length() - 1);
			scan = new Scanner(in).useDelimiter(",");
			while (scan.hasNext()) {
				accepted.add(scan.next());
			}
			
			transition = new String[states.size()][terminals.size()];
			
			while ((in = br.readLine()) != null) {
				String s = "";
				for (int i = 0; i < in.length(); i++) {
					if(in.charAt(i) == '-')
						s += ",";
					else if(in.charAt(i) == '>') {
						
					}
					else if (in.charAt(i) != '(' && in.charAt(i) != ')') {
						s += in.charAt(i);
					}
					
				}
				
				scan = new Scanner(s).useDelimiter(",");
				transition[states.indexOf(scan.next())][terminals.indexOf(scan.next())] = scan.next();
				
			}
			
			
			br.close();
			fr.close();
			
			
		}
		catch(IOException ex) {
			System.out.println("IOException!");
		}
		
	}
	
	public boolean test(String s) {
		String curr = start;
		for (int i = 0; i < s.length(); i++) {
			String term = String.valueOf(s.charAt(i));
			curr = transition[states.indexOf(curr)][terminals.indexOf(term)];
		}
		
		return accepted.contains(curr);
	}
	
	public static void main(String[] args) {
		String in = "";
		if (args.length == 1) {
		in = args[0];
		}
		else {
			System.out.println("Please enter a file name.");
			scan = new Scanner(System.in);
			in = scan.next();
		}
		Automaton auto = new Automaton(in);
		System.out.println("Now enter strings to test against the automaton.\nTo exit, type Ctrl+c");
		scan = new Scanner(System.in);
		while (true) {
			String s = scan.nextLine();
			boolean invalid = false;
			for(int i = 0; i < s.length();i++) {
				String c = String.valueOf(s.charAt(i));
				if(!terminals.contains(c)) {
					System.out.println(c + " is not an input terminal for this automaton.\nThe input terminals are as follows: " + terminals);
					invalid = true;
					break;
				}
			}
			
			if(invalid)
				continue;
			
			boolean accepted = auto.test(s);
			if (accepted)
				System.out.println("The string " + s + " was accepted!");
			else
				System.out.println("The string " + s + " was not accepted!");
		}
		
		
		
		
	}
}
