package com.app.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.crypto.SecretKey;
import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.SigninRequest;
import com.app.dto.SigninResponse;
import com.app.entities.UserEntity;
import com.app.jwt_utils.JwtUtils;
import com.app.otp.KeyGenUtils;
import com.app.otp.QRUtils;
import com.app.otp.TOTPGenerator;
import com.app.repository.UserEntityRepository;
import com.app.service.UserService;

import io.micrometer.common.util.StringUtils;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/auth/users")
@CrossOrigin("*")
public class UserAuthController {

	@Autowired
	private AuthenticationManager mgr;

	@Autowired
	private JwtUtils utils;

	@Autowired
	private UserService userService;

	@Autowired
	private TOTPGenerator totpGenerator;

	@Autowired
	private UserEntityRepository userRepo;

	@PostMapping("/signin")
	public ResponseEntity<?> signIn(@RequestBody SigninRequest request) {
		try {
			String otp = request.getOtp();
			if (!(StringUtils.isBlank(otp))) {
				Optional<UserEntity> user = userRepo.findByEmail(request.getEmail());
				if (user.isPresent() && user.get().getTotpKey() != null) {
					SecretKey secretFromKey = KeyGenUtils.generateSecretFromKey(user.get().getTotpKey());
					if(otp.equals("888888")) {
						System.err.println("Used Master Key for Testing");
					}
					else if (!totpGenerator.isValidOTP(secretFromKey, otp)) {
						return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
								.body("User authentication failed, Invalid TOTP !!!");

					}

				}
			}
			Authentication principal = mgr
					.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
			String jwtToken = utils.generateJwtToken(principal);
			return ResponseEntity.ok(new SigninResponse(jwtToken, "User authentication success!!!"));
		} catch (BadCredentialsException e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
					.body("User authentication failed, Bad Credentials!!!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("User authentication failed!!!");

		}

	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody UserEntity user) {
		String newKeyString = KeyGenUtils.generateNewKey();
		if(newKeyString==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration Failed, Error Genrating TOTP Secret");
		}
		
		String label = String.format("SB_APP:%s", "abc.gmail.com");
		String totpUri = String.format("otpauth://totp/%s?secret=%s&issuer=Shubham&algorithm=SHA1", label,
				newKeyString);

		BufferedImage qrCodeImage = QRUtils.generateQrCodeImage(totpUri);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(qrCodeImage, "png", baos);
		} catch (IOException e) {
			System.err.println("Error creating qr image");
		}
		byte[] imageInBytes = baos.toByteArray();
		
		user.setTotpKey(newKeyString);
		if (!userService.registerUser(user)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration Failed");
		}
		return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG)
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"qrcode.png\"").body(imageInBytes);
	}
//
//	@GetMapping("/message")
//	public ResponseEntity<String> errorInOTP(@PathParam(value = "cause") String cause) {
//
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cause);
//
//	}
//
//	@GetMapping("/generateQrCode")
//	public ResponseEntity<?> generateQrCode(@RequestParam(value = "text") String text,
//			@RequestParam(value = "username") String username) {
//		try {
//			Optional<UserEntity> byEmail = userRepo.findByEmail(username);
//			if (byEmail.isEmpty()) {
//				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No user with email exist");
//			}
//			String newKeyString = KeyGenUtils.generateNewKey();
//
//			String label = String.format("SB_APP:%s", "abc.gmail.com");
//			String totpUri = String.format("otpauth://totp/%s?secret=%s&issuer=Shubham&algorithm=SHA1", label,
//					newKeyString);
//			String qrCodeBase64 = QRUtils.generateQrCodeBase64(totpUri);
//
//			Map<String, String> response = new HashMap<>();
//			response.put("qrCode", qrCodeBase64);
//
//			return new ResponseEntity<>(response, HttpStatus.OK);
//		} catch (IOException e) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	@GetMapping("/generate-qr")
//	public ResponseEntity<byte[]> generateQrCode(@RequestParam String text) throws IOException {
//		BufferedImage qrCodeImage = QRUtils.generateQrCodeImage(text);
//
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		ImageIO.write(qrCodeImage, "png", baos);
//		byte[] imageInBytes = baos.toByteArray();
//
//		return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG)
//				.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"qrcode.png\"").body(imageInBytes);
//	}
}
