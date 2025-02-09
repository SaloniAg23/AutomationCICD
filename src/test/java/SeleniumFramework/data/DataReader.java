package SeleniumFramework.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	public List<HashMap<String, String>> getJsondataToMap(String FilePath) throws IOException
	{
		//Read json data as String value
		String jsonContent = FileUtils.readFileToString(new File(FilePath),
				StandardCharsets.UTF_8);

		//String to HashMap using Jackson databind
		
		ObjectMapper mapper = new ObjectMapper();
		
		//Below line tells how we have to return data(List) and in format data should be converted(HashMap)
		
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {});
		
		return data;
		
	}
	
	

}
