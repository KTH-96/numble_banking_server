package com.numble.banking.common.utils;

import static com.numble.banking.exception.ErrorCode.NOT_FIND_MEMBER;
import static com.numble.banking.exception.ErrorCode.NOT_LOGIN_STATUS;
import static com.numble.banking.exception.ErrorCode.NOT_WEB_REQUEST_EXCEPTION;

import com.numble.banking.exception.ApplicationException;
import com.numble.banking.exception.NotRefreshTokenException;
import com.numble.banking.exception.WebRequestException;
import com.numble.banking.member.Member;
import com.numble.banking.member.member.exception.NotFindMemberException;
import com.numble.banking.member.member.repository.MemberRepository;
import com.numble.banking.member.oauth.LoginOauthMember;
import com.numble.banking.member.oauth.service.JwtProvider;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {

	private final JwtProvider jwtProvider;
	private final MemberRepository memberRepository;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		boolean hasLoginAnnotation = parameter.hasParameterAnnotation(LoginOauth.class);
		boolean hasLoginMemberType = LoginOauthMember.class.isAssignableFrom(parameter.getParameterType());
		return hasLoginMemberType && hasLoginAnnotation;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		HttpServletRequest request = Optional.ofNullable(webRequest.getNativeRequest(HttpServletRequest.class))
			.orElseThrow(() -> new WebRequestException(NOT_WEB_REQUEST_EXCEPTION));
		//클라이언트와 상의후 결정
		//애플리케이션 테스트를 위해 쿠키로 바로 사용
		String refreshToken = Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
			.orElseThrow(() -> new NotRefreshTokenException(NOT_LOGIN_STATUS));
		Long id = jwtProvider.decodeToken(refreshToken);

		Member member = memberRepository.findById(id)
			.orElseThrow(() -> new NotFindMemberException(NOT_FIND_MEMBER));

		return LoginOauthMember.from(member.getId());
	}
}
