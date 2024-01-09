package hello.hellospring.Service;

import hello.hellospring.Domain.Member;
import hello.hellospring.Repository.MemberRepository;
import hello.hellospring.Repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    // memberService 입장에서 DI. 의존성 주입
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /*
    * 회원가입
    */
    public Long join(Member member){
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        /*
         * Optional<Member> result = memberRepository.findByName(member.getName()); --> Optional 바로 반환 권장하지 않음
         * result.ifPresent(m -> {
         *     throw new IllegalArgumentException("이미 존재하는 회원입니다.");
         * });
         */

        // 리턴값이 optional이기에 바로 ifPresent 사용 가능
        // findByName 뒤에 쭉 나오기에 메소드로 뽑아서 사용하는 것이 좋음
        memberRepository.findByName(member.getName())
                .ifPresent(m->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });

        /*
         * ifPersent: null이 아니라 이미 값이 있다면 동작을 하겠다.
         * optional이기에 가능한 것.
         * null일 가능성이 있다면 Optional로 한번 감싸서 반환을 하여 사용하는 것이 좋음
         * 또는 result.orElseGet(): 값을 가져오거나 없으면 이 동작을 해라
         */
    }

    /*
    * 전체 회원 조회
    */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /*
     * id로 회원 조회
     */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
