package chat.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import chat.controller.ChatController;

public abstract class CTECMedia implements Interactable
{
	private ChatController baseController;
	private String[] filter;

	public CTECMedia(ChatController baseController)
	{
		this.baseController = baseController;
		filter = setupFilter();
	}

	public ChatController getBaseController()
	{
		return baseController;
	}

	public String[] getFilter()
	{
		return filter;
	}

	private String[] setupFilter()
	{
		int wordCount = 0;
		Scanner wordScanner = new Scanner(this.getClass().getResourceAsStream("Words.txt"));
		while (wordScanner.hasNextLine())
		{
			wordScanner.nextLine();
			wordCount++;
		}
		String[] boringWords = new String[wordCount];
		wordCount = 0;
		wordScanner = new Scanner(this.getClass().getResourceAsStream("Words.txt"));
		while (wordScanner.hasNextLine())
		{
			boringWords[wordCount] = wordScanner.nextLine();
			wordCount++;
		}
		wordScanner.close();
		return boringWords;
	}

	public void filter(List<String> words)
	{
		removeEmptyText(words);
		for (int index = 0; index < words.size(); index++)
		{
			for (int filterIndex = 0; filterIndex < filter.length; filterIndex++)
			{
				// System.out.println(filter[filterIndex]);
				if (words.get(index).equalsIgnoreCase(filter[filterIndex]))
				{
					// System.out.println("Removing" +filter[filterIndex]);
					words.remove(index);
					if (index != 0)
					{
						index--;
					}
				}
			}
		}
	}

	public boolean filter(String toFilter)
	{
		for (String word : filter)
		{
			if (toFilter.equalsIgnoreCase(word))
			{
				return true;
			}

		}
		return false;
	}

	public void removeEmptyText(List<String> words)
	{
		for (int index = 0; index < words.size(); index++)
		{
			if (words.get(index).trim().equals(""))
			{
				words.remove(index);
				index--;
			}
		}
	}

	public String removePunc(String word)
	{
		String punctuation = ".,'?!;:\"(){}^[]<>-@#";
		String skimed = word;
		for (int index = 0; index < punctuation.length(); index++)
		{

			while (skimed.contains(punctuation.substring(index, index + 1)))
			{
				skimed = skimed.substring(0, skimed.indexOf(punctuation.charAt(index)))
						+ skimed.substring(skimed.indexOf(punctuation.charAt(index)) + 1);
			}
		}
		return skimed;
	}

}
