package util;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

public class FajlKlasa {

    
    public static String dohvatiPutanjuSlike() {
        try {
            ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance()
                    .getExternalContext().getContext();
            String pathString = ctx.getRealPath("/");
            Path path = Paths.get(pathString);
            Path path2 = path.getParent().getParent();
            pathString = path2.toString();
            pathString = pathString + "\\web\\resources\\slike";
            return pathString;
        } catch (Exception ex) {
            Logger.getLogger(FajlKlasa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
