package com.choicemaker.xmlencryption.app;

import static com.choicemaker.xmlencryption.AwsKmsUtils.DEFAULT_AWS_KEY_ENCRYPTION_ALGORITHM;

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
import com.choicemaker.xmlencryption.DocumentEncryptor;
import com.choicemaker.xmlencryption.EncryptedKeyFactory;
import com.choicemaker.xmlencryption.SecretKeyInfoFactory;

public class EncryptionApp {

	public static int encrypt(String[] args) throws Exception {

		// Create a document encryptor
		EncryptionParameters params = EncryptionCommandLine
				.parseCommandLine(args);
		AWSCredentials creds = new BasicAWSCredentials(
				params.getAwsAccessKey(), params.getAwsSecretkey());
		SecretKeyInfoFactory skif = new SecretKeyInfoFactory(
				params.getAwsMasterKeyId(),
				DEFAULT_AWS_KEY_ENCRYPTION_ALGORITHM, params.getAwsEndpoint(),
				creds);
		EncryptedKeyFactory ekf = new EncryptedKeyFactory();
		final DocumentEncryptor encryptor = new DocumentEncryptor(skif, ekf);

		// Read the input
		InputStream sourceDocument;
		if (params.getInputFile() == null) {
			sourceDocument = System.in;
		} else {
			sourceDocument = new FileInputStream(params.getInputFile());
		}
		DocumentBuilder builder = XMLUtils.createDocumentBuilder(false);
		Document doc = builder.parse(sourceDocument);

		// Encrypt the input
		encryptor.encrypt(doc);

		// Output the result
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(System.out);
		transformer.transform(source, result);

		return CommandLineUtils.EXIT_SUCCESS;
	}

	public static void main(String[] args) throws Exception {
		int exitCode = encrypt(args);
		System.exit(exitCode);
	}

}
