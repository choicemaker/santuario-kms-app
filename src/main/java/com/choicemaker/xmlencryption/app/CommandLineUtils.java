package com.choicemaker.xmlencryption.app;


/**
 * 
 * @author rphall
 */
public class CommandLineUtils {

	/*
	 * For consistency with reserved Bash exit codes, application-specific
	 * errors codes are defined in the range 64 to 113, inclusive.
	 */
	public static final int EXIT_SUCCESS = 0;
	public static final int EXIT_VERB_ERROR = 64;

	public static final int EXIT_NOT_YET_IMPLEMENTED = 112;
	public static final int EXIT_UNKNOWN_ERROR = 113;

	private CommandLineUtils() {
	}

}
