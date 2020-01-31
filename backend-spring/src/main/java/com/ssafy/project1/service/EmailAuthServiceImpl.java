package com.ssafy.project1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.project1.dto.EmailAuthDTO;
import com.ssafy.project1.repository.EmailAuthRepository;
import com.ssafy.project1.util.mailAuth.MailService;
import com.ssafy.project1.util.mailAuth.MakeAuthKey;

@Service
public class EmailAuthServiceImpl implements EmailAuthService {

	@Autowired
	EmailAuthRepository emailAuthRepo;

	@Autowired
	MailService mailService;
	
	MakeAuthKey makeAuthKey;
	
	@Override
	public int auth_request(EmailAuthDTO dto) {
		// TODO Auto-generated method stub
		//필요없는 것들을 삭제
		emailAuthRepo.delete_useless();
		//인증 가능한지 확인
		int case1 = emailAuthRepo.exists_check_member(dto).success;
		int case2 = emailAuthRepo.exists_check_email_auth(dto).success;
		System.out.println(case1+" "+case2);
		if(case1==0 && case2==0) {
			//인증키 생성(랜덤함수로)
			makeAuthKey = new MakeAuthKey();
			makeAuthKey.makeKey();
			String key = makeAuthKey.getKey();
			System.out.println(key);
			dto.setAuth_key(key);
			//인증요청 db에 저장
			emailAuthRepo.insert(dto);
			//인증요청 email 발송 
			mailService.sendMail(dto.getEmail(), "project1의 인증 메일 입니다", "인증번호는 "+dto.getAuth_key()+" 입니다.");
			return 1;
		}else
			return -1;
	}

	@Override
	public int auth_request_re(EmailAuthDTO dto) {
		// TODO Auto-generated method stub
		//시간내로 재요청해야함
		//필요없는 것들을 삭제한다
		emailAuthRepo.delete_useless();
		//테이블에 있는지 확인
		EmailAuthDTO selectOne = emailAuthRepo.selectOne(dto);
		if(selectOne!=null) {
			//인증키를 다시 만든다
			makeAuthKey = new MakeAuthKey();
			makeAuthKey.makeKey();
			String key = makeAuthKey.getKey();
			dto.setAuth_key(key);
			//업데이트를 한다
			emailAuthRepo.update_key(dto);
			//인증요청 email 다시 발송
			mailService.sendMail(dto.getEmail(), "project1의 재인증 메일 입니다", "인증번호는 "+dto.getAuth_key()+" 입니다.");

			return 1;
		}else
			return -1;
	}

	@Override
	public int auth_check(EmailAuthDTO dto) {
		// TODO Auto-generated method stub
		//디비에 저장된 인증키와 시간을 비교한 후 성공시 db의 인증체크를 true로 바꾼다. 그리고 1을 반환
		EmailAuthDTO selectOneIdTime = emailAuthRepo.selectOneIdTime(dto);
		if(selectOneIdTime!=null) {
			emailAuthRepo.update_check(dto);
			return 1;
		}else
			return -1;
	}

	@Override
	public List<EmailAuthDTO> selectList() {
		// TODO Auto-generated method stub
		//인증 중인 리스트 반환
		return emailAuthRepo.selectList();
	}

}
