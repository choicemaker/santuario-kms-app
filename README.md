# santuario-kms-app
Command-line application that encrypts or decrypts XML files using master keys managed by Amazon Web Services Key Management Service (AWS KMS).

Quickstart:

1. Download santuario-kms-app.jar

2. Create a managed AWS user

3. Create a managed AWS key

4. Create a santuario-kms.properties file

   ```
   aws.user.accessKey=<access key>
   aws.user.secretKey=<secret key>
   aws.kms.masterKey=<master key id>
   escrow.rsa.key=<rsa PKC#8 key file>
   ```

5. Encrypt an XML file

   ```bash
   java -jar santuario-kms-app.jar encrypt \
        -p santuario-kms.properties plaintext.xml > encrypted.xml
   ```

6. Decrypt an XML file

   ```bash
   java -jar santuario-kms-app.jar decrypt \
        -p santuario-kms.properties encrypted.xml > plaintext.xml 
   ```

References

1. [ChoiceMaker Santuario-KMS project][1]

[1]: https://github.com/choicemaker/santuario-kms

