package com.choicemaker.xmlencryption.app;

import com.choicemaker.util.SystemPropertyUtils;

public class Usage {

	private static final String EOL = SystemPropertyUtils.PV_LINE_SEPARATOR;
	private static final String INDENT = "  ";

	protected static final String USAGE_MSG(String appInvocation) {
		String retVal = EOL
				+ "Usage: "
				+ EOL
				+ INDENT
				+ appInvocation
				+ " -p <encryption_properties> [<input_file>]"
				+ EOL
				+ EOL
				+ "where"
				+ EOL
				+ INDENT
				+ "* help    -- prints this message"
				+ EOL
				+ EOL
				+ INDENT
				+ "* encrypt -- encrypts the input"
				+ EOL
				+ EOL
				+ INDENT
				+ "* decrypt -- decrypts the input"
				+ EOL
				+ EOL
				+ INDENT
				+ "* encryption_properties is a file containing encryption or decryption properties"
				+ EOL
				+ EOL
				+ INDENT
				+ "* input_file is an optional file that will be encrypted or decrypted."
				+ EOL
				+ INDENT
				+ "  If no input file is specified, standard input will be used."
				+ EOL + EOL + "Results are streamed to standard output." + EOL
				+ EOL;
		return retVal;
	}

	public static void usage(final boolean isHelp, String appInvocation,
			String[] unused) {
		String msg = USAGE_MSG(appInvocation);
		if (isHelp) {
			System.out.println(msg);
		} else {
			System.err.println(msg);
		}
	}

}
