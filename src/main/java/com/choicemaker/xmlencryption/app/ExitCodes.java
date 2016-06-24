package com.choicemaker.xmlencryption.app;

/**
 * Defines exit codes for the various command line applications. For consistency
 * with reserved Bash exit codes, application-specific errors codes are defined
 * in the range 64 to 113, inclusive.
 * 
 * @author rphall
 */
public interface ExitCodes {

	int EXIT_SUCCESS = 0;
	int EXIT_VERB_ERROR = 64;

	int EXIT_NOT_YET_IMPLEMENTED = 112;
	int EXIT_UNKNOWN_ERROR = 113;

}
