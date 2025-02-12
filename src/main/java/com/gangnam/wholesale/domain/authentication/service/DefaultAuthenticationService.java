package com.gangnam.wholesale.domain.authentication.service;

import com.gangnam.wholesale.domain.authentication.dto.SignInDto;
import com.gangnam.wholesale.domain.authentication.dto.SignUpDto;
import com.gangnam.wholesale.domain.authentication.dto.ValidateRequestDto;
import com.gangnam.wholesale.domain.authentication.entity.Member;
import com.gangnam.wholesale.domain.authentication.exception.LoginFailureException;
import com.gangnam.wholesale.domain.authentication.exception.MemberNotFoundException;
import com.gangnam.wholesale.domain.authentication.repository.MemberRepository;
import com.gangnam.wholesale.global.entity.ValidateCode;
import com.gangnam.wholesale.global.error.exception.CodeNotFoundException;
import com.gangnam.wholesale.global.repository.ValidateCodeRepository;
import com.gangnam.wholesale.global.service.JwtProvider;
import com.gangnam.wholesale.global.util.CodeGeneratorUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultAuthenticationService implements AuthenticationService {

    private final ValidateCodeRepository validateCodeRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    @Transactional
    public SignUpDto.Response signUp(SignUpDto.Request request) {
        Member member = Member.newUser(request.getEmail(), this.passwordEncoder.encode(request.getPassword()));
        this.validateCodeRepository.save(ValidateCode.create(member.getEmail(), CodeGeneratorUtil.generate()));
        return SignUpDto.Response.newInstance(this.memberRepository.save(member));
    }

    @Override
    public SignInDto.Response signIn(SignInDto.Request request) {

        Member member = this.memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new MemberNotFoundException(request.getEmail()));

        if (this.passwordEncoder.matches(request.getPassword(), member.getPassword()) &&
                Boolean.TRUE.equals(member.getEnabled()))

            return SignInDto.Response
                    .newInstance(jwtProvider.createAccessToken(member), jwtProvider.createRefreshToken(member));

        throw new LoginFailureException();
    }

    @Override
    @Transactional
    public void validate(String email, ValidateRequestDto request) {
        ValidateCode code = this.validateCodeRepository.findByValidateCode(request.getCode())
                .orElseThrow(() -> new CodeNotFoundException(request.getCode()));

        Member member = this.memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException(email));

        if (member.getEmail().equals(code.getId())) member.enabled();
    }

}
