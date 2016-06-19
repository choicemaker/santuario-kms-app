package com.choicemaker.xmlencryption.app;

public class Main {

	public static final String VERB_ENCRYPT = "encrypt";
	public static final String VERB_DECRYPT = "decrypt";
	public static final String VERB_HELP = "help";

	public static void main(String[] args) {

		int exitCode = CommandLineUtils.EXIT_UNKNOWN_ERROR;
		try {
			CommandLineComponents clc = new CommandLineComponents(args);

				if (clc.verb == null || clc.verb.isEmpty()) {
					usage(clc.params);
					exitCode = CommandLineUtils.EXIT_VERB_ERROR;

				} else if (clc.verb.equals(VERB_HELP)) {
					exitCode = usage(clc.params);

				} else if (clc.verb.equalsIgnoreCase(VERB_ENCRYPT)) {
					exitCode = encrypt(clc.params);

				} else if (clc.verb.equalsIgnoreCase(VERB_ENCRYPT)) {
					exitCode = decrypt(clc.params);

				} else {
					usage(clc.params);
					exitCode = CommandLineUtils.EXIT_VERB_ERROR;

				}

		} finally {
			System.exit(exitCode);
		}

	}

	public static int usage(String[] params) {
		return UsageApp.usage(params);
	}

	public static int encrypt(String[] params) {
		return EncryptionApp.encrypt(params);
	}

	public static int decrypt(String[] params) {
		return DecryptionApp.decrypt(params);
	}

}
