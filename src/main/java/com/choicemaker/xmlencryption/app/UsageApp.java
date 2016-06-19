package com.choicemaker.xmlencryption.app;

public class UsageApp {
	
	public static int usage(String[] params) {
		System.err.println("usage -- not yet implemented");
		return CommandLineUtils.EXIT_NOT_YET_IMPLEMENTED;
	}

	public static void main(String[] args) {
		int exitCode = usage(args);
		System.exit(exitCode);
	}

}
