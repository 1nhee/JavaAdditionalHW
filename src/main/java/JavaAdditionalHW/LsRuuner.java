package JavaAdditionalHW;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class LsRuuner {

	String input_path;
	boolean listUnSorted;
	boolean listSorted;
	boolean lastModified;
	boolean size;
	boolean help;

	public static void main(String[] args) {

		LsRuuner myRunner = new LsRuuner();
		myRunner.run(args);

	}

	void run(String[] args) {
		Options options = createOptions();

		if (parseOptions(options, args)) {
			if (help) {
				printHelp(options);
				return;
			}
		}

		// path is required (necessary) data so no need to have a branch.
		System.out.println("This is result of 'i' option");
		System.out.println("You put this path: " + input_path +"\n");

		// TODO show the number of files in the path

		File dirFile = new File(input_path);
		File[] fileList = dirFile.listFiles();
		ArrayList<String> fileNames = new ArrayList<String>();
		ArrayList<String> filePaths = new ArrayList<String>();
		
		for(File toGet: fileList) {
		  if(toGet.isFile()) {
		    String tempPath = toGet.getParent();
		    filePaths.add(tempPath);
		    
		    String tempFileName = toGet.getName();
		    fileNames.add(tempFileName);
		  }
		}

		if (listUnSorted) {
			System.out.println("\nThis is result of 'f' option");
			System.out.println("This is list of unsorted files in the path\n");
			System.out.println(" ");

			for (String toPrint : fileNames) {
				System.out.println(toPrint);
			}
			
			System.out.println(" ");

		} 
		
		if (listSorted) {
			System.out.println("\nThis is result of 'a' option");
			System.out.println("This is list of sorted files in the path\n");

			Collections.sort(fileNames);

	        for (String toCheck : fileNames) {
	        	System.out.println(toCheck);
	        }

			System.out.println(" ");

		} 
		
		if (lastModified) {
			System.out.println("\nThis is result of 'm' option");
			System.out.println("These are the date that the file is modifieded lastly in the path\n");

			ArrayList<Long> sortedTime = new ArrayList<Long>();
			
			for (File toCheck : fileList) {
				long currTimeModified = toCheck.lastModified();
				sortedTime.add(currTimeModified);
			}
			
			Collections.sort(sortedTime);

			for (long toCheckTime: sortedTime) {
				for (File toCheck : fileList) {
					long currTimeModified = toCheck.lastModified();
					if(toCheckTime == currTimeModified) {
						System.out.println(toCheck.getName());
					}
				}
			}
			
			System.out.println(" ");
		} 
		
		if (size) {
			System.out.println("\nThis is result of 't' option");
			System.out.println("These are the size of files\n");

			for (File toCheck : fileList) {
				System.out.println("The size of this file \'" + toCheck.getName() + "\' is " + toCheck.length());
			}
			System.out.println(" ");
		}

		System.out.println("\n\nYour program is terminated. Your program is done!\nIf you need information of the options, put H which is help option!");
	}

	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);

			input_path = cmd.getOptionValue("i");
			listUnSorted = cmd.hasOption("f");
			listSorted = cmd.hasOption("a");
			lastModified = cmd.hasOption("t");
			size = cmd.hasOption("h");
			help = cmd.hasOption("H");

		} catch (Exception e) {
			printHelp(options);
			return false;
		}

		return true;
	}

	// Definition Stage
	private Options createOptions() {
		Options options = new Options();

		// input
		options.addOption(
				Option.builder("i").longOpt("Input_path").desc("Set a path of a directory or a file to display")
						.hasArg().argName("Path name to display").required().build());

		// absolute path
		options.addOption(Option.builder("f").longOpt("information")
				.desc("Display list of unsorted files in the directory").argName("absolute path name").build());

		// list all
		options.addOption(Option.builder("a").longOpt("list").desc("Display list of sorted files in the directory")
				.argName("list").build());

		// last modified
		options.addOption(Option.builder("t").longOpt("last_modified")
				.desc("Display the date the file is modified lastly").argName("last modified").build());

		// last modified
		options.addOption(
				Option.builder("h").longOpt("size").desc("Display the size of files").argName("size").build());

		// add options by using OptionBuilder
		options.addOption(Option.builder("H").longOpt("Help").desc("Help").build());

		return options;
	}

	private void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "ls CLI";
		String footer = "";
		formatter.printHelp("CLIExample", header, options, footer, true);
	}

}

