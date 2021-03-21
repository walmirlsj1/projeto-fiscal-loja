package Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.PropertiesConfigurationLayout;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class Config {

	public static PropertiesConfiguration getConfiguracao() throws ConfigurationException, IOException {

		PropertiesConfiguration config = new PropertiesConfiguration();
		PropertiesConfigurationLayout layout = new PropertiesConfigurationLayout();
		config.setLayout(layout);

		File file = new File("config.properties");

		if (!file.exists()) {
			
			/* Define configuration basic for APP */
			file.createNewFile();
			config.setHeader("Configuracoes - Banco - etc");
			config.setProperty("db_host", "localhost");
			config.setProperty("db_local", "C:/Users/Ghost/eclipse-workspace/projeto-fiscal-loja/BANCO.FDB");
			config.setProperty("db_porta", "3050");
			config.setProperty("path_clientes", "C:\\GDOOR\\Backups\\");

			layout.save(config, new FileWriter(file));
		} else {
			layout.load(config, new InputStreamReader(new FileInputStream(file)));
		}

		return config;
	}
}
