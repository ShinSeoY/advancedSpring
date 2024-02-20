package com.sandy.advancedSpring.common.config.batch;

import com.sandy.advancedSpring.domain.member.MyUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class BatchJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;


    @Bean
    Job delMemberJob() {
        return jobBuilderFactory.get("delMemberJob")
                .start(delMemberStartStep())
                .next(decider()).on("DELETED").to(delMemberStep()).on("*").to(delMemberNextStep()).next(delMemberEndStep())
                .from(decider()).on("*").to(delMemberNextStep()).next(delMemberEndStep())
                .end().build();
    }

    @Bean
    public Step delMemberStartStep() {
        return stepBuilderFactory.get("delMemberStartStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info("----- delMemberStartStep");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step delMemberStep() {
        return stepBuilderFactory.get("delMemberStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info("----- delMemberStep");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step delMemberNextStep() {
        return stepBuilderFactory.get("delMemberNextStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info("----- delMemberNextStep");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step delMemberEndStep() {
        return stepBuilderFactory.get("delMemberEndStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info("----- delMemberEndStep");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public JobExecutionDecider decider() {
        return new MemTypeDecider();
    }

    public static class MemTypeDecider implements JobExecutionDecider {
        @Override
        public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
            String username = "del_member";
//            String username = "stop_member";
//            String username = "use_member";

            MyUser myUser = MyUser.builder()
                    .username(username)
                    .password("1234")
                    .build();

            if (myUser.getUsername().equals("del_member")) {
                return new FlowExecutionStatus("DELETED");
            } else if ((myUser.getUsername().equals("stop_member"))) {
                return new FlowExecutionStatus("STOPPED");
            } else {
                return new FlowExecutionStatus("USING");
            }
        }
    }

    @Bean
    public Job miracleMorning() {
        return jobBuilderFactory.get("miracleMorning")
                .start(wakeUp())
                .on("COMPLETED")
                .to(drinkCoffee())
                .on("*")
                .to(exercise())
                .on("*")
                .end()  // job의 재시작을 막음

                .from(wakeUp())
                .on("*")
                .to(drinkWater())
                .on("*")
                .to(exercise())
                .on("*")
                .fail()    // job의 재시작을 막지 않음
                .end().build();
    }

    @Bean
    public Step wakeUp() {
        return stepBuilderFactory.get("wakeUp")
                .tasklet((contribution, chunkContext) -> {
                    log.info("----- wakeUp");

                    String result = "COMPLETED";

                    //Flow에서 on은 RepeatStatus가 아닌 ExitStatus를 바라본다.
                    if (result.equals("COMPLETED"))
                        contribution.setExitStatus(ExitStatus.COMPLETED);
                    else if (result.equals("FAIL"))
                        contribution.setExitStatus(ExitStatus.FAILED);
                    else if (result.equals("UNKNOWN"))
                        contribution.setExitStatus(ExitStatus.UNKNOWN);

                    return RepeatStatus.FINISHED;
                })
                .allowStartIfComplete(true) // COMPLETED로 끝난 STEP도 재실행 대상에 포함시켜줌 (원래 job이 한번 completed되면 재실행이 안됨)
                .build();
    }

    @Bean
    public Step drinkCoffee() {
        return stepBuilderFactory.get("drinkCoffee")
                .tasklet((contribution, chunkContext) -> {
                    log.info("----- drinkCoffee");
                    return RepeatStatus.FINISHED;
                })
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Step drinkWater() {
        return stepBuilderFactory.get("drinkWater")
                .tasklet((contribution, chunkContext) -> {
                    log.info("----- drinkWater");
                    return RepeatStatus.FINISHED;
                })
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Step exercise() {
        return stepBuilderFactory.get("exercise")
                .tasklet((contribution, chunkContext) -> {
                    log.info("----- exercise");
                    return RepeatStatus.FINISHED;
                })
                .allowStartIfComplete(true)
                .build();
    }


    @Bean
    public Job ExampleJob() {

        // .from(startStep()) : 이전 스텝(여기서는 startStep())의 상태를 지정해줌
        // .to(processStep()) : 다음 스텝(여기서는 processStep())의 상태를 지정해줌

        Job exampleJob = jobBuilderFactory.get("exampleJob")
                .start(startStep())
                .on("FAILED") //startStep의 ExitStatus가 FAILED일 경우
                .to(failOverStep()) //failOver Step을 실행 시킨다.
                .on("*") //failOver Step의 결과와 상관없이
                .to(writeStep()) //write Step을 실행 시킨다.
                .on("*") //write Step의 결과와 상관없 이
                .end() //Flow를 종료시킨다.

                .from(startStep()) //startStep이 FAILED가 아니고
                .on("COMPLETED") //COMPLETED일 경우
                .to(processStep()) //process Step을 실행 시킨다
                .on("*") //process Step의 결과와 상관없이
                .to(writeStep()) // write Step을 실행 시킨다.
                .on("*") //wrtie Step의 결과와 상관없이
                .end() //Flow를 종료 시킨다.

                .from(startStep()) //startStep의 결과가 FAILED, COMPLETED가 아닌
                .on("*") //모든 경우
                .to(writeStep()) //write Step을 실행시킨다.
                .on("*") //write Step의 결과와 상관없이
                .end() //Flow를 종료시킨다.
                .end()
                .build();

        return exampleJob;
    }

    @Bean
    public Step startStep() {
        return stepBuilderFactory.get("startStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info("Start Step!");

                    String result = "COMPLETED";
//                    String result = "FAIL";
                    //String result = "UNKNOWN";

                    //Flow에서 on은 RepeatStatus가 아닌 ExitStatus를 바라본다.
                    if (result.equals("COMPLETED"))
                        contribution.setExitStatus(ExitStatus.COMPLETED);
                    else if (result.equals("FAIL"))
                        contribution.setExitStatus(ExitStatus.FAILED);
                    else if (result.equals("UNKNOWN"))
                        contribution.setExitStatus(ExitStatus.UNKNOWN);

                    return RepeatStatus.FINISHED;
                })
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Step failOverStep() {
        return stepBuilderFactory.get("nextStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info("FailOver Step!");
                    return RepeatStatus.FINISHED;
                })
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Step processStep() {
        return stepBuilderFactory.get("processStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info("Process Step!");
                    return RepeatStatus.FINISHED;
                })
                .allowStartIfComplete(true)
                .build();
    }


    @Bean
    public Step writeStep() {
        return stepBuilderFactory.get("writeStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info("Write Step!");
                    return RepeatStatus.FINISHED;
                })
                .allowStartIfComplete(true)
                .build();
    }
}
