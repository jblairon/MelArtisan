package fr.jose.plateformeArtisan.tools;

public class FormatNombre {
	
	//supprime les 0 après la virgule si la partie décimale est égale à 0
	public static String supprimeZeroApresVirgule(float f) {
		String floatString = new Float(f).toString();
        int positVirgule = floatString.lastIndexOf(".");
        boolean sansVirgule = true;
        for(int i=positVirgule+1;i<floatString.length();i++){
            if(floatString.charAt(i) != '0'){
                sansVirgule = false;
                break;
            }
        }
        return sansVirgule ? floatString.substring(0, positVirgule) : floatString;
	}

}
