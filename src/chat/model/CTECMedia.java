package chat.model;

import java.text.DecimalFormat;
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

	public String findMostPopular(List<String> words)
	{
		List<String> removedStrings = new ArrayList<String>();
		List<Integer> counts = new ArrayList<Integer>();
		while (!words.isEmpty())
		{
			String currentSearch = words.get(0);
			int currentCount = 0;
			while (words.remove(currentSearch))
			{
				currentCount++;
			}
			removedStrings.add(currentSearch);
			counts.add(currentCount);
		}
		int maxIndex = 0;
		int maxValue = counts.get(0);
		for (int index = 1; index < counts.size(); index++)
		{
			if (counts.get(index) > maxValue)
			{
				maxValue = counts.get(index);
				maxIndex = index;
			}
		}
		return removedStrings.get(maxIndex) + " and has been used: " + counts.get(maxIndex) + " times" + "or "
				+ DecimalFormat.getPercentInstance().format((double) counts.get(maxIndex) / words.size());
	}

	public String[] findMostPopular(int Number,List<String> words)
	{
		String[] popWords = new String[Number];
		List<String> removedStrings = new ArrayList<String>();
		List<Integer> counts = new ArrayList<Integer>();
		for (int index = 0; index < words.size(); index++)
		{
			words.set(index, words.get(index).toLowerCase());
		}

		int pos = 0;
		while (pos < words.size())
		{

			// System.out.println(DecimalFormat.getPercentInstance().format((double)
			// pos / tweetedWords.size()));

			int count = 0;
			for (int index = 0; index < words.size(); index++)
			{
				if (words.get(index).equalsIgnoreCase(words.get(pos)))
				{
					count++;
				}

			}
			removedStrings.add(words.get(pos));
			counts.add(count);
			while (pos < words.size() && removedStrings.contains(words.get(pos)))
			{

				pos++;
			}
		}
		for (int index = 0; index < Number; index++)
		{
			if (removedStrings.size() > 0)
			{
				int maxIndex = 0;
				int maxValue = counts.get(0);
				for (int pos2 = 1; pos2 < counts.size(); pos2++)
				{
					if (counts.get(pos2) > maxValue)
					{
						maxValue = counts.get(pos2);
						maxIndex = pos2;
					}
				}

				popWords[index] = removedStrings.get(maxIndex) + " and has been used: " + counts.get(maxIndex) + " times" + "or "
						+ DecimalFormat.getPercentInstance().format((double) counts.get(maxIndex) / words.size());
				removedStrings.remove(maxIndex);
				counts.remove(maxIndex);
			}
		}
		return popWords;
	}

}
