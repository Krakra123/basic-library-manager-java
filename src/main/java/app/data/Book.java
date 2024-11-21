package app.data;

import java.util.ArrayList;
import java.util.List;

public class Book {

	public static class VolumeInfo {
		public static class ImageLinks {
			public String thumbnail = "";
		}

		public String title = "";
		public List<String> authors = new ArrayList<>();
		public String publisher = "";
		public String publishedDate = "";
		public String description = "";
		public List<String> categories = new ArrayList<>();
		public int pageCount = 0;
		public ImageLinks imageLinks = new ImageLinks();
		public String previewLink = "";
		public String infoLink = "";
	}

	public static class AccessInfo {
		public String webReaderLink = "";
	}
	
	public String id = "";
	public VolumeInfo volumeInfo = new VolumeInfo();
	public AccessInfo accessInfo = new AccessInfo();
}