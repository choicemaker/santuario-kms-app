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

import static com.choicemaker.xmlencryption.app.ExitCodes.EXIT_SUCCESS;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Document;

import com.choicemaker.xmlencryption.CredentialSet;
import com.choicemaker.xmlencryption.DocumentDecryptor;
import com.choicemaker.xmlencryption.EncryptionParameters;
import com.choicemaker.xmlencryption.EncryptionScheme;

public class DecryptionApp {

	public static final String DEFAULT_CREDENTIALSET_NAME = "default";

	public static int decrypt(String[] args) throws Exception {

		int retVal;
		EncryptionParameters params = EncryptionCommandLine
				.parseCommandLine(args);
		if (params.hasErrors()) {
			retVal = params.computeSummaryCode();

		} else {
			// Construct a decryptor
			final EncryptionScheme es = params.getEncryptionScheme();
			final CredentialSet cs = params.getCredentialSet();
			final DocumentDecryptor decryptor = new DocumentDecryptor(es, cs);

			// Read the input
			InputStream sourceDocument;
			if (params.getInputFile() == null) {
				sourceDocument = System.in;
			} else {
				sourceDocument = new FileInputStream(params.getInputFile());
			}
			DocumentBuilder builder = XMLUtils.createDocumentBuilder(false);
			Document doc = builder.parse(sourceDocument);

			// Decrypt the input
			decryptor.decrypt(doc);

			// Output the result
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(System.out);
			transformer.transform(source, result);

			retVal = EXIT_SUCCESS;
		}

		return retVal;
	}

	public static void main(String[] args) throws Exception {
		int exitCode = decrypt(args);
		System.exit(exitCode);
	}

}
