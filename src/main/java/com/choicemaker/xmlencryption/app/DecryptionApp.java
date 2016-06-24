package com.choicemaker.xmlencryption.app;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Document;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.choicemaker.xmlencryption.DocumentDecryptor;
import com.choicemaker.xmlencryption.EncryptionParameters;

public class DecryptionApp {

	public static int decrypt(String[] args) throws Exception {

		// Create a document decryptor
		EncryptionParameters params = EncryptionCommandLine
				.parseCommandLine(args);
		AWSCredentials creds = new BasicAWSCredentials(
				params.getAwsAccessKey(), params.getAwsSecretkey());
		final DocumentDecryptor decryptor = new DocumentDecryptor(
				params.getAwsEndpoint(), creds);

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

		return CommandLineUtils.EXIT_SUCCESS;
	}

	public static void main(String[] args) throws Exception {
		int exitCode = decrypt(args);
		System.exit(exitCode);
	}

}
