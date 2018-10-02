package editor.model;

public class UIData implements Comparable<UIData> {
	
	private static final String DEFAULT_IMG = "images/basic.png";
	
	private String myName;
	private String myImg;
	private int mySoundIndex;
	
	public UIData(String name, String imgLocation, int sndIndex){
		
		myName = name;
		myImg = imgLocation;
		mySoundIndex = sndIndex;
		
	}
	
	public UIData(){
		myName = "character";
		myImg = DEFAULT_IMG;
		mySoundIndex = 0;
	}
	
	public UIData(UIData old) {
		this(old.getName(), old.getImageLocation(), old.getSndIndex());
	}

	public String getName() {
		return myName;
	}

	public void setName(String name) {
		myName = name;
	}

	public String getImageLocation() {
		return myImg;
	}

	public void setImageLocation(String img) {
		myImg = img;
	}

	public int getSndIndex() {
		return mySoundIndex;
	}

	public void setSndIndex(int sndIndex) {
		mySoundIndex = sndIndex;
	}

	@Override
	public int compareTo(UIData o) {
		return 0;
	}

}
