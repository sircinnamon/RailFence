public class RailFence
{
	//Encodes and decodes with a "Rail Fence" cipher
	//Dailyprogrammer intermediate challenge #196
	//java RailFence e 3 12345678901
	/*
	1   5   9
	 2 4 6 8 0
	  3   7   1
	*/
	public static void main(String[] args)
	{
		if(args.length < 3)
		{
			System.out.println("Invalid arguments. Try \"java Railfence [e/d] key plaintext\"");
		}
		else if(args[0].equals("e"))
		{
			int key = Integer.parseInt(args[1]);
			System.out.println(encode(key, args[2]));
		}
		else if(args[0].equals("d"))
		{
			int key = Integer.parseInt(args[1]);
			System.out.println(decode(key, args[2]));
		}
	}

	public static String encode(int key, String text)
	{
		String ciphertext = "";
		String[] lines = new String[key];
		for(int i = 0; i < key; i++){lines[i] = "";}
		int line = 0;
		int direction = 1; //moving positively or negatively
		for(int i=0; i<text.length(); i++)
		{
			lines[line] = lines[line]+text.charAt(i);
			line = line + direction;
			if(line == 0 || line == key-1)
			{
				direction = direction*(-1);
			}
		}
		for(String l : lines){ciphertext = ciphertext + l;}
		return ciphertext;
	}

	public static String decode(int key, String text)
	{
		char[] plaintext = new char[text.length()];
		String[] lines = splitLines(key, text.length());

		for(int i = 0; i < key; i++)
		{
			//System.out.println("#" + i + " : " + lines[i]);
			int startbreak = 0;
			for(int j = 0; j < i; j++)
			{
				startbreak = startbreak+lines[j].length();
			}
			int endbreak = startbreak + lines[i].length();
			lines[i] = text.substring(startbreak, endbreak);
			//System.out.println(startbreak + " to " + endbreak);
			//System.out.println("#" + i + " : " + lines[i]);
		}

		int line = 0;
		int direction = 1; //moving positively or negatively
		int[] pos = new int[lines.length];
		for(int i=0; i<pos.length;i++){pos[i] = 0;}
		for(int i=0; i<text.length(); i++)
		{
			plaintext[i] = lines[line].charAt(pos[line]);
			pos[line]++;
			line = line + direction;
			if(line == 0 || line == key-1)
			{
				direction = direction*(-1);
			}
		}

		return new String(plaintext);
	}

	public static String[] splitLines(int key, int length)
	{
		String[] lines = new String[key];
		for(int i = 0; i < key; i++){lines[i] = "";}
		int line = 0;
		int direction = 1; //moving positively or negatively
		for(int i=0; i<length; i++)
		{
			lines[line] = lines[line]+"?";
			line = line + direction;
			if(line == 0 || line == key-1)
			{
				direction = direction*(-1);
			}
		}
		return lines;
	}
}