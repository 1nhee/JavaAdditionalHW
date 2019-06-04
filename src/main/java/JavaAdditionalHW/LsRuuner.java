package JavaAdditionalHW;

import java.io.File;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class LsRuuner {

	String input_path;
	boolean list;
	boolean absolute;
	boolean lastModified;
	boolean size;
	boolean help;

	public static void main(String[] args) {

		Runner myRunner = new Runner();
		myRunner.run(args);

	}

	void run(String[] args) {
		Options options = createOptions();
		
		if(parseOptions(options, args)){
			if (help){
				printHelp(options);
				return;
			}
		}
			
			// path is required (necessary) data so no need to have a branch.
			System.out.println("This is result of 'p' option");
			System.out.println("You put this path: " + input_path);
			
			// TODO show the number of files in the path
			
			File file = new File(input_path);
		/*
		 * System.out.println(file.getAbsolutePath());
		 * System.out.println(file.getName());
		 * System.out.println(file.listFiles().length);
		 */
			
			//absolute
			if(absolute) {
				System.out.println("This is result of 'a' option");
				System.out.println("These are absolute pathes in the path");
				
				 for(int i = 0; i < file.listFiles().length; i++) {
					 System.out.println(file.getAbsolutePath());
				 } 
				 
				 System.out.println(" ");
				//list all file name
			}else if(list) {
				System.out.println("This is result of 'l' option");
				System.out.println("This is list of files in the path");
				
				for(int i = 0; i < file.listFiles().length; i++) {
					System.out.println(file.getName());
				}
				System.out.println(" ");
				
			}else if(lastModified) {
				System.out.println("This is result of 'm' option");
				System.out.println("These are the date that the file is modifieded lastly in the path");
				
				for(int i = 0; i < file.listFiles().length; i++) {
					System.out.println(file.lastModified());
				}
				
				System.out.println(" ");
			}else if(size) {
				System.out.println("This is result of 's' option");
				System.out.println("These are the size of files");
				
				for(int i = 0; i < file.listFiles().length; i++) {
					System.out.println(file.length());
				}
				System.out.println(" ");
			}
				
				System.out.println("Your program is terminated. Your program is done!");
		} 

	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);

			input_path = cmd.getOptionValue("i");
			list = cmd.hasOption("l");
			absolute = cmd.hasOption("a");
			lastModified = cmd.hasOption("m");
			size = cmd.hasOption("s");
			help = cmd.hasOption("h");

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
				options.addOption(Option.builder("i").longOpt("Input_path")
						.desc("Set a path of a directory or a file to display")
						.hasArg()
						.argName("Path name to display")
						.required()
						.build());
		
		// absolute path
		options.addOption(Option.builder("a").longOpt("absolute_path")
						.desc("display an abslute path of a directory or a file")
						.argName("absolute path name")
						.build());

		
		// list all
		options.addOption(Option.builder("l").longOpt("list")
						.desc("Display list of files in the directory")
						.argName("list")
						.build());
		
		// last modified
		options.addOption(Option.builder("m").longOpt("last_modified")
				.desc("Display the date the file is modified lastly")
				.argName("last modified")
				.build());
		
		// last modified
		options.addOption(Option.builder("s").longOpt("size")
				.desc("Display the size of files")
				.argName("size")
				.build()); 
				
		// add options by using OptionBuilder
		options.addOption(Option.builder("h").longOpt("help")
				        .desc("Help")
				        .build());
				
		return options;
	}
	
	private void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "ls CLI";
		String footer ="";
		formatter.printHelp("CLIExample", header, options, footer, true);
	}


}
