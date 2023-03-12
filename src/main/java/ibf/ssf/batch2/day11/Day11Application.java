package ibf.ssf.batch2.day11;

import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Day11Application {

	//Configures port number (REFER to day11workshop instructions)
	public static void main(String[] args) {
		//Boilerplate code provided by SpringBoot:
		//SpringApplication.run(Day11Application.class, args);
	
		//instantiate springapplication to load Day11Application.class
		SpringApplication app = new SpringApplication(Day11Application.class);

		//logs info 
		Logger logger = Logger.getLogger(Day11Application.class.getName());

		//declare port number
		String port = "3000";

		//ApplicationArguments provides access to command line arguments within a SpringBoot applicatiom
		//parse the command line arguments within args
		ApplicationArguments cliOpts = new DefaultApplicationArguments(args);

		//if command line arguments contains option "port"
		if (cliOpts.containsOption("port")){
			//get the value of port from the first index
			port = cliOpts.getOptionValues("port").get(0);
			app.setDefaultProperties(Collections.singletonMap("server.port", port));
			logger.log(Level.INFO, ">>>Application started on argument line port: port %s\n".formatted(port));

			//to run from terminal:
			// hoshiyun@Hos-MacBook-Air day11-pm % 
			//mvn spring-boot:run -Dspring-boot.run.arguments="--port=6000 --logLevel=TRACE"
		};
		
		if (args.length == 0){
			logger.log(Level.WARNING, "No port has been indicated by the user. Programme will now proceed to check if system environmental SERVER_PORT exists.");
			String defaultPort = System.getenv("SERVER_PORT"); 
			if (defaultPort != null){
					// set default properties for app to have the value set by command 
					//if system environment has already set the port number 
						//To do this: export SERVER_PORT=2000;
				//logger info that the port has started on port
				app.setDefaultProperties(Collections.singletonMap("server.port", defaultPort));
				logger.log(Level.INFO, ">>>Application started on environmental setting port: port %s\n".formatted(defaultPort));

			} 
			if (defaultPort == null) {
				logger.log(Level.WARNING, "No system environment variable has been detected. Programme will now set default port.");
				app.setDefaultProperties(Collections.singletonMap("server.port", port));
				logger.log(Level.INFO, ">>>Application started on default port configured by programe port: port %s\n".formatted(port));
			}
		}
		
		app.run(args);

			
	}
}


