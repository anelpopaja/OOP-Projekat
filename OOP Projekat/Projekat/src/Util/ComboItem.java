package Util;

public class ComboItem {

	    private Integer key;
	    private String value;

	    public ComboItem(Integer key, String value)
	    {
	        this.key = key;
	        this.value = value;
	    }

	    @Override
	    public String toString()
	    {
	        return value;
	    }

	    public Integer getKey()
	    {
	        return key;
	    }

	    public String getValue()
	    {
	        return value;
	    }
}
