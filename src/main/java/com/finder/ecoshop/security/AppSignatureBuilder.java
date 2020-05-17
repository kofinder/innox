package com.finder.ecoshop.security;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class AppSignatureBuilder {

	private static final String HMAC_SHA_512 = "HmacSHA512";
	
	private static final byte DELIMITER = '\n';
	private String algorithm = HMAC_SHA_512;
	private String scheme;
	private String host;
	private String method;
	private String resource;
	private String nonce;
	private String apiKey;
	private byte[] apiSecret;
	private byte[] payload;
	private String timeStamp;
	private String contentType;

	public String getAlgorithm() {
		return HMAC_SHA_512;
	}

	public AppSignatureBuilder algorithm(String algorithm) {
		this.algorithm = algorithm;
		return this;
	}

	public AppSignatureBuilder scheme(String scheme) {
		this.scheme = scheme;
		return this;
	}

	public AppSignatureBuilder host(String host) {
		this.host = host;
		return this;
	}

	public AppSignatureBuilder apiKey(String key) {
		this.apiKey = key;
		return this;
	}

	public AppSignatureBuilder method(String method) {
		this.method = method;
		return this;
	}

	public AppSignatureBuilder resource(String resource) {
		this.resource = resource;
		return this;
	}

	public AppSignatureBuilder contentType(String contentType) {
		this.contentType = contentType;
		return this;
	}

	public AppSignatureBuilder timeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
		return this;
	}

	public AppSignatureBuilder nonce(String nonce) {
		this.nonce = nonce;
		return this;
	}

	public AppSignatureBuilder apiSecret(byte[] secretBytes) {
		this.apiSecret = secretBytes;
		return this;
	}

	public AppSignatureBuilder apiSecret(String secretString) {
		this.apiSecret = secretString.getBytes(StandardCharsets.UTF_8);
		return this;
	}

	public AppSignatureBuilder payload(byte[] payloadBytes) {
		this.payload = payloadBytes;
		return this;
	}

	public byte[] build() {
		
		Objects.requireNonNull(algorithm, "algorithm");
		Objects.requireNonNull(scheme, "scheme");
		Objects.requireNonNull(method, "method");
		Objects.requireNonNull(host, "host");
		Objects.requireNonNull(resource, "resource");
		Objects.requireNonNull(contentType, "contentType");
		Objects.requireNonNull(apiKey, "apiKey");
		Objects.requireNonNull(nonce, "nonce");
		Objects.requireNonNull(timeStamp, "timeStamp");
		Objects.requireNonNull(payload, "payload");
		try {
			final Mac digest = Mac.getInstance(algorithm);
			SecretKeySpec secretKey = new SecretKeySpec(apiSecret, algorithm);
			digest.init(secretKey);
			digest.update(method.getBytes(StandardCharsets.UTF_8));
			digest.update(DELIMITER);
			digest.update(scheme.getBytes(StandardCharsets.UTF_8));
			digest.update(DELIMITER);
			digest.update(host.getBytes(StandardCharsets.UTF_8));
			digest.update(DELIMITER);
			digest.update(resource.getBytes(StandardCharsets.UTF_8));
			digest.update(DELIMITER);
			digest.update(contentType.getBytes(StandardCharsets.UTF_8));
			digest.update(DELIMITER);
			digest.update(apiKey.getBytes(StandardCharsets.UTF_8));
			digest.update(DELIMITER);
			digest.update(nonce.getBytes(StandardCharsets.UTF_8));
			digest.update(DELIMITER);
			digest.update(timeStamp.getBytes(StandardCharsets.UTF_8));
			digest.update(DELIMITER);
			digest.update(payload);
			digest.update(DELIMITER);
			
			final byte[] signatureBytes = digest.doFinal();
			digest.reset();
			return signatureBytes;
		} catch (NoSuchAlgorithmException | NullPointerException | InvalidKeyException e) {
			throw new RuntimeException("Can't create signature: " + e.getMessage(), e);
		}
	}

	public boolean isHashEquals(byte[] expectedSignature) {
		final byte[] signature = build();
		return MessageDigest.isEqual(signature, expectedSignature);
	}

	public String buildAsHexString() {
		return DatatypeConverter.printHexBinary(build());
	}

	public String buildAsBase64String() {
		return DatatypeConverter.printBase64Binary(build());
	}
}
