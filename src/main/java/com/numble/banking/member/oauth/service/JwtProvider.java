package com.numble.banking.member.oauth.service;

import com.numble.banking.member.oauth.dto.JwtToken;
import com.numble.banking.member.oauth.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

	private final JwtProperties jwtProperties;
	private final SecretKey secretKey;

	public JwtProvider(JwtProperties jwtProperties) {
		this.jwtProperties = jwtProperties;
		this.secretKey = Keys.hmacShaKeyFor(jwtProperties.getSecretKey());
	}

	public JwtToken createToken(Long payload) {
		Date accessTokenExpiredDate = new Date(
			new Date().getTime() + jwtProperties.getAccessTokenExpiredMillisecond());
		Date refreshTokenExpiredDate = new Date(
			new Date().getTime() + jwtProperties.getRefreshTokenExpiredMillisecond());

		return new JwtToken(createJwtToken(payload, accessTokenExpiredDate),
			createJwtToken(payload, refreshTokenExpiredDate));
	}

	private String createJwtToken(Long payload, Date date) {
		return buildToken(payload, date);
	}

	private String buildToken(Long payload, Date date) {
		return Jwts.builder()
			.claim("id", payload)
			.setExpiration(date)
			.signWith(secretKey)
			.compact();
	}

	public Long decodeToken(String token) {
		Claims claims = Jwts.parserBuilder()
			.setSigningKey(jwtProperties.getSecretKey())
			.build()
			.parseClaimsJws(token)
			.getBody();

		return claims.get("id", Long.class);
	}
}
