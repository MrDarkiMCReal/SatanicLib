To apply this lib to your project you can use jitpack.io
make sure you put dependency of this lib under your main dependencys(for e.g. spigot-api) to avoid version problems
This lib provides:
- Localization
- ItemStack utils
- ItemStack deserealization from string
- SimpleRequirements with just returning true/false
- Requirements with localized messages with a reason what player exactly didnt have.
- Easy config creation and usage
- Some usefull methods like deserealization and hex color translation

See Wiki page to more info
work in progress..

	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>

	<dependency>
	    <groupId>com.github.MrDarkiMCReal</groupId>
	    <artifactId>SatanicLib</artifactId>
	    <version>1.0</version>
	</dependency>
