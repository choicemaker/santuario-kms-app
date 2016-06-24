package com.choicemaker.xmlencryption.app;

/**
 * The command line for the Main application consists of:
 * <ul>
 * <li>a verb (encrypt, decrypt or help)</li>
 * <li>an array of any additional String arguments that follow the verb</li>
 * </ul>
 * 
 * @author rphall
 */
public class CommandLineComponents {
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