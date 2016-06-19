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

class CommandLineComponents {
	public final String verb;
	public final String[] params;

	public CommandLineComponents(String[] args) {
		if (args == null || args.length == 0) {
			this.verb = null;
			this.params = null;

		} else {
			this.verb = args[0] == null ? null : args[0].trim().toLowerCase();

			final int paramsCount = args[0] == null ? 0 : args.length - 1;
			assert paramsCount >= 0;
			this.params = new String[paramsCount];
			if (paramsCount > 0) {
				System.arraycopy(args, 1, params, 0, paramsCount);
			}

		}
	}
}
