package com.choicemaker.xmlencryption.app;

public class EncryptionApp {
	
	public static int encrypt(String[] params) {
		System.err.println("encrypt -- not yet implemented");
		return CommandLineUtils.EXIT_NOT_YET_IMPLEMENTED;
	}

	public static void main(String[] args) {
		int exitCode = encrypt(args);
		System.exit(exitCode);
	}

}
