package common.zeroquest.lib;

/**
* @author ProPercivalalb
*/
public enum Localizations {

		EN_US("en_US.lang");
    
		String fileName;

		Localizations(String file) {
				this.fileName = file;
		}

		public String getFile() {
			return this.fileName;
		}

    public static final String LANG_RESOURCE_LOCATION = "/assets/zero_quest/lang/";
}