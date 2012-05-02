package com.dm.content.tourapi.ctrl;

/**
 * @author  Gwak seok jong
 */
public abstract class api {
	protected String key;
	public abstract String getUrl(String[] parm);
	/**
	 * @param _key
	 * @uml.property  name="key"
	 */
	public abstract void setKey(String _key);
	public api(String _key) {
		setKey(_key);
	};
}
