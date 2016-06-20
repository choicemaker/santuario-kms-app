/*******************************************************************************
 * Copyright (c) 2016 ChoiceMaker LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     ChoiceMaker LLC - initial API and implementation
 *******************************************************************************/
package com.choicemaker.xmlencryption.app;

//import java.io.File;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class EncryptionCommandLine {

	public static final String ARG_ENCRYPTION_PROPERTIES = "p";
	public static final String DESC_ENCRYPTION_PROPERTIES = "[REQUIRED] property file specifying encryption parameters";

	public static final String COMMAND_LINE = "LogPartitioner";

	public static Options createOptions() {
		final boolean hasArg = true;
		Options retVal = new Options();
		Option opt;

		opt = new Option(ARG_ENCRYPTION_PROPERTIES, hasArg,
				DESC_ENCRYPTION_PROPERTIES);
		opt.setRequired(false);
		retVal.addOption(opt);

		return retVal;
	}

	/**
	 * Parse the command line arguments into parameters for the encryption
	 * applications.
	 *
	 * @param args
	 *            non-null array of command-line arguments
	 * @return encryption or decryption parameters, or null if help is requested
	 *         or errors are detected.
	 * @throws ParseException
	 * @throws IOException
	 */
	public static EncryptionParameters parseCommandLine(String[] args) {
		return parseCommandLine(false, args);
	}

	/**
	 * Parse the command line arguments into parameters for the encryption
	 * applications.
	 *
	 * @params isHelp a flag indicating whether the command line is being parsed
	 *         a request for help
	 * @param args
	 *            non-null array of command-line arguments
	 * @return encryption or decryption parameters, or null if help is requested
	 *         or errors are detected.
	 * @throws ParseException
	 * @throws IOException
	 */
	public static EncryptionParameters parseCommandLine(final boolean isHelp,
			String[] args) {

		EncryptionParameters retVal;

		if (args == null || args.length == 0) {
			retVal = new EncryptionParameters();
			assert retVal.isHelp();

		} else {

			List<String> errors = new ArrayList<>();
			Properties props = null;
			File inputFile = null;

			Options options = createOptions();
			CommandLineParser parser = new BasicParser();
			CommandLine cl = null;
			try {
				cl = parser.parse(options, args);
			} catch (ParseException e) {
				String msg = "Unexpected error while parsing command line: "
						+ e.toString();
				errors.add(msg);
			}

			if (cl != null) {
				// Required properties file
				String propsFileName = cl
						.getOptionValue(ARG_ENCRYPTION_PROPERTIES);
				if (propsFileName != null) {
					propsFileName = propsFileName.trim();
				}
				if (propsFileName == null || propsFileName.isEmpty()) {
					errors.add(missingArgument(ARG_ENCRYPTION_PROPERTIES));
				} else {
					File f = new File(propsFileName);
					if (!f.exists() || !f.isFile()) {
						String msg = "Properties file '" + f.getAbsolutePath()
								+ "' does not exist or is not a file";
						errors.add(msg);
					} else {
						FileReader fr;
						try {
							fr = new FileReader(f);
							props = new Properties();
							props.load(fr);
						} catch (FileNotFoundException e) {
							String msg = "Unexpected error (does file '"
									+ propsFileName + "' exist?): "
									+ e.toString();
							errors.add(msg);
						} catch (IOException e) {
							String msg = "Unable to load encryption properties from '"
									+ propsFileName + "': " + e.toString();
							errors.add(msg);
						}
					}
				}

				// Optional input file (otherwise use standard input)
				String[] leftover = cl.getArgs();
				if (leftover.length > 0) {
					String inputFileName = leftover[0];
					File f = new File(inputFileName);
					if (!f.exists() || !f.isFile()) {
						String msg = "Input file '" + f.getAbsolutePath()
								+ "' does not exist or is not a file";
						errors.add(msg);
					} else {
						inputFile = f;
					}
				}

				// Check for extra, unexpected arguments
				if (leftover.length > 1) {
					final int extraCount = leftover.length - 1;
					assert extraCount > 0;
					String[] extra = new String[extraCount];
					System.arraycopy(leftover, 1, extra, 0, extraCount);
					StringBuilder sb = new StringBuilder();
					if (extraCount == 1) {
						sb.append("Extra argument: '").append(extra[0])
								.append("'");
					} else {
						sb.append("Extra arguments: '").append(extra[0])
								.append("', ...");
					}
					errors.add(sb.toString());
				}
			}

			assert errors != null;
			if (args.length == 1 && isHelp) {
				// Ignore errors if only help is requested
				List<String> empty = Collections.emptyList();
				retVal = new EncryptionParameters(isHelp, empty);

			} else if (!errors.isEmpty()) {
				retVal = new EncryptionParameters(isHelp, errors);

			} else {
				assert errors.isEmpty();
				assert props != null;
				retVal = new EncryptionParameters(isHelp, errors, props,
						inputFile);
			}

		}

		return retVal;
	}

	// public static String invalidArgument(String argName, String argValue) {
	// String retVal = "Invalid value ('" + argValue + "') for the '"
	// + argName + "' argument";
	// return retVal;
	// }

	public static String missingArgument(String argName) {
		String retVal = "Missing the required '" + argName + "' argument";
		return retVal;
	}

	// protected static final String OPTION_FLAG = "-";
	//
	// protected static final String MAGIC_DIVIDER = "__MAGIC_DIVIDER__";

	private EncryptionCommandLine() {
	}

}
