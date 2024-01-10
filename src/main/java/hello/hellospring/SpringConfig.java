package hello.hellospring;

import hello.hellospring.Repository.JdbcMemberRepository;
import hello.hellospring.Repository.MemberRepository;
import hello.hellospring.Repository.MemoryMemberRepository;
import hello.hellospring.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {


    // 바로 autowaired하거나 생성자를 통해서 활용
    // @Autowired
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource){ this.dataSource = dataSource;}


    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){

//        return new MemoryMemberRepository();      // MemMoryRepository
        return new JdbcMemberRepository(dataSource);  // JdbcRepository 사용
    }

}
