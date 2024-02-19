package com.sandy.advancedSpring.common.config.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
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
    public Job miracleMorning() {
        return jobBuilderFactory.get("miracleMorning")
                .start(wakeUp())
                .on("COMPLETED")
                .to(drinkCoffee())
                .on("*")
                .to(exercise())
                .on("*")
                .end()

                .from(wakeUp())
                .on("*")
                .to(drinkWater())
                .on("*")
                .to(exercise())
                .on("*")
                .end()
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
                .build();
    }

    @Bean
    public Step drinkCoffee() {
        return stepBuilderFactory.get("drinkCoffee")
                .tasklet((contribution, chunkContext) -> {
                    log.info("----- drinkCoffee");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step drinkWater() {
        return stepBuilderFactory.get("drinkWater")
                .tasklet((contribution, chunkContext) -> {
                    log.info("----- drinkWater");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step exercise() {
        return stepBuilderFactory.get("exercise")
                .tasklet((contribution, chunkContext) -> {
                    log.info("----- exercise");
                    return RepeatStatus.FINISHED;
                })
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
                    //String result = "FAIL";
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
                .build();
    }

    @Bean
    public Step failOverStep() {
        return stepBuilderFactory.get("nextStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info("FailOver Step!");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step processStep() {
        return stepBuilderFactory.get("processStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info("Process Step!");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }


    @Bean
    public Step writeStep() {
        return stepBuilderFactory.get("writeStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info("Write Step!");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}