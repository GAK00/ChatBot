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
	private double percentComplete;
	private String percentPrefix;
	

	public CTECMedia(ChatController baseController)
	{
		this.baseController = baseController;
		filter = setupFilter();
		percentComplete = 0;
		percentPrefix = "";
	}

	
	
	public ChatController getBaseController()
	{
		return baseController;
	}

	public String getPercentComplete()
	{
		return percentPrefix +" "+DecimalFormat.getPercentInstance().format(percentComplete);
	}
	protected void setPrefix(String prefix)
	{
		this.percentPrefix = prefix;
	}
	protected void setPercentComplete(double percentComplete)
	{
		this.percentComplete = percentComplete;
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
		this.percentComplete= 0;
		this.percentPrefix = "Filtering";
		removeEmptyText(words);
		this.percentComplete = .2;
		int  size = words.size();
		for (int index = 0; index < words.size(); index++)
		{
			this.percentComplete = .2+ ((double)index/size)/1.25;
			for (int filterIndex = 0; filterIndex < filter.length; filterIndex++)
			{
			
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
		this.percentPrefix = "Sorting";
		this.percentComplete = 0;
		String popWord = "";
		List<String> removedStrings = new ArrayList<String>();
		List<Integer> counts = new ArrayList<Integer>();
		for (int index = 0; index < words.size(); index++)
		{
			words.set(index, words.get(index).toLowerCase());
		}

		int pos = 0;
		int size = words.size();
		while (pos < words.size())
		{
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
			this.percentComplete = ((double)pos/size)/1.1;
		}
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
						this.percentComplete = .9+ ((double)pos/size)/10;
					}
				}

				popWord = removedStrings.get(maxIndex) + " and has been used: " + counts.get(maxIndex) + " times" + "or "
						+ DecimalFormat.getPercentInstance().format((double) counts.get(maxIndex) / words.size());
				removedStrings.remove(maxIndex);
				counts.remove(maxIndex);
			}
		
		return popWord;
	}

	public String[] findMostPopular(int Number,List<String> words)
	{
		this.percentPrefix = "Sorting";
		this.percentComplete = 0;
		String[] popWords = new String[Number];
		List<String> removedStrings = new ArrayList<String>();
		List<Integer> counts = new ArrayList<Integer>();
		for (int index = 0; index < words.size(); index++)
		{
			words.set(index, words.get(index).toLowerCase());
		}

		int pos = 0;
		int size = words.size();
		while (pos < words.size())
		{
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
			this.percentComplete = ((double)pos/size)/1.5;
		}
		for (int index = 0; index < Number; index++)
		{
			this.percentComplete = 0.8 +( (double)index/Number)/5;
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
