package rawg.shell.commands;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * Spring Shell system commands.
 * 
 * The Property resolver loads the generated git.properties file upon application startup.
 */
@ShellComponent(value = "System")
public class VersionCommands {
    @Value("${git.branch}")
    private String branch;

    @Value("${git.commit.id.full}")
    private String commitId;

    @Value("${git.build.time}")
    private String buildTime;

    /**
     * Shows build version information, etc.  
     */
	@ShellMethod(key = "version", value = "Retrieve program version information")
    public void showVersion() {
		System.out.println("Built on " + buildTime + " with Git " + commitId + " on branch " + branch);
    }
}
