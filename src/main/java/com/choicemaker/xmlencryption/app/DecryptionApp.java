package com.choicemaker.xmlencryption.app;

public class DecryptionApp {
	
	public static int decrypt(String[] params) {
		System.err.println("decrypt -- not yet implemented");
		return CommandLineUtils.EXIT_NOT_YET_IMPLEMENTED;
	}

	public static void main(String[] args) {
		int exitCode = decrypt(args);
		System.exit(exitCode);
	}

}
