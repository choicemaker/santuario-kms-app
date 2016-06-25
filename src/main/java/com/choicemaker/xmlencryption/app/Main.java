package com.choicemaker.xmlencryption.app;

import static com.choicemaker.xmlencryption.ErrorCodes.EXIT_SUCCESS;
import static com.choicemaker.xmlencryption.ErrorCodes.EXIT_UNKNOWN_ERROR;
import static com.choicemaker.xmlencryption.ErrorCodes.EXIT_VERB_ERROR;

/**
 * The main application of the santuario-kms-app module. This application is run
 * from a command line and expects that the first command line argument will be
 * a 'verb' that specifies how to process the remaining arguments on the command
 * line. Valid verb values are:
 * <ul>
 * <li>
 * <code>encrypt -- further processing is delegated to the Encryption application</code>
 * </li>
 * <li>
 * <code>decrypt -- further processing is delegated to the Decryption application</code>
 * </li>
 * <li><code>help -- a usage message is displayed on standard out</code></li>
 * </ul>
 * If a valid verb is not specified, a usage message is displayed on standard
 * error.
 * <p>
 * After processing is complete, the application (or its delegate) exists with
 * an exit code defined by the ExitCodes interface.
 * </p>
 * <p>
 * For the <code>encrypt</code> and <code>decrypt</code> verbs, a least one
 * additional command line arguments is required after the verb:
 * <ul>
 * <li>-p &lt;properties file&gt;</li>
 * </ul>
 * The properties file specifies credentials used for encryption and decryption.
 * See the documentation for the santuario-kms project for more details.
 * </p>
 * <p>
 * If no other arguments are specified, the application will read input from
 * standard input. If one additional argument is specified, the argument is
 * treated as the name of a file to encrypt or decrypt. If two or more one
 * additional arguments are specified, processing terminates with an error of {.
 * </p>
 * <p>
 * If processing is successful, the exit code will be {@link EXIT_SUCCESS 0} and
 * output from the application will be displayed on standard out. If processing
 * is unsuccessful, the exit code will be greater than 0, in the range 64 to 113
 * inclusive, an error message will be displayed on standard error.
 * 
 * @author rphall
 */
public class Main {

	public static final String VERB_ENCRYPT = "encrypt";
	public static final String VERB_DECRYPT = "decrypt";
	public static final String VERB_HELP = "help";
	public static final String VERB_OPTIONS = "[" + VERB_HELP + "|"
			+ VERB_ENCRYPT + "|" + VERB_DECRYPT + "]";

	public static void main(String[] args) {

		int exitCode = EXIT_UNKNOWN_ERROR;
		try {
			CommandLineComponents clc = new CommandLineComponents(args);

			if (clc.verb == null || clc.verb.isEmpty()) {
				final boolean isHelp = false;
				usage(isHelp, VERB_OPTIONS, clc.params);
				exitCode = EXIT_VERB_ERROR;

			} else if (clc.verb.equalsIgnoreCase(VERB_HELP)) {
				final boolean isHelp = true;
				usage(isHelp, VERB_OPTIONS, clc.params);
				exitCode = EXIT_SUCCESS;

			} else if (clc.verb.equalsIgnoreCase(VERB_ENCRYPT)) {
				exitCode = encrypt(clc.params);

			} else if (clc.verb.equalsIgnoreCase(VERB_DECRYPT)) {
				exitCode = decrypt(clc.params);

			} else {
				final boolean isHelp = false;
				usage(isHelp, VERB_OPTIONS, clc.params);
				exitCode = EXIT_VERB_ERROR;

			}

		} catch (Throwable t) {
			t.printStackTrace();

		} finally {
			System.exit(exitCode);
		}

	}

	protected static void usage(boolean isHelp, String verb, String[] params) {
		String appInvocation = "java -jar santuario-kms-app.jar " + verb;
		Usage.usage(isHelp, appInvocation, params);
	}

	public static int encrypt(String[] params) throws Exception {
		return EncryptionApp.encrypt(params);
	}

	public static int decrypt(String[] params) throws Exception {
		return DecryptionApp.decrypt(params);
	}

}
