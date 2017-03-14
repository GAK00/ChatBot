package chat.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

import chat.controller.ChatController;

public class FileController
{
	private String directory;
	private String fileSeparator;

	public FileController()
	{
		directory = "";
		fileSeparator = System.getProperty("file.separator");
	}

	public static void saveFile(ChatController baseController, String fileName, String contents)
	{
		File saveFile;
		if (fileName.length() > 5)
		{
			if (fileName.substring(fileName.length() - 4).equals(".txt")||fileName.substring(fileName.length() - 11).equals(".properties"))
			{
				saveFile = new File(fileName);
			} else
			{
				saveFile = new File(fileName + ".txt");
			}
		} else
		{
			saveFile = new File("derp derp derp.txt");
		}
		try
		{
			FileWriter saveFileWriter = new FileWriter(saveFile);
			saveFileWriter.write(contents);
			saveFileWriter.close();
		} catch (IOException e)
		{
			baseController.handleErrors(e);
		}
	}

	public static String readFile(ChatController baseController, String fileName)
	{
		String fileContenets = "";
		File readFile;
		if (fileName.substring(fileName.length() - 4).equals(".txt")||fileName.substring(fileName.length() - 11).equals(".properties"))
		{
			readFile = new File(fileName);
		} else
		{
			readFile = new File(fileName + ".txt");
		}
		try
		{
			Scanner fileReader = new Scanner(readFile);
			while (fileReader.hasNextLine())
			{
				fileContenets += "\n"+fileReader.nextLine();
			}
		} catch (IOException e)
		{
			baseController.handleErrors(e);

		} catch (NullPointerException e)
		{
			baseController.handleErrors(e);
		}

		return fileContenets;
	}

	public ArrayList<String> getData(String name)
	{
		ArrayList<String> data = null;
		Path path = Paths.get(directory + fileSeparator + name);
		if (Files.exists(path))
		{
			try
			{
				data = (ArrayList<String>) Files.readAllLines(path);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return data;
	}

	public String getRawData(String name)
	{
		String data = "";
		Path path = Paths.get(directory + fileSeparator + name);
		if (Files.exists(path))
		{
			try
			{
				data = new String(Files.readAllBytes(path), "UTF-8");
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return data;
	}

	public boolean createFile(byte data[], String name)
	{
		boolean success = true;
		Path path = Paths.get(directory + fileSeparator + name);
		try
		{
			Files.write(path, data, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return success;
	}

	/**
	 * warning if the program crahes chance of perma lock
	 * 
	 * @return success sate
	 */
	public void lock()
	{
		Path path = Paths.get(directory + fileSeparator + "Locked.txt");
		try
		{
			Files.write(path, "Locked".getBytes(), StandardOpenOption.CREATE);
			File lockFile = new File(directory + fileSeparator + "Locked.txt");
			lockFile.deleteOnExit();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void unlock()
	{
		try
		{
			File lockFile = new File(directory + fileSeparator + "Locked.txt");
			if (lockFile.exists())
			{
				lockFile.delete();
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public boolean setFileData(byte data[], String name)
	{
		boolean success = true;
		Path path = Paths.get(directory + fileSeparator + name);
		if (!Files.exists(path))
		{
			try
			{
				if (!path.toFile().createNewFile())
				{
					System.out.println("Error Data Cannot be saved");
					success = false;
				}
			} catch (IOException e)
			{
				e.printStackTrace();
				success = false;
			}
		}
		try
		{
			Files.write(path, data);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return success;
	}

	public boolean makeDirectory(String name)
	{

		boolean success = true;
		try
		{
			String path = getParentDirectory();

			directory = path + fileSeparator + name + fileSeparator;
			File newDirectory = new File(directory);
			Path testPath = Paths.get(directory);
			if (!newDirectory.mkdir() && !Files.exists(testPath))
			{
				success = false;
			}
		} catch (Exception exception)
		{
			exception.printStackTrace();
			success = false;
		}
		return success;

	}

	private String getParentDirectory() throws Exception
	{
		String parentDirectory = "";
		URL path = FileController.class.getProtectionDomain().getCodeSource().getLocation();
		String thisPath = URLDecoder.decode(path.getFile(), "UTF-8");
		parentDirectory = new File(thisPath).getParentFile().getPath();
		return parentDirectory;

	}

	public String getDirectory()
	{
		return directory;

	}

	public boolean getIsLocked()
	{
		boolean isLocked = false;
		if (Files.exists(Paths.get(directory + fileSeparator + "Locked.txt")))
		{
			isLocked = true;
		}
		return isLocked;
	}

}
