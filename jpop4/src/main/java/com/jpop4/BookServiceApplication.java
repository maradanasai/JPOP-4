package com.jpop4;

import com.jpop4.config.BaseDbConfig;
import com.jpop4.config.BookConfig;
import com.jpop4.config.BookScreenConfig;
import com.jpop4.config.ScreenCommonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
		BookConfig.class,
		ScreenCommonConfig.class,
		BookScreenConfig.class,
		BaseDbConfig.class
})
public class BookServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookServiceApplication.class, args);
	}

}
