/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */

package javastory.club.stage1.step42.persist.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.StringTokenizer;

import javastory.club.stage1.step42.domain.TravelClub;
import javastory.club.stage1.step42.persist.TravelClubStore;

public class TravelClubFile implements TravelClubStore {
	//
	private String fileName;
	private String tempFileName; 
	private String delimiter;
	
	public TravelClubFile() {
		// 
		this.fileName = "TravelClubFile.db"; 
		this.tempFileName = "TravelClubTempFile.txt"; 
		this.delimiter = "||"; 
	}
	
	public int count() {
		// 
		int count = 0; 
        BufferedReader reader; 
        
		try {
	        reader = requestReader(fileName);
			
			while(true) {
				if(reader.readLine() == null) {
					break; 
				}
				count++; 
			}
			reader.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return count; 
	}
	
	public boolean exist(String id) {
		// 
        boolean found = false;
        BufferedReader reader; 
        
		try {
	        reader = requestReader(fileName);
			
	        String line = null; 
			while(true) {
				if((line = reader.readLine()) == null) {
					break; 
				}
				
				if (hasId(line, id)) {
					found = true; 
					break; 
				}; 
			}
			reader.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return found; 
	}
	
	@Override
	public void create(TravelClub club) {
		//
		if (this.exist(club.getId())) {
			return; 
		}
		
		FileWriter fileWriter;
		try {
			fileWriter = requestFileWriter(fileName);
			fileWriter.write(convertToStr(club)); 
			fileWriter.write("\r\n"); 
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public TravelClub retrieve(String clubId) {
		// 
		TravelClub resultClub = null; 
        BufferedReader reader; 
		try {
	        reader = requestReader(fileName);
			
	        String line = null; 
			while(true) {
				if((line = reader.readLine()) == null) {
					return null;
				}
				
				if (hasId(line, clubId)) {
					resultClub = convertToObject(line); 
					break; 
				}; 
			}
			reader.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return resultClub; 
	}
	
	public TravelClub retrieveByName(String name) {
		// 
		TravelClub resultClub = null; 
        BufferedReader reader; 
		try {
	        reader = requestReader(fileName);
			
	        String line = null; 
			while(true) {
				if((line = reader.readLine()) == null) {
					return null;
				}
				
				if (hasName(line, name)) {
					resultClub = convertToObject(line); 
					break; 
				}; 
			}
			reader.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return resultClub; 
	}

	@Override
	public Set<TravelClub> retrieveAll() {
		// 
		Set<TravelClub> resultClubs = new HashSet<>(); 
        BufferedReader reader; 
		try {
	        reader = requestReader(fileName);
			
	        String line = null; 
			while(true) {
				if((line = reader.readLine()) == null) {
					break; 
				}
				
				TravelClub club = convertToObject(line); 
				if (club != null) {
					resultClubs.add(club); 
				}
			}
			reader.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return resultClubs; 
	}
	
	public void update(TravelClub club) {
		// 
		BufferedReader reader;
        PrintWriter writer;
		try {
	        reader = requestReader(fileName);
	        File tempFile = new File(this.tempFileName); 
	        writer = new PrintWriter(new FileWriter(tempFile)); 
	        
	        String line = null;
	        String id = club.getId(); 
	        
	        while ((line = reader.readLine()) != null) {
	        	if (hasId(line, id)) { 
	        		line = convertToStr(club); 
	        	}
	            writer.println(line);
	            writer.flush();
	        }
	        writer.close();
	        reader.close();
	        
	        if (!tempFile.renameTo(this.createFile(fileName))) { 
	            System.out.println("Could not rename file");
	        }
	        
	        if (!tempFile.delete()) {
	            System.out.println("Could not delete file");
	            return;
	        }

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void delete(TravelClub club) {
		//
		this.delete(club.getId());
	}

	public void delete(String id) {
		// 
		BufferedReader reader;
        PrintWriter writer;
		try {
	        reader = requestReader(fileName);
	        File tempFile = new File(this.tempFileName); 
	        writer = new PrintWriter(new FileWriter(tempFile)); 
	        
	        String line = null;
	        
	        while ((line = reader.readLine()) != null) {
	        	if (hasId(line, id)) { 
	        		continue; 
	        	}
	            writer.println(line);
	            writer.flush();
	        }
	        writer.close();
	        reader.close();
	        
	        if (!tempFile.renameTo(this.createFile(fileName))) { 
	            System.out.println("Could not rename file");
	        }

	        if (!tempFile.delete()) {
	            System.out.println("Could not delete file");
	            return;
	        }

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private boolean hasName(String line, String name) {
		// 
		StringTokenizer tokenizer = new StringTokenizer(line, delimiter);

		tokenizer.nextToken();
		String token = tokenizer.nextToken();
		if (name.equals(token)) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean hasId(String line, String id) {
		// 
		StringTokenizer tokenizer = new StringTokenizer(line, delimiter);

		String token = tokenizer.nextToken();
		if (id.equals(token)) {
			return true;
		} else {
			return false;
		}
	}

	private String convertToStr(TravelClub club) {
		// 
		StringBuilder builder = new StringBuilder(); 
		
		builder.append(club.getId()); 
		builder.append(this.delimiter); 
		builder.append(club.getName()); 
		builder.append(this.delimiter); 
		builder.append(club.getIntro()); 
		
		return builder.toString(); 
	}
	
	private TravelClub convertToObject(String clubStr) {
		// 
		StringTokenizer tokenizer = new StringTokenizer(clubStr, this.delimiter); 
		TravelClub resultClub = null; 
		
		try {
			String id = tokenizer.nextToken(); 
			String name = tokenizer.nextToken(); 
			String intro = tokenizer.nextToken(); 
			resultClub = new TravelClub(id); 
			resultClub.setName(name); 
			resultClub.setIntro(intro);
		} catch (NoSuchElementException e) {
			//
			e.printStackTrace(); 
		}

		return resultClub; 
	}

	private BufferedReader requestReader(String fileName) throws IOException {
		//
		return new BufferedReader(new FileReader(createFile(fileName)));
	}

	private FileWriter requestFileWriter(String fileName) throws IOException {
		//
		return new FileWriter(createFile(fileName), true);
	}
	
	private File createFile(String fileName) throws IOException {
		//
		String typeFolderName = "filedb"; 
		String folderName = "step42"; 
		File resourceFile = null; 
		
		try {
			String fullFileName; 
			String pathName; 

			String cannonicalPath = (new File(".")).getCanonicalPath();
		    String fileSeparator = System.getProperty("file.separator"); 

		    StringBuilder builder = new StringBuilder(); 
		    builder.append(cannonicalPath).append(fileSeparator);
		    builder.append("resource").append(fileSeparator); 
	    	builder.append(typeFolderName).append(fileSeparator);
    		builder.append(folderName); 
		    pathName = builder.toString();

		    builder.append(fileSeparator).append(fileName); 
		    fullFileName = builder.toString();

			Path path = Paths.get(pathName); 
	        if (!Files.exists(path)) {
                Files.createDirectories(path);
	        }
	        
			resourceFile = new File(fullFileName);
			if (!resourceFile.exists()) {
				resourceFile.createNewFile(); 
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return resourceFile; 
	}
	
	public static void main(String[] args) {
		// 
		TravelClubFile clubFile = new TravelClubFile(); 
		String clubName = "clubOne"; 
		clubFile.create(new TravelClub(clubName, "It's very nice club.")); 
		
		TravelClub readClub = clubFile.retrieve(clubName);
		System.out.println("read club --> " + readClub.toString()); 
	}
}