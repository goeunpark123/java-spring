package com.ohgiraffers.userservice.service;

import com.ohgiraffers.userservice.aggregate.UserEntity;
import com.ohgiraffers.userservice.client.OrderServiceClient;
import com.ohgiraffers.userservice.dto.UserDTO;
import com.ohgiraffers.userservice.repository.UserRepository;
import com.ohgiraffers.userservice.vo.ResponseOrder;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /*설명. FeignClient 이후 추가할 부분*/
    private OrderServiceClient orderServiceClient;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder, OrderServiceClient orderServiceClient) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.orderServiceClient = orderServiceClient;
    }

    @Transactional
    @Override
    public void registUser(UserDTO userDTO) {

        /*설명. userId가 비어있는 상태인데 UUID를 활용하여 서버 측에서 회원의 고유한 아이디 생성*/
        userDTO.setUserId(UUID.randomUUID().toString());

        /*설명. 필드값이 정확히 일치할 때만 매칭하기 위해 STRICT 모드 상태로 modelMapper 설정(STANDARD -> STRICT)*/
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);

        /*설명. spring security 모듈 추가 후 진행하므로 security 설정도 추가*/
//        userEntity.setEncryptedPwd("암호화된 비밀번호");
        userEntity.setEncryptedPwd(bCryptPasswordEncoder.encode(userDTO.getPwd()));

        userRepository.save(userEntity);
    }

    @Override
    public UserDTO getUserDetailsByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);

        if(userEntity == null)
            throw new UsernameNotFoundException(email + "아이디의 유저는 존재하지 않음");

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);

        return userDTO;
    }

    /*설명. UserDetailService 인터페이스 상속 이후 DB에서 로그인 사용자 정보 조회 후 UserDetails 타입으로 반환하는 기능 구현*/
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByEmail(email);

        if(userEntity == null) throw new UsernameNotFoundException(email + "아이디의 유저는 존재하지 않음");

        return new User(userEntity.getEmail(), userEntity.getEncryptedPwd(),
                true, true, true, true,
                new ArrayList<>());
    }

    @Override
    public UserDTO getUserById(String id) {
        UserEntity userEntity = userRepository.findById(Long.valueOf(id)).orElseThrow(() -> {
            return new UsernameNotFoundException("조회된 회원 없음");
        });

        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);

        /*설명. FeignClient를 통한 통신 시 추가*/
        List<ResponseOrder> orderList = orderServiceClient.getUserOrders(id);

        userDTO.setOrders(orderList);

        return userDTO;
    }
}
