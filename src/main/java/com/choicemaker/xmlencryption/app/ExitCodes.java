/*
 * Copyright (c) 2016 ChoiceMaker LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     ChoiceMaker LLC - initial API and implementation
 */
package com.choicemaker.xmlencryption.app;

import com.choicemaker.xmlencryption.ErrorCodes;

/**
 * Defines exit codes for command line applications. For consistency with
 * reserved Bash exit codes, application-specific error and exit codes are
 * defined in the range 64 to 113, inclusive.
 *
 * @author rphall
 */
public interface ExitCodes {

	int EXIT_SUCCESS = ErrorCodes.NO_ERRORS;
	int EXIT_VERB_ERROR = ErrorCodes.ERROR_VERB_ERROR;
	int EXIT_EXTRA_ARGS = ErrorCodes.ERROR_EXTRA_ARGS;
	int EXIT_MULTIPLE_ERRORS = ErrorCodes.MULTIPLE_ERRORS;
	int EXIT_NOT_YET_IMPLEMENTED = ErrorCodes.ERROR_NOT_YET_IMPLEMENTED;
	int EXIT_UNKNOWN_ERROR = ErrorCodes.UNKNOWN_ERROR;

}
