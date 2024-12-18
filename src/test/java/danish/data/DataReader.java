package danish.data;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import java.util.HashMap;
//import com.fasterxml.jackson.databind.JsonProcessingException;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

public class DataReader {
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		//read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
		//String to HashMap - need to download Jackson Databind jars
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	
	}
}
