package com.choicemaker.xmlencryption.app;

public class Main {

	public static final String VERB_ENCRYPT = "encrypt";
	public static final String VERB_DECRYPT = "decrypt";
	public static final String VERB_HELP = "help";
	public static final String VERB_OPTIONS = "[" + VERB_HELP + "|"
			+ VERB_ENCRYPT + "|" + VERB_DECRYPT + "]";

	public static void main(String[] args) {

		int exitCode = CommandLineUtils.EXIT_UNKNOWN_ERROR;
		try {
			CommandLineComponents clc = new CommandLineComponents(args);

			if (clc.verb == null || clc.verb.isEmpty()) {
				final boolean isHelp = false;
				usage(isHelp, VERB_OPTIONS, clc.params);
				exitCode = CommandLineUtils.EXIT_VERB_ERROR;

			} else if (clc.verb.equalsIgnoreCase(VERB_HELP)) {
				final boolean isHelp = true;
				usage(isHelp, VERB_OPTIONS, clc.params);
				exitCode = CommandLineUtils.EXIT_SUCCESS;

			} else if (clc.verb.equalsIgnoreCase(VERB_ENCRYPT)) {
				exitCode = encrypt(clc.params);

			} else if (clc.verb.equalsIgnoreCase(VERB_DECRYPT)) {
				exitCode = decrypt(clc.params);

			} else {
				final boolean isHelp = false;
				usage(isHelp, VERB_OPTIONS, clc.params);
				exitCode = CommandLineUtils.EXIT_VERB_ERROR;

			}

		} catch(Throwable t) {
			t.printStackTrace();

		} finally {
			System.exit(exitCode);
		}

	}

	protected static void usage(boolean isHelp, String verb, String[] params) {
		String appInvocation = "java -jar santuario-kms-app.jar " + verb;
		Usage.usage(isHelp, appInvocation, params);
	}

	public static int encrypt(String[] params) {
		return EncryptionApp.encrypt(params);
	}

	public static int decrypt(String[] params) {
		return DecryptionApp.decrypt(params);
	}

}
