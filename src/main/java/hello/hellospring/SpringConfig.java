package hello.hellospring;

import hello.hellospring.Aop.TimeTraceAop;
import hello.hellospring.Repository.*;
import hello.hellospring.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {


    // 바로 autowaired하거나 생성자를 통해서 활용
    // @Autowired
//    private DataSource dataSource;

//    @Autowired
//    public SpringConfig(DataSource dataSource){ this.dataSource = dataSource;}

    /*
    * 원래 스펙에서는 이렇게 받아와야함
    * @PersistenceContext
    * private EntityManager em;
    * */

//    private EntityManager em;
//    @Autowired
//    public SpringConfig(EntityManager em){this.em = em;}


    // SpringDataJpa 사용 시
    private final MemberRepository memberRepository;
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }
//    @Bean
//    public MemberService memberService(){
//        return new MemberService(memberRepository());
//    }

    /*
    @Bean
    public MemberRepository memberRepository(){

//        return new MemoryMemberRepository();      // MemMoryRepository
//        return new JdbcMemberRepository(dataSource);  // JdbcRepository
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);

    }
     */

    /*
    * Aop 빈 설정
    */
//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }

}
