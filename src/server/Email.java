package server;
import java.util.Date;

public class Email {
	
	public static final String EMAIL_SPLT = ";;";
    private String writer;
    private Date date;
    private String matter;
	private String content;
    
    public Email(String pWriter, Date pDate, String pMatter, String pContent){
        writer = pWriter;
        date = pDate;
        matter = pMatter;
        content = pContent;
    }
    
    /**
	 * @return the writer
	 */
	public String getWriter() {
		return writer;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @return the matter
	 */
	public String getMatter() {
		return matter;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	
	public String toString() {
		return writer + EMAIL_SPLT + date.toString() + EMAIL_SPLT + matter + EMAIL_SPLT + content;
	}
    
}